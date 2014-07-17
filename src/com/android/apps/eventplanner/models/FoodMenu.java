package com.android.apps.eventplanner.models;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.android.apps.eventplanner.utils.Constants.Cuisine;
import com.android.apps.eventplanner.utils.Constants.EventType;
import com.android.apps.eventplanner.utils.Constants.FoodType;
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("FoodMenu")
public class FoodMenu extends ParseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1938346539234896128L;
	private int id;
	private ArrayList<Food> foods;
	private ArrayList<ParseObject> foodPO;
	private Map<FoodType, ArrayList<Food>> foodTypeMap;
	private URL urls;
	private Cuisine cuisine;
	private List<EventType> eventList;
	
	public FoodMenu(){
		super();
	}
	
	public FoodMenu(int id, ArrayList<Food> food,  String menuUrl, Cuisine cuisine, List<EventType> eventList ) {
		this.id = id;
		this.foods = food;
		this.cuisine = cuisine;
		this.eventList = eventList;
		setURL(menuUrl);
	//	setFoodMap();
	}
	
	public List<EventType> getEvents() {
		//return getList("food");
		return this.eventList;
	}
	
	public List<Food> getFoods() {
		//return getList("food");
		return this.foods;
	}

	public Cuisine getCuisine() {
		//return Cuisine.valueOf(getString("cuisine"));
		return this.cuisine;
		
	}

	public URL getUrls() {
		//setURL(getString("url"));
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
			//	Log.d("Food", "food:" + f.getDescription() + "type:" + f.getType().getType());
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
	
	
	public static ArrayList<FoodMenu> getFoodMenu(EventType type){
		ArrayList<FoodMenu> foodMenu = new ArrayList<FoodMenu>();
		if(type == null)
			foodMenu.add(getFoodMenu().get(2));
		else{
			for(FoodMenu f : getFoodMenu()){
				if(f.getEvents().toString().contains(type.toString())){
					foodMenu.add(f);
				}
			}
		}
		return foodMenu;
	}
	
	public static ArrayList<FoodMenu> getFoodMenu(){
		ArrayList<FoodMenu> foodMenu = new ArrayList<FoodMenu>();
		
		//Parse query 
		ArrayList<Food> fList = new ArrayList<Food>();
		fList.add(new Food("Kesari Rasmalai", "Soft poached homemade cheese dumplings in a saffron flavored light milk syrup", "rasmalai",FoodType.DESSERT ));
		fList.add(new Food("Samosa","Crisp Dumplings, Spiced Potato, Peas", "samosa", FoodType.APPETIZER ));
		fList.add(new Food("Asparagus Shiitake Tulsi Shorba","Asparagus, Shiitake, Indian Basil Soup", "soup", FoodType.APPETIZER ));
		fList.add(new Food("Duck Tinka Kebab","Tawa seared Sonoma Duck Breast, Tempered Yogurt, Fenugreek, Nigella Dill Caper Chutney", "duck", FoodType.APPETIZER ));
		fList.add(new Food("Awadhi Lamb Qorma","Cashew Nuts, Almonds, Shallots, Orris Essence, Saffron", "duck", FoodType.ENTREE ));
		fList.add(new Food("Kadhai Paneer","Cottage Cheese, Onion, Tomato, Coriander, Crushed Chili", "paneer", FoodType.ENTREE ));
		fList.add(new Food("Silken Tofu &Vegetable Kofta (Vegan)","Spiced Tofu Dumplings, Watsonville Organic Vegetables, Onion, Tomato Sauce", "paneer", FoodType.ENTREE ));
		fList.add(new Food("Mango Green Olive Sea Bass Curry","Sea Bass, Shallots, Coconut", "duck", FoodType.ENTREE ));
		fList.add(new Food("Flying Horse","Indian Lager", "flying", FoodType.BEVERAGE ));
		
		
		List<EventType> eList = new ArrayList<EventType>();
		eList.add(EventType.WEDDING);
		eList.add(EventType.BIRTHDAY);
		eList.add(EventType.BABY_SHOWER);
		
		
		ArrayList<Food> fList1 = new ArrayList<Food>();
		fList.add(new Food("Guacamole","Our famous avocado dip made fresh daily in half or full order", "gaucamole", FoodType.APPETIZER ));
		fList.add(new Food("Queso Fundido","8 Golden crisp mini-tacos served with guacamole and sour topping.", "pizza", FoodType.ENTREE ));
		fList.add(new Food("Botana Grande","A wonderful combination of taquitos, quesadillas and garnachas. Served with guacamole and sour topping.", "paneer", FoodType.ENTREE ));
		fList.add(new Food("Mexican Dessert Flan","sponge cake soaked in a mixture of three kinds of milk, topped with whipped cream and strawberries", "flan", FoodType.DESSERT ));
		fList.add(new Food("Modello","Lager", "flying", FoodType.BEVERAGE ));
		
		List<EventType> eList1 = new ArrayList<EventType>();
		eList1.add(EventType.WEDDING);
		eList1.add(EventType.BIRTHDAY);
		
		ArrayList<Food> fList2 = new ArrayList<Food>();
		fList1.add(new Food( "Roadside Sliders", "Bite-Sized Burgers on Mini-Buns Served with Grilled Onions, Pickles and Ketchup.", "sliders",FoodType.APPETIZER));
		fList1.add(new Food( "Fettuccini Alfredo", "A Rich Parmesan Cream Sauce. Available with Chicken..","alfredo", FoodType.ENTREE));
		fList1.add(new Food( "Tiramisu", "Italian Custard Made with Mascarpone, Whipped Cream, Lady Fingers, Marsala and Coffee Liqueur. Topped with Whipped Cream and Ground Chocolate..","tiramisu", FoodType.DESSERT));
		fList1.add(new Food( "Mai Tai","Rum and Tropical Juices Topped with Myer's and Kraken Rums .", "maitai", FoodType.BEVERAGE));
		
		List<EventType> eList2 = new ArrayList<EventType>();
		eList2.add(EventType.WEDDING);
		eList2.add(EventType.BIRTHDAY);
		eList2.add(EventType.COCKTAIL);

		foodMenu.add(new FoodMenu(1, fList, "http://www.lafiestarestaurant.net/menu.nxg", Cuisine.MEXICAN, eList));
		foodMenu.add(new FoodMenu(2, fList1, "http://www.amber-india.com/Indian-Restaurant-San-Francisco/menu.html", Cuisine.INDIAN, eList1));
		foodMenu.add(new FoodMenu(3, fList2, "http://www.thecheesecakefactory.com/menu/welcome/Welcome", Cuisine.ITALIAN, eList2));
		return foodMenu;
		
	}
	
}
