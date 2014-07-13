package com.android.apps.eventplanner.models;

import java.net.URL;
import java.util.List;

public class Theme {
	
	private String name;
	private List<Item> item;
	private List<EventRecommendation.Type> eventType;

	public class Item {
		private String id;
		private String name;
		private float price;
		private URL url; //amazon/e-bay
		private Type type;
		
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
		public Type getType() {
			return type;
		}
	}
	
	enum Type {
		DECORATION("Decoration"),
		COSTUME("Costume"),
		CAKE_DESIGN("Cake Design");
		
		private String type;
		
		private Type(String type) {
			this.type = type;
		}
	}
	
	public String getName() {
		return name;
	}

	public List<Item> getItem() {
		return item;
	}

	public List<EventRecommendation.Type> getEventType() {
		return eventType;
	}
}
