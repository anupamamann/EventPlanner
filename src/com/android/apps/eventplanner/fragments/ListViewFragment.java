package com.android.apps.eventplanner.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.apps.eventplanner.EventApplication;
import com.android.apps.eventplanner.R;
import com.android.apps.eventplanner.VenueActivity;
import com.android.apps.eventplanner.VenueListArrayAdapter;
import com.android.apps.eventplanner.models.Venue;
import com.android.apps.eventplanner.utils.GoogleClient;
import com.android.apps.eventplanner.utils.Constants.EventType;

public class ListViewFragment extends Fragment {
	ArrayList<Venue> venues;
	VenueListArrayAdapter venueAdapter;
	ListView lvVenues;
	Activity context;
	Button btnAddVenueList;
	
	public interface ListViewFragmentListener {
		void passAdapter(VenueListArrayAdapter adapter);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = activity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		venues = new ArrayList<Venue>();
		venueAdapter = new VenueListArrayAdapter(context, venues);
		
		//dummy venues
		Venue v1 = new Venue("myVenue1", "100 Road, Sunnyvale", 100);
		populatePhotos(v1.getPhotos());
		Venue v2 = new Venue("myVenue2", "200 Road, Sunnyvale", 200);
		venues.add(v1);
		venues.add(v2);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_list_view, container, false);
		lvVenues = (ListView) v.findViewById(R.id.lvVenueList);
		lvVenues.setAdapter(venueAdapter);
		ListViewFragmentListener l = (ListViewFragmentListener) getActivity();
		l.passAdapter(venueAdapter);
		return v;
	}
	
	private void populatePhotos(List<String> venuePhotoUrls) {
		GoogleClient gClient = EventApplication.getClient();
		EventType query = ((VenueActivity) context).getEventType();
		gClient.getImages(venuePhotoUrls, query);
	}

}
