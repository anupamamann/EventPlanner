package com.android.apps.eventplanner.models;

import java.net.URL;
import java.util.List;
import java.util.Map;

import com.android.apps.eventplanner.utils.Constants.Cuisine;
import com.android.apps.eventplanner.utils.Constants.FoodType;

public class FoodMenu {
	
	private List<Food> foods;
	private Map<FoodType, List<Food>> foodTypeMap;
	private List<URL> urls;
	private Cuisine cuisine;
	
	
	public List<Food> getFoods() {
		return foods;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public List<URL> getUrls() {
		return urls;
	}

	public Map<FoodType, List<Food>> getFoodTypeMap() {
		return foodTypeMap;
	}

	public void addFood(Food food){
		foods.add(food);
		foodTypeMap.put(food.getType(),  foods);
	}
	

}
