package com.android.apps.eventplanner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.android.apps.eventplanner.models.Events;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class EventRecomendationActivity extends Activity {

	GridView gvImages;
	ArrayList<Events> eventsList; 
	EventTypeArrayAdapter eventAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_recomendation);
		gvImages=(GridView)findViewById(R.id.gvEventTypes);
		eventsList = new ArrayList<Events>();
		eventAdapter = new EventTypeArrayAdapter(this, eventsList);
		gvImages.setAdapter(eventAdapter);
		
		ParseQuery<Events> query = Events.getQuery(); 
		query.findInBackground(new FindCallback<Events>() {

			@Override
			public void done(List<Events> events, ParseException arg1) {
				if(events !=null)
					eventsList.addAll(events);
			}
		});
	
	}
}
