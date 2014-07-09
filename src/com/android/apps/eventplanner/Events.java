package com.android.apps.eventplanner;

import java.io.Serializable;
import java.util.ArrayList;

public class Events implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1798493934024749490L;
	private String imageSrc;
	private String eventDescription;
	private int eventID;

	public Events(int eventID, String eventDescription, String imageSrc){
		this.eventID = eventID;
		this.eventDescription = eventDescription;
		this.imageSrc = imageSrc;
	}
	
	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}	

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+eventID;
	}
	
	public static ArrayList<Events> getEvents(){
		ArrayList<Events> events = new ArrayList<Events>();
		events.add(new Events(1, "BDay", "ic_bday"));
		events.add(new Events(2, "Bachelorette", "ic_bachelorette"));
		events.add(new Events(3, "Wedding", "ic_wedding"));
		events.add(new Events(4, "BabyShower", "ic_baby"));
		return events;
	}
	
	

}
