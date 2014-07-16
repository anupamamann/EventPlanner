package com.android.apps.eventplanner.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.apps.eventplanner.ImageResult;
import com.google.android.gms.maps.model.LatLng;

public class Venue {
	private String name;
	private String address;
	private int capacity;
	private LatLng location;
	//List<ImageResult> venuePhotos;
	List<String> venuePhotos;
	
	public Venue(String name, double lat, double lng) {
		this.name = name;
		this.location = new LatLng(lat, lng);
	}
	
	public Venue(String name, String address, int capacity) {
		this.name = name;
		this.address = address;
		this.capacity = capacity;
		//this.venuePhotos = new ArrayList<ImageResult>();
		this.venuePhotos = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	public LatLng getLocation() {
		return location;
	}
	public String getAddress() {
		return address;
	}

	public int getCapacity() {
		return capacity;
	}
	
	/*public List<ImageResult> getPhotos() {
		return venuePhotos;
	}*/
	
	public List<String> getPhotos() {
		return venuePhotos;
	}
	
	public static Venue fromJson(JSONObject json) throws JSONException {
		Venue v = new Venue(json.getString("name"),
				json.getJSONObject("geometry").getJSONObject("location").getDouble("lat"),
				json.getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
		return v;
	}
	
	/*public void setPhotos(List<ImageResult> photos) {
		this.venuePhotos = photos;
	}*/
	
}
