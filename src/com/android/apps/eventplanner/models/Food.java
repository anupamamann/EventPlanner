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
	
	public Food(String Description, String Title, String image, FoodType type) {
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
	
	
	public static ArrayList<Food> getFoodMenu(){
		ArrayList<Food> fList = new ArrayList<Food>();
		fList.add(new Food("PineApple Upside Downside Cheesecake","CheeseCake", "cheesecake", FoodType.DESSERT ));
		fList.add(new Food("Samosa","Samosa", "cheesecake", FoodType.APPETIZER ));
		fList.add(new Food("Mysore Dosa","Mysore Dosa", "cheesecake", FoodType.ENTREE ));
		fList.add(new Food("ShockTop","Belgium Ale", "cheesecake", FoodType.BEVERAGE ));
		
		
		fList.add(new Food("Bite-Sized Burgers on Mini-Buns Served with Grilled Onions, Pickles and Ketchup.", "Roadside Sliders", "cheesecake", FoodType.APPETIZER));
		fList.add(new Food("A Rich Parmesan Cream Sauce. Available with Chicken..", "Fettuccini Alfredo", "cheesecake", FoodType.ENTREE));
		fList.add(new Food("Italian Custard Made with Mascarpone, Whipped Cream, Lady Fingers, Marsala and Coffee Liqueur. Topped with Whipped Cream and Ground Chocolate..", "Tiramisu", "cheesecake", FoodType.DESSERT));
		fList.add(new Food("Rum and Tropical Juices Topped with Myer's and Kraken Rums .", "Mai Tai", "cheesecake", FoodType.BEVERAGE));
		
		return fList;
	}
	
	
}
