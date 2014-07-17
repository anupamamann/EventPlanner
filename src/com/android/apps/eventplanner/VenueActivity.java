package com.android.apps.eventplanner;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ActionBar.Tab;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;

import com.android.apps.eventplanner.VenuePhotosDialog.MapViewFragmentListener;
import com.android.apps.eventplanner.fragments.CreateEventFragment;
import com.android.apps.eventplanner.fragments.CreateEventFragment.CreateEventListener;
import com.android.apps.eventplanner.fragments.DatePickerFragment.DateChangeListner;
import com.android.apps.eventplanner.fragments.ListViewFragment;
import com.android.apps.eventplanner.fragments.ListViewFragment.ListViewFragmentListener;
import com.android.apps.eventplanner.fragments.MapViewFragment;
import com.android.apps.eventplanner.listeners.FragmentTabListener;
import com.android.apps.eventplanner.models.Events;
import com.android.apps.eventplanner.models.Venue;
import com.android.apps.eventplanner.utils.Constants;
import com.android.apps.eventplanner.utils.Constants.EventType;

public class VenueActivity extends FragmentActivity implements
		ListViewFragmentListener, MapViewFragmentListener, CreateEventListener, DateChangeListner {
	
	VenueListArrayAdapter adapter;
	Venue venue;
	FragmentManager fm;
	Menu menu;
	MenuItem miSummary;
	CreateEventFragment createFragment;
	EventType eventType;

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venue);
		
		setupTabs();
		fm = getSupportFragmentManager();
		
		// getIntent Object
		String typeFromIntent = getIntent()
				.getStringExtra(Constants.EVENT_TYPE);
		if (typeFromIntent != null)
			eventType = EventType.valueOf(typeFromIntent.toUpperCase());
		else
			eventType = Events.getInstance().getType();
		Log.i("EVENT", eventType.name());
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
	
	public EventType getEventType() {
		return eventType;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_univ, menu);
        this.menu = menu;
        return true;
	}
	
	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(Events.getInstance() != null && miSummary == null) {
            alterMenu();
        }
        return true;
    }
	
	public void onSave(MenuItem mi) {
		// simple implementation
		/*Intent i = new Intent(this, TodoActivity.class);
		Events.getInstance().setVenue(venue);
		//i.putExtra("venue", venue.getName());
		startActivity(i);*/
		
		// better implementation
		//save object into current event
		Events currentEvent = Events.getInstance();
		if(currentEvent == null){
			//open dialog to notify and create event
			openCreateNewEventDialog();
			alterMenu();
		} else {
			currentEvent.setVenue(venue);
			showEventCreatedDialog("Item saved to " + currentEvent.getName());
		}
		Toast.makeText(VenueActivity.this,
				"Added " + venue.getName() + " to event", Toast.LENGTH_LONG)
				.show();
		Events.getInstance().setType(eventType);
	}
	
	public void onCreateNewEvent(MenuItem mi) {
		openCreateNewEventDialog();
	}
	
	public void showEventCreatedDialog(String message){
		AlertDialog.Builder alertNotification = new AlertDialog.Builder(this);
		alertNotification.setTitle(message);
		alertNotification.setCancelable(true);
		alertNotification.setPositiveButton("ok", new DialogInterface.OnClickListener(){
	            public void onClick(DialogInterface dialog, int which) {
	            	dialog.dismiss();
	            }

	        });
		alertNotification.create();
	     alertNotification.show();
	}
	
	private void openCreateNewEventDialog() {
		FragmentManager fm = getSupportFragmentManager();
		createFragment = CreateEventFragment.newInstance("New Event");
		createFragment.show(fm, "compose_dialog");
	}

	@Override
	public void onEventCreate(String eventName) {
		showEventCreatedDialog("Event " + eventName +" created");
		//Toast.makeText(this, "New event:" + eventName + " created!", Toast.LENGTH_SHORT).show();
	}
	
	public void onSummary(MenuItem mi) {
		Intent i = new Intent(this, TodoActivity.class);
		startActivity(i);
	}
	
	private void alterMenu() {
		 menu.removeItem(R.id.miCreateNewEvent);
	     miSummary = menu.add("Summary");
	     miSummary.setIcon(R.drawable.ic_saved_events);
	     miSummary.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	     miSummary.setOnMenuItemClickListener(new OnMenuItemClickListener() {

				@Override
				public boolean onMenuItemClick(MenuItem item) {
					Intent i = new Intent(VenueActivity.this, TodoActivity.class);
					startActivity(i);
					return true;
				}
		    	 
		     });
	}

	@Override
	public void onDateSelected(String text) {
		createFragment.onDateSet(text);
	}


}