package com.android.apps.eventplanner;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.Context;

import com.android.apps.eventplanner.models.EventRecommendation;
import com.android.apps.eventplanner.models.Events;
import com.android.apps.eventplanner.models.Food;
import com.android.apps.eventplanner.models.FoodMenu;
import com.android.apps.eventplanner.utils.Constants.Cuisine;
import com.android.apps.eventplanner.utils.Constants.EventType;
import com.android.apps.eventplanner.utils.Constants.FoodType;
import com.android.apps.eventplanner.utils.GoogleClient;
import com.parse.Parse;
import com.parse.ParseObject;

public class EventApplication extends Application {
	
	static GoogleClient client;
	static EventRecommendation recoBirthday;
	static EventRecommendation recoWedding;
	static EventRecommendation recoCocktail;
	static EventRecommendation recoBabyShower;
	
	//FoodMenu
	static FoodMenu fMenuBirthday;
	static FoodMenu fMenuWedding;
	static FoodMenu fMenuCocktail;
	static FoodMenu fMenuBaby;

	@Override
	public void onCreate() {
		
		
		ParseObject.registerSubclass(Events.class);
		ParseObject.registerSubclass(EventRecommendation.class);
		ParseObject.registerSubclass(FoodMenu.class);
		Parse.initialize(this, "5bkiaqUQfGNuyF46iQGG3gUsTX3tMA9vufPT02J3", "ko6ZAHYOHSEasTb78JzmnEemNdJWACkSXfYru3fg");
		
		
		recoBirthday = new EventRecommendation(EventType.BIRTHDAY, "birthday");
		recoWedding = new EventRecommendation(EventType.WEDDING, "wedding");
		recoCocktail = new EventRecommendation(EventType.COCKTAIL, "cocktail");
		recoBabyShower = new EventRecommendation(EventType.BABY_SHOWER,"baby");
		
		//recoBirthday.saveInBackground();
				//recoBabyShower.saveInBackground();
				//recoCocktail.saveInBackground();
				//recoWedding.saveInBackground();
		
		ParseObject f1 = new ParseObject("Food");
		f1.put("title","Roadside Sliders");
		f1.put("image","cheesecake" );
		f1.put("description","Bite-Sized Burgers on Mini-Buns Served with Grilled Onions" );
		f1.put("type", FoodType.APPETIZER.toString());
		
		ParseObject f2 = new ParseObject("Food");
		f2.put("title","Fettuccini Alfredo");
		f2.put("image","cheesecake" );
		f2.put("description","A Rich Parmesan Cream Sauce. Available with Chicken.." );
		f2.put("type", FoodType.ENTREE.toString());
		
		ParseObject f3 = new ParseObject("Food");
		f1.put("title","Tiramisu");
		f1.put("image","cheesecake" );
		f1.put("description","Italian Custard Made with Mascarpone, Whipped Cream, Lady Fingers, Marsala and Coffee Liqueur. Topped with Whipped Cream and Ground Chocolate..s" );
		f1.put("type", FoodType.DESSERT.toString());
		
		ParseObject f4 = new ParseObject("Food");
		f2.put("title","Mai Tai");
		f2.put("image","cheesecake" );
		f2.put("description","Rum and Tropical Juices Topped with Myer's and Kraken Rums.." );
		f2.put("type", FoodType.BEVERAGE.toString());
		
		
		ArrayList<ParseObject> foods = new ArrayList<ParseObject>();
		foods.add(f1);
		foods.add(f2);
		foods.add(f3);
		foods.add(f4);
		
		
		ArrayList<Food> fList = new ArrayList<Food>();
		fList.add(new Food("Bite-Sized Burgers on Mini-Buns Served with Grilled Onions, Pickles and Ketchup.", "Roadside Sliders", "cheesecake", FoodType.APPETIZER));
		fList.add(new Food("A Rich Parmesan Cream Sauce. Available with Chicken..", "Fettuccini Alfredo", "cheesecake", FoodType.ENTREE));
		fList.add(new Food("Italian Custard Made with Mascarpone, Whipped Cream, Lady Fingers, Marsala and Coffee Liqueur. Topped with Whipped Cream and Ground Chocolate..", "Tiramisu", "cheesecake", FoodType.DESSERT));
		fList.add(new Food("Rum and Tropical Juices Topped with Myer's and Kraken Rums .", "Mai Tai", "cheesecake", FoodType.BEVERAGE));
		
		
		List<EventType> eList = new ArrayList<EventType>();
		eList.add(EventType.WEDDING);
		eList.add(EventType.BIRTHDAY);
		
		ArrayList<String> eList1 = new ArrayList<String>();
		eList1.add(EventType.WEDDING.toString());
		eList1.add(EventType.BIRTHDAY.toString());
		
		fMenuBirthday = new FoodMenu(1, fList,eList ,"http://www.thecheesecakefactory.com/menu/welcome/Welcome", Cuisine.ITALIAN);
		fMenuBirthday.put("food", foods);
		fMenuBirthday.put("events", eList1);
		fMenuBirthday.saveInBackground();
		

		client = new GoogleClient();
		super.onCreate();
	}
	
	public static GoogleClient getClient() {
		return client;
	}

	public static EventRecommendation getRecoBirthday() {
		return recoBirthday;
	}

	public static EventRecommendation getRecoWedding() {
		return recoWedding;
	}

	public static EventRecommendation getRecoCocktail() {
		return recoCocktail;
	}

	public static EventRecommendation getRecoBabyShower() {
		return recoBabyShower;
	}

	
}
