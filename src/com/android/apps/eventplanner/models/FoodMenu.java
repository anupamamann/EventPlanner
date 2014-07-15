package com.android.apps.eventplanner.models;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;
import android.widget.Toast;

import com.android.apps.eventplanner.utils.Constants.Cuisine;
import com.android.apps.eventplanner.utils.Constants.FoodType;

public class FoodMenu {
	
	private ArrayList<Food> foods;
	private Map<FoodType, ArrayList<Food>> foodTypeMap;
	private URL urls;
	private Cuisine cuisine;
	
	public FoodMenu(ArrayList<Food> food, String menuUrl, Cuisine cuisine ) {
		this.foods =   food;
		this.cuisine = cuisine;
		setURL(menuUrl);
		setFoodMap();
	}
	
	public List<Food> getFoods() {
		return foods;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public URL getUrls() {
		return urls;
	}

	public Map<FoodType, ArrayList<Food>> getFoodTypeMap() {
		return foodTypeMap;
	}

	public void setURL(String menuUrl){
		URL url; 
		try{
		url =  new URL(menuUrl);
		this.urls = url;
		}catch(Exception e){
			Log.e("FoodMenu", "Malformed URL");
		}
	}
	
	
	public void setFoodMap(){
		foodTypeMap =  new HashMap<FoodType, ArrayList<Food>>();
		ArrayList<Food> tempFood;
		if(foods != null){
			for(Food f: foods){
				Log.d("Food", "food:" + f.getDescription() + "type:" + f.getType().getType());
				if( foodTypeMap.containsKey(f.getType())){
					foodTypeMap.get(f.getType()).add(f);
				}else{
					tempFood = new ArrayList<Food>();
					tempFood.add(f);
					foodTypeMap.put(f.getType(),  tempFood);
				}
				
			}
			Log.d("food map:" , foodTypeMap.size()+"");
		}
	}
	
	public static ArrayList<FoodMenu> getFoodMenu(){
		ArrayList<FoodMenu> foodMenu = new ArrayList<FoodMenu>();
		foodMenu.add(new FoodMenu(Food.getFoodMenu(), "http://www.lafiestarestaurant.net/menu.nxg", Cuisine.MEXICAN));
		foodMenu.add(new FoodMenu(Food.getFoodMenu(), "http://www.amber-india.com/Indian-Restaurant-San-Francisco/menu.html", Cuisine.INDIAN));
		foodMenu.add(new FoodMenu(Food.getFoodMenu(), "http://www.maggianos.com/en/pages/menu.aspx", Cuisine.ITALIAN));
		
		return foodMenu;
		
	}
	
	
	

}
