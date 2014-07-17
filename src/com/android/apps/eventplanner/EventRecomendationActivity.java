package com.android.apps.eventplanner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.android.apps.eventplanner.models.EventRecommendation;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class EventRecomendationActivity extends Activity {

	GridView gvImages;
	ArrayList<EventRecommendation> eventsList; 
	EventTypeArrayAdapter eventAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_recomendation);
		gvImages=(GridView)findViewById(R.id.gvEventTypes);
		eventsList = new ArrayList<EventRecommendation>();
		eventAdapter = new EventTypeArrayAdapter(this, eventsList);
		gvImages.setAdapter(eventAdapter);
		Toast.makeText(EventRecomendationActivity.this, "Parse", Toast.LENGTH_LONG).show();
		
	
		
		ParseQuery<EventRecommendation> query  = ParseQuery.getQuery(EventRecommendation.class);
		query.findInBackground(new FindCallback<EventRecommendation>() {

			@Override
			public void done(List<EventRecommendation> events, ParseException arg1) {
				Log.d("events", events.size()+":" + events.get(0).getEventImage());
				if(events !=null)
					eventsList.addAll(events);
			}
		});
	
	}
}
