package com.android.apps.eventplanner.models;

import java.util.ArrayList;

import com.android.apps.eventplanner.utils.Constants.FOODTYPE;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class Food {

	private String title;
	private String image;	 
	private String description;
	private int id; 
	FOODTYPE type;
	
	public Food() {
		this.description = "PineApple Upside Downside Cheesecake";
		this.id = 1;
		this.image = "cheesecake";
		this.title = "CheeseCake";
				
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
	
	
	
	public static ArrayList<Food> getFoodMenu(){
		ArrayList<Food> fList = new ArrayList<Food>();
		fList.add(new Food());
		
		return fList;
	}
	
}
