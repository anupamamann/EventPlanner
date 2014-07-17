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
		setFoodMap();
	}
	
	//Not being used
	public FoodMenu(int id, ArrayList<Food> food, List<EventType> eventList, String menuUrl, Cuisine cuisine ) {
		put("id", id);
		put("url", menuUrl);
		put("cuisine", cuisine.toString());
		
		//this.foods =   food;
		//this.cuisine = cuisine;
		setURL(menuUrl);
		setFoodMap();
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
			//Log.d("food map:" , foodTypeMap.size()+"");
		}
	}
	
	
	public static ArrayList<FoodMenu> getFoodMenu(EventType type){
		ArrayList<FoodMenu> foodMenu = new ArrayList<FoodMenu>();
		
		for(FoodMenu f : getFoodMenu()){
			if(f.getEvents().toString().contains(type.toString())){
				foodMenu.add(f);
			}
		}
		return foodMenu;
	}
	
	public static ArrayList<FoodMenu> getFoodMenu(){
		ArrayList<FoodMenu> foodMenu = new ArrayList<FoodMenu>();
		
		//Parse query 
		
		ParseQuery<FoodMenu> query  = ParseQuery.getQuery(FoodMenu.class);
		query.findInBackground(new FindCallback<FoodMenu>() {

			@Override
			public void done(List<FoodMenu> fMenu, ParseException arg1) {
				if(fMenu !=null)
//					Log.d("url:", fMenu.get(0).getUrls().toString() + fMenu.get(0).getList("events").toString());
					for(FoodMenu f : fMenu){
	//					Log.d("list:", f.getList("events").toString());
						if(f.getList("events").toString().contains(EventType.BIRTHDAY.toString())){
							//add to food List
						}
					}
			}
			
				});
	
		
		
		ArrayList<Food> fList = new ArrayList<Food>();
		fList.add(new Food("Kesari Rasmalai", "Soft poached homemade cheese dumplings in a saffron flavored light milk syrup", "CheeseCake",FoodType.DESSERT ));
		fList.add(new Food("Samosa","Crisp Dumplings, Spiced Potato, Peas", "cheesecake", FoodType.APPETIZER ));
		fList.add(new Food("Asparagus Shiitake Tulsi Shorba","Asparagus, Shiitake, Indian Basil Soup", "cheesecake", FoodType.APPETIZER ));
		fList.add(new Food("Duck Tinka Kebab","Tawa seared Sonoma Duck Breast, Tempered Yogurt, Fenugreek, Nigella Dill Caper Chutney", "cheesecake", FoodType.APPETIZER ));
		fList.add(new Food("Awadhi Lamb Qorma","Cashew Nuts, Almonds, Shallots, Orris Essence, Saffron", "cheesecake", FoodType.ENTREE ));
		fList.add(new Food("Kadhai Paneer","Cottage Cheese, Onion, Tomato, Coriander, Crushed Chili", "cheesecake", FoodType.ENTREE ));
		fList.add(new Food("Silken Tofu &Vegetable Kofta (Vegan)","Spiced Tofu Dumplings, Watsonville Organic Vegetables, Onion, Tomato Sauce", "cheesecake", FoodType.ENTREE ));
		fList.add(new Food("Mango Green Olive Sea Bass Curry","Sea Bass, Shallots, Coconut", "cheesecake", FoodType.ENTREE ));
		fList.add(new Food("Flying Horse","Indian Lager", "cheesecake", FoodType.BEVERAGE ));
		fList.add(new Food("Taj Mahal","Indian Lager", "cheesecake", FoodType.BEVERAGE ));
		
		
		List<EventType> eList = new ArrayList<EventType>();
		eList.add(EventType.WEDDING);
		eList.add(EventType.BIRTHDAY);
		eList.add(EventType.BABY_SHOWER);
		
		
		ArrayList<Food> fList1 = new ArrayList<Food>();
		fList.add(new Food("Guacamole","Our famous avocado dip made fresh daily in half or full order", "CheeseCake", FoodType.APPETIZER ));
		fList.add(new Food("Queso Fundido","8 Golden crisp mini-tacos served with guacamole and sour topping.", "cheesecake", FoodType.ENTREE ));
		fList.add(new Food("Botana Grande","A wonderful combination of taquitos, quesadillas and garnachas. Served with guacamole and sour topping.", "cheesecake", FoodType.ENTREE ));
		fList.add(new Food("Mexican Dessert Flan","sponge cake soaked in a mixture of three kinds of milk, topped with whipped cream and strawberries", "mexicanDessert", FoodType.DESSERT ));
		fList.add(new Food("Modello","Lager", "cheesecake", FoodType.BEVERAGE ));
		
		List<EventType> eList1 = new ArrayList<EventType>();
		eList.add(EventType.WEDDING);
		eList.add(EventType.BIRTHDAY);
		
		ArrayList<Food> fList2 = new ArrayList<Food>();
		fList1.add(new Food("Bite-Sized Burgers on Mini-Buns Served with Grilled Onions, Pickles and Ketchup.", "Roadside Sliders", "cheesecake", FoodType.APPETIZER));
		fList1.add(new Food("A Rich Parmesan Cream Sauce. Available with Chicken..", "Fettuccini Alfredo", "cheesecake", FoodType.ENTREE));
		fList1.add(new Food("Italian Custard Made with Mascarpone, Whipped Cream, Lady Fingers, Marsala and Coffee Liqueur. Topped with Whipped Cream and Ground Chocolate..", "Tiramisu", "cheesecake", FoodType.DESSERT));
		fList1.add(new Food("Rum and Tropical Juices Topped with Myer's and Kraken Rums .", "Mai Tai", "cheesecake", FoodType.BEVERAGE));
		
		List<EventType> eList2 = new ArrayList<EventType>();
		eList.add(EventType.WEDDING);
		eList.add(EventType.BIRTHDAY);
		eList.add(EventType.COCKTAIL);

		foodMenu.add(new FoodMenu(1, fList, "http://www.lafiestarestaurant.net/menu.nxg", Cuisine.MEXICAN, eList));
		foodMenu.add(new FoodMenu(2, fList1, "http://www.amber-india.com/Indian-Restaurant-San-Francisco/menu.html", Cuisine.INDIAN, eList1));
		foodMenu.add(new FoodMenu(3, fList2, "http://www.thecheesecakefactory.com/menu/welcome/Welcome", Cuisine.ITALIAN, eList2));
	/*	foodMenu.add(new FoodMenu(Food.getFoodMenu(), "http://www.maggianos.com/en/pages/menu.aspx", Cuisine.ITALIAN));
		foodMenu.add(new FoodMenu(Food.getFoodMenu(), "http://www.maggianos.com/en/pages/menu.aspx", Cuisine.ITALIAN));
		foodMenu.add(new FoodMenu(Food.getFoodMenu(), "http://www.maggianos.com/en/pages/menu.aspx", Cuisine.ITALIAN));
		foodMenu.add(new FoodMenu(Food.getFoodMenu(), "http://www.maggianos.com/en/pages/menu.aspx", Cuisine.ITALIAN));*/
		Log.d("Size of food:" , foodMenu.size()+"");
		return foodMenu;
		
	}
	
}
