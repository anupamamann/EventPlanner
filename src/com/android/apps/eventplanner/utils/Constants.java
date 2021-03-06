package com.android.apps.eventplanner.utils;


public class Constants {

	public static final String DRAWABLE = "drawable";
	public static final String EVENT_TYPE = "eventType";
	public static final String Strings = "strings";

	public enum FoodType{
		APPETIZER("Appetizers"),
		ENTREE("Entree"),
		DESSERT("Dessert"),
		BEVERAGE("Beverage");

		private String type;
		private FoodType(String type) {
			this.type = type;
		}
		public String getType(){
			return type;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return type;
		}
	}

	public enum EventType {
		BIRTHDAY("Birthday"),
		WEDDING("Wedding"),
		BABY_SHOWER("Baby Shower"),
		COCKTAIL("Cocktail"),
		HALLOWEEN("Halloween");

		private String type;

		private EventType(String type) {
			this.type = type;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return type;
		}


	}

	public enum ThemeType {
		DECORATION("Decoration"),
		COSTUME("Costume"),
		CAKE_DESIGN("Cake Design");

		private String type;

		private ThemeType(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return type;
		}
	}

	public enum Cuisine {
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

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return cuisine;
		}
	}

}
