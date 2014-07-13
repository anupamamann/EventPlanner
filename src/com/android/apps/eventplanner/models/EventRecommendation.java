package com.android.apps.eventplanner.models;

import java.util.List;

import com.android.apps.eventplanner.utils.Constants.EventType;


public class EventRecommendation {
	
	private EventType type;
	private List<Venue> venues;
	private List<FoodMenu> foods;
	private List<Theme> themes;
	private List<MusicPlaylist> playlists;
	
	public EventRecommendation(EventType t) {
		this.type = t;
	}
	
	public EventType getType() {
		return type;
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

}
