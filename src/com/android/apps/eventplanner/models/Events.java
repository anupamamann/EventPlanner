package com.android.apps.eventplanner.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.android.apps.eventplanner.utils.Constants.EventType;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Represents a Table in Parse
 * Get methods retrieves data from Parse Response
 * 
 */

@ParseClassName("Events")
public class Events extends ParseObject implements Serializable{
	
	private static final long serialVersionUID = -1798493934024749490L;
	private String imageSrc;
	private String eventDescription;
	private int eventID;
	private String name;
	private EventType type;
	private Date date;
	private FoodMenu food;
	private Theme theme;
	private Venue venue;
	private MusicPlaylist music;
	private List<Guest> guests;
	

	public Events() {
		// TODO Auto-generated constructor stub
	}
	public Events(int eventID, String eventDescription, String imageSrc){
		this.eventID = eventID;
		this.eventDescription = eventDescription;
		this.imageSrc = imageSrc;
	}
	
	public String getImageSrc() {
		return getString("imgSrc");
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getEventDescription() {
		return  getString("eventDesc");
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}	

	public int getEventID() {
		return getInt("eventId");
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+eventID;
	}
	
	public static ParseQuery<Events> getQuery(){
		return ParseQuery.getQuery(Events.class);
	}

}
