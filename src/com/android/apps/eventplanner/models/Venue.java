package com.android.apps.eventplanner.models;

public class Venue {
	private String address;
	private double lat;
	private double longi;
	private double altitude;
	
	public Venue(String address, double lat, double longi, double altitude) {
		this.address = address;
		this.lat = lat;
		this.longi = longi;
		this.altitude = altitude;
	}
	
	public String getAddress() {
		return address;
	}
	public double getLat() {
		return lat;
	}
	public double getLongi() {
		return longi;
	}
	public double getAltitude() {
		return altitude;
	}
}
