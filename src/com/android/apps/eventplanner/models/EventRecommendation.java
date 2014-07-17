package com.android.apps.eventplanner.models;

import java.util.List;

import android.util.Log;
import android.widget.Toast;

import com.android.apps.eventplanner.utils.Constants.EventType;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Represents Event Recommendation Table in Parse
 * Get methods retrieves all events 
 * 
 */

@ParseClassName("EventRecommendation")
public class EventRecommendation extends ParseObject {
	
	private EventType type;
	private String eventImage;
	private List<Venue> venues;
	private List<FoodMenu> foods;
	private List<Theme> themes;
	private List<MusicPlaylist> playlists;
	
	public EventRecommendation(){
		super();
	}
	
	public EventRecommendation(EventType type, String imgSrc) {
		put("type", type.toString());
		put("eventImage",imgSrc );
		
	}
	
	public void  setType( EventType type) {
		put("type", type.toString());
	}
	
	public String getType() {
		return getString("type");
	}
	
	public String getEventImage() {
		return getString("eventImage");
	}

	public void setEventImage(String eventImage) {
		put("eventImage", eventImage);
		//this.eventImage = eventImage;
	}
	
	public List<Venue> getVenues() {
		return venues;
	}
	

	public List<FoodMenu> getFoods() {
		return foods;
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public List<MusicPlaylist> getPlaylists() {
		return playlists;
	}

	
	public static ParseQuery<EventRecommendation> getQuery(){
		return ParseQuery.getQuery(EventRecommendation.class);
	
	}

	


}
