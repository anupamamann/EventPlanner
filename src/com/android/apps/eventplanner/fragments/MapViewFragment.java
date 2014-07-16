package com.android.apps.eventplanner.fragments;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.apps.eventplanner.EventApplication;
import com.android.apps.eventplanner.R;
import com.android.apps.eventplanner.VenueActivity.ErrorDialogFragment;
import com.android.apps.eventplanner.VenuePhotosDialog;
import com.android.apps.eventplanner.models.TodoListItem.Type;
import com.android.apps.eventplanner.models.Venue;
import com.android.apps.eventplanner.utils.GoogleClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MapViewFragment extends Fragment implements
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener {
	private FragmentManager fm;
	private SupportMapFragment mapFragment;
	private GoogleMap map;
	private LocationClient mLocationClient;
	private View mapView;
	private FragmentActivity myContext;
	
	/*
	 * Define a request code to send to Google Play services This code is
	 * returned in Activity.onActivityResult
	 */
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	
	@Override
	public void onAttach(Activity activity) {
		myContext = (FragmentActivity) activity;
		super.onAttach(activity);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mapView != null) {
			ViewGroup parent = (ViewGroup) mapView.getParent();
			parent.removeView(mapView);
		}
		else
			mapView = inflater.inflate(R.layout.fragment_map_view, container, false);
		return mapView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fm = myContext.getSupportFragmentManager();
		mLocationClient = new LocationClient(myContext, this, this);
		mapFragment = ((SupportMapFragment) fm.findFragmentById(R.id.map));
		if (mapFragment != null) {
			map = mapFragment.getMap();
			if (map != null) {
				Log.i("EVENT", "Map Fragment was loaded properly!");
				map.setMyLocationEnabled(true);
			} else {
				Log.i("EVENT", "Error - Map was null!!");
			}
		} else {
			Log.e("EVENT", "Error - Map Fragment was null!!");
		}
		
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

			@Override
			public void onInfoWindowClick(Marker m) {
				VenuePhotosDialog overlay = VenuePhotosDialog
						.newInstance("Birthday Party Venue pics");
				overlay.show(fm, "venue_pics");

			}
			
		});
		
		//map.animateCamera( CameraUpdateFactory.zoomTo( 5.0f ) );  
	}
	
	/*
	 * Called when the Activity becomes visible.
	 */
	@Override
	public void onStart() {
		super.onStart();
		// Connect the client.
		if (isGooglePlayServicesAvailable()) {
			mLocationClient.connect();
		}

	}

	/*
	 * Called when the Activity is no longer visible.
	 */
	@Override
	public void onStop() {
		// Disconnecting the client invalidates it.
		mLocationClient.disconnect();
		super.onStop();
	}

	/*
	 * Handle results returned to the FragmentActivity by Google Play services
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Decide what to do based on the original request code
		switch (requestCode) {

		case CONNECTION_FAILURE_RESOLUTION_REQUEST:
			/*
			 * If the result code is Activity.RESULT_OK, try to connect again
			 */
			switch (resultCode) {
			case Activity.RESULT_OK:
				mLocationClient.connect();
				break;
			}

		}
	}

	private boolean isGooglePlayServicesAvailable() {
		// Check that Google Play services is available
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(myContext);
		// If Google Play services is available
		if (ConnectionResult.SUCCESS == resultCode) {
			// In debug mode, log the status
			Log.i("Location Updates", "Google Play services is available.");
			return true;
		} else {
			// Get the error dialog from Google Play services
			Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(resultCode, myContext,
					CONNECTION_FAILURE_RESOLUTION_REQUEST);

			// If Google Play services can provide an error dialog
			if (errorDialog != null) {
				// Create a new DialogFragment for the error dialog
				ErrorDialogFragment errorFragment = new ErrorDialogFragment();
				errorFragment.setDialog(errorDialog);
				errorFragment.show(fm, "Location Updates");
			}

			return false;
		}
	}

	/*
	 * Called by Location Services when the request to connect the client
	 * finishes successfully. At this point, you can request the current
	 * location or start periodic updates
	 */
	@Override
	public void onConnected(Bundle dataBundle) {
		// Display the connection status
		Location location = mLocationClient.getLastLocation();
		if (location != null) {
			Log.i("EVENT", "GPS location was found!");
			LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
			Log.i("EVENT", latLng.latitude + "," + latLng.longitude);
			CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 8);
			map.animateCamera(cameraUpdate);
			setupMapMarkers(latLng);
		} else {
			Log.w("EVENT","Current location was null, enable GPS on emulator!");
		}
	}

	/*
	 * Called by Location Services if the connection to the location client
	 * drops because of an error.
	 */
	@Override
	public void onDisconnected() {
		// Display the connection status
		Log.w("EVENT", "Disconnected. Please re-connect.");
	}

	/*
	 * Called by Location Services if the attempt to Location Services fails.
	 */
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		/*
		 * Google Play services can resolve some errors it detects. If the error
		 * has a resolution, try sending an Intent to start a Google Play
		 * services activity that can resolve error.
		 */
		if (connectionResult.hasResolution()) {
			try {
				// Start an Activity that tries to resolve the error
				connectionResult.startResolutionForResult(myContext,
						CONNECTION_FAILURE_RESOLUTION_REQUEST);
				/*
				 * Thrown if Google Play services canceled the original
				 * PendingIntent
				 */
			} catch (IntentSender.SendIntentException e) {
				// Log the error
				e.printStackTrace();
			}
		} else {
			Log.w("EVENT","Sorry. Location services not available to you");
		}
	}
	
	private void setupMapMarkers(LatLng currentLocation) {
		//display points on map
		GoogleClient client = EventApplication.getClient();
		client.getPlaces(Type.FOOD, "birthday party", currentLocation.latitude,
				currentLocation.longitude, 100000, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				JSONArray placesJsonResults = null;
				try {
					placesJsonResults = json.getJSONArray("results");
					if (placesJsonResults != null)
					for (int i = 0;i<placesJsonResults.length();i++) {
						Venue v = Venue.fromJson(placesJsonResults.getJSONObject(i));
						map.addMarker(new MarkerOptions()
		                .title(v.getName())
		                .position(v.getLocation()));
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void onFailure(Throwable t, JSONObject response) {
				Log.w("EVENT","Failure in Google Places API");
			}
		});
	}
			
}
