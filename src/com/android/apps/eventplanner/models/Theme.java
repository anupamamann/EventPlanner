package com.android.apps.eventplanner.models;

import java.net.URL;
import java.util.List;

import com.android.apps.eventplanner.utils.Constants;
import com.android.apps.eventplanner.utils.Constants.ThemeType;

public class Theme {
	
	private String name;
	private List<Item> item;
	private List<Constants.EventType> eventType;

	public class Item {
		private String id;
		private String name;
		private float price;
		private URL url; //amazon/e-bay
		private ThemeType type;
		
		public String getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public float getPrice() {
			return price;
		}
		public URL getUrl() {
			return url;
		}
		public ThemeType getType() {
			return type;
		}
	}
	
	public String getName() {
		return name;
	}

	public List<Item> getItem() {
		return item;
	}

	public List<Constants.EventType> getEventType() {
		return eventType;
	}
}
