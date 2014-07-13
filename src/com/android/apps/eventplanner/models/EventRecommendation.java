package com.android.apps.eventplanner.models;

import java.util.List;

public class EventRecommendation {
	
	private Type type;
	private List<Venue> venues;
	private List<FoodMenu> foods;
	private List<Theme> themes;
	private List<MusicPlaylist> playlists;
	
	public enum Type {
		BIRTHDAY("Birthday"),
		WEDDING("Wedding"),
		BABY_SHOWER("Baby Shower"),
		COCKTAIL("Cocktail"),
		HALLOWEEN("Halloween");
		
		private String type;
		
		private Type(String type) {
			this.type = type;
		}
	}
	
	public EventRecommendation(Type t) {
		this.type = t;
	}
	
	public Type getType() {
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
