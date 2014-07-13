package com.android.apps.eventplanner.models;

import java.util.ArrayList;

import com.android.apps.eventplanner.utils.Constants.FoodType;


public class Food {

	private String title;
	private String image;	 
	private String description;
	private int id; 
	private FoodType type;
	
	public Food(int ID, String Description, String Title, String image, FoodType type) {
		this.description =  Description;
		this.id = ID;
		this.image = image;
		this.title = Title;
		this.type =  type;
				
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public FoodType getType() {
		return type;
	}
	public void setType(FoodType type) {
		this.type = type;
	}
	
	
	public static ArrayList<Food> getFoodMenu(){
		ArrayList<Food> fList = new ArrayList<Food>();
		fList.add(new Food(1, "PineApple Upside Downside Cheesecake","CheeseCake", "cheesecake", FoodType.DESSERT ));
		fList.add(new Food(2, "Samosa","Samosa", "cheesecake", FoodType.APPETIZER ));
		fList.add(new Food(3, "Mysore Dosa","Mysore Dosa", "cheesecake", FoodType.ENTREE ));
		fList.add(new Food(4, "ShockTop","Belgium Ale", "cheesecake", FoodType.BEVERAGE ));
		
		return fList;
	}
	
	
}
