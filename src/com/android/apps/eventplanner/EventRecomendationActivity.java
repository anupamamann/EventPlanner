package com.android.apps.eventplanner;

import java.util.ArrayList;

import com.myandroid.apps.eventplanner.R;
import com.myandroid.apps.eventplanner.R.id;
import com.myandroid.apps.eventplanner.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class EventRecomendationActivity extends Activity {

	GridView gvImages;
	ArrayList<Events> eventsList; 
	EventTypeArrayAdapter eventAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_recomendation);
		gvImages=(GridView)findViewById(R.id.gvEventTypes);
		
		eventsList = Events.getEvents();
		eventAdapter = new EventTypeArrayAdapter(this, eventsList);
		gvImages.setAdapter(eventAdapter);
		
	}
}
