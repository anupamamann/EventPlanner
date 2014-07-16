package com.android.apps.eventplanner;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.android.apps.eventplanner.fragments.ListViewFragment;
import com.android.apps.eventplanner.fragments.ListViewFragment.ListViewFragmentListener;
import com.android.apps.eventplanner.fragments.MapViewFragment;
import com.android.apps.eventplanner.listeners.FragmentTabListener;
import com.android.apps.eventplanner.VenuePhotosDialog.MapViewFragmentListener;
import com.android.apps.eventplanner.models.Venue;

public class VenueActivity extends FragmentActivity implements
		ListViewFragmentListener, MapViewFragmentListener {
	
	VenueListArrayAdapter adapter;
	Venue venue;

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venue);
		
		setupTabs();

	}

	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
			.newTab()
			.setText("Map View")
			.setIcon(R.drawable.ic_tab_map)
			.setTag("MapViewFragment")
			.setTabListener(
				new FragmentTabListener<MapViewFragment>(R.id.flContainer, this, "mapview",
						MapViewFragment.class));

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
			.newTab()
			.setText("List View")
			.setIcon(R.drawable.ic_tab_list)
			.setTag("ListViewFragment")
			.setTabListener(
			    new FragmentTabListener<ListViewFragment>(R.id.flContainer, this, "listview",
			    		ListViewFragment.class));

		actionBar.addTab(tab2);
	}

	// Define a DialogFragment that displays the error dialog
	public static class ErrorDialogFragment extends DialogFragment {

		// Global field to contain the error dialog
		private Dialog mDialog;

		// Default constructor. Sets the dialog field to null
		public ErrorDialogFragment() {
			super();
			mDialog = null;
		}

		// Set the dialog to display
		public void setDialog(Dialog dialog) {
			mDialog = dialog;
		}

		// Return a Dialog to the DialogFragment.
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return mDialog;
		}
	}
	
	public void onAddVenueList(View v) {
		Venue toAdd = adapter.getSelectedPosition();
		Toast.makeText(this, toAdd.getName(), Toast.LENGTH_SHORT).show();
	}
	
	public void onAddVenueMap(View v) {
		Toast.makeText(this, venue.getName(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void passAdapter(VenueListArrayAdapter adapter) {
		this.adapter = adapter;
	}

	@Override
	public void passVenue(Venue v) {
		this.venue = v;
	}


}