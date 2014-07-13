package com.android.apps.eventplanner;

import android.app.Application;

import com.android.apps.eventplanner.models.EventRecommendation;
import com.android.apps.eventplanner.models.Events;
import com.parse.Parse;
import com.parse.ParseObject;

public class EventApplication extends Application {
	
	EventRecommendation recoBirthday;

	@Override
	public void onCreate() {
		
		super.onCreate();
		ParseObject.registerSubclass(Events.class);
		Parse.initialize(this, "5bkiaqUQfGNuyF46iQGG3gUsTX3tMA9vufPT02J3", "ko6ZAHYOHSEasTb78JzmnEemNdJWACkSXfYru3fg");
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("foo", "bar");
		testObject.saveInBackground();
		
		recoBirthday = new EventRecommendation(EventRecommendation.Type.BIRTHDAY);
		//set other attributes food/venue/themes too
	}
	
}
