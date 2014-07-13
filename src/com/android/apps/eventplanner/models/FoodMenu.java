package com.android.apps.eventplanner.models;

import java.net.URL;
import java.util.List;

public class FoodMenu {
	
	private List<Food> foods;
	private Cuisine cuisine;
	private List<URL> urls;
	
	class Food {
		private long id;
		private String title;
		private String Description;
		private Type type;
		private String imgUrl;
		
		public String getTitle() {
			return title;
		}
		public String getImgUrl() {
			return imgUrl;
		}
		public String getDescription() {
			return Description;
		}
		public long getId() {
			return id;
		}
		public Type getType() {
			return type;
		}
	}
	
	enum Cuisine {
		INDIAN("Indian"),
		MEDITERRANEAN("Iediterranean"),
		ITALIAN("Italian"),
		AMERICAN("American"),
		MEXICAN("Mexican"),
		CHINESE("Chinese");
		
		private String cuisine;
		
		private Cuisine(String type) {
			this.cuisine = type;
		}
	}
	
	enum Type {
		APPETIZER("Appetizer"),
		ENTREE("Entree"),
		DESSERT("Dessert"),
		BEVERAGE("Beverage");
		
		private String type;
		
		private Type(String type) {
			this.type = type;
		}
	}
	
	public List<Food> getFoods() {
		return foods;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public List<URL> getUrls() {
		return urls;
	}

}
