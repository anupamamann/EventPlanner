package com.android.apps.eventplanner.models;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;

public class Venue {
	private String name;
	LatLng location;
	private double altitude;
	
	public Venue(String name, double lat, double lng) {
		this.name = name;
		this.location = new LatLng(lat, lng);
		//this.altitude = altitude;
	}
	
	public String getName() {
		return name;
	}
	public LatLng getLocation() {
		return location;
	}
	public double getAltitude() {
		return altitude;
	}
	
	public static Venue fromJson(JSONObject json) throws JSONException {
		Venue v = new Venue(json.getString("name"),
				json.getJSONObject("geometry").getJSONObject("location").getDouble("lat"),
				json.getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
		return v;
	}
}
