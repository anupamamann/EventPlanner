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
	
	
	private static Events instance = null;
	
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
	private ArrayList<Song> music;
	private List<Guest> guests;
	

	
	
	public Events() {
		// TODO Auto-generated constructor stub
	}
	
	public static Events getInstance() {
	      return instance;
	   }
	
	 public static Events createEvent() {
	      if(instance == null) {
	         instance = new Events();
	      }
	      return instance;
	   }
	 
/*	public Events(int eventID, String eventDescription, String imageSrc){
		this.eventID = eventID;
		this.eventDescription = eventDescription;
		this.imageSrc = imageSrc;
	}*/
	
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
	
	
	public static Events getCurrentEvent(){
		
		return new Events();
	}


	public FoodMenu getFood() {
		return food;
	}


	public void setFood(FoodMenu food) {
		this.food = food;
	}


	public Theme getTheme() {
		return theme;
	}


	public void setTheme(Theme theme) {
		this.theme = theme;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Venue getVenue() {
		return venue;
	}


	public void setVenue(Venue venue) {
		this.venue = venue;
	}


	public ArrayList<Song> getMusic() {
		return music;
	}


	public void setMusic(ArrayList<Song> music) {
		this.music = music;
	}


	public List<Guest> getGuests() {
		return guests;
	}


	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}


	public EventType getType() {
		return type;
	}


	public void setType(EventType type) {
		this.type = type;
	}

}
