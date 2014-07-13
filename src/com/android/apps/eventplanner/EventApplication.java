package com.android.apps.eventplanner;

import android.app.Application;
import android.content.Context;

import com.android.apps.eventplanner.models.EventRecommendation;
import com.android.apps.eventplanner.models.Events;
import com.android.apps.eventplanner.utils.Constants.EventType;
import com.android.apps.eventplanner.utils.GoogleClient;
import com.parse.Parse;
import com.parse.ParseObject;

public class EventApplication extends Application {
	
	static GoogleClient client;
	static EventRecommendation recoBirthday;
	static EventRecommendation recoWedding;
	static EventRecommendation recoCocktail;
	static EventRecommendation recoBabyShower;

	@Override
	public void onCreate() {
		
		super.onCreate();
		ParseObject.registerSubclass(Events.class);
		Parse.initialize(this, "5bkiaqUQfGNuyF46iQGG3gUsTX3tMA9vufPT02J3", "ko6ZAHYOHSEasTb78JzmnEemNdJWACkSXfYru3fg");
		//ParseObject testObject = new ParseObject("TestObject");
		//testObject.put("foo", "bar");
		//testObject.saveInBackground();
		
		client = new GoogleClient();
		 		
		recoBirthday = new EventRecommendation(EventType.BIRTHDAY);
		//set other attributes food/venue/themes too
		recoWedding = new EventRecommendation(EventType.WEDDING);
		recoCocktail = new EventRecommendation(EventType.COCKTAIL);
		recoBabyShower = new EventRecommendation(EventType.BABY_SHOWER);

	}
	
	public static GoogleClient getClient() {
		return client;
	}

	public static EventRecommendation getRecoBirthday() {
		return recoBirthday;
	}

	public static EventRecommendation getRecoWedding() {
		return recoWedding;
	}

	public static EventRecommendation getRecoCocktail() {
		return recoCocktail;
	}

	public static EventRecommendation getRecoBabyShower() {
		return recoBabyShower;
	}

	
}
