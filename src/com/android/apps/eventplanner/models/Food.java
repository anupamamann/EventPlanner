package com.android.apps.eventplanner.models;

import java.util.ArrayList;

import com.android.apps.eventplanner.utils.Constants.FoodType;
import com.parse.ParseClassName;
import com.parse.ParseObject;


public class Food{

	private String title;
	private String image;	 
	private String description;
	private int id; 
	private FoodType type;
	
	public Food(){
		super();
	}
	
	public Food(String Title, String Description, String image, FoodType type) {
		//put("description", Description);
		//put("title", Title);
		//put("image", image);
		//put("type", type.toString());
		
		this.description =  Description;
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
	
	
}
