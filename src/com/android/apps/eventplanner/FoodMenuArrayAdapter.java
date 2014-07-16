package com.android.apps.eventplanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.apps.eventplanner.models.Food;
import com.android.apps.eventplanner.models.FoodMenu;
import com.android.apps.eventplanner.utils.Constants;
import com.android.apps.eventplanner.utils.Constants.FoodType;
import com.android.apps.eventplanner.utils.Functions;
import com.android.apps.eventplanner.utils.SectionedAdapter;


public class FoodMenuArrayAdapter extends ArrayAdapter<FoodMenu> {
	
	ListView lvMenu;
	ImageButton btAdd;
	Menu mMenu;
	
	ImageView ivFood;
	TextView tvTitle; 
	TextView tvDescription;
	
	
	
	public FoodMenuArrayAdapter(Context context, List<FoodMenu> menu){
		super(context,R.layout.fragment_food_menu, menu);
		
	}
	
		
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Toast.makeText(getContext(), "adapter",Toast.LENGTH_LONG ).show();
		View v;
		if(convertView == null){
			v  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_food_menu, parent,false);
		}else{
			v = convertView;
		}
		
		setUpListView(v, position);
			
		return v;
		
	}
	
	
	public boolean setUpListView(View v, int position){
		FoodMenu fMenu;
		Toast.makeText(getContext(), "setting up views", Toast.LENGTH_LONG).show();
		try{
			fMenu = this.getItem(position);
			lvMenu = (ListView)v.findViewById(R.id.lvMenu);
			Map<FoodType, ArrayList<Food>> food = fMenu.getFoodTypeMap();
			for(FoodType type: food.keySet()){
				ArrayList<Food> item = food.get(type);
				
				adapter.addSection(type.getType(),new ArrayAdapter<Food>(getContext(),R.layout.item_food, item));
			}
			lvMenu.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			
		}catch(Exception e){
			return false;
		}

		
		return true;
	}
	
	SectionedAdapter adapter=new SectionedAdapter() {
		protected View getHeaderView(String caption, int index,
				View convertView,
				ViewGroup parent) {
			TextView result=(TextView)convertView;
		
			if (convertView==null) {
				result=(TextView)LayoutInflater.from(getContext()).inflate(R.layout.header,null);
			}

			result.setText(caption);

			return(result);
		}	

		@Override
		protected View getListView(int position, View convertView, ViewGroup parent) {
			View v;
			Toast.makeText(getContext(), "getting list view", Toast.LENGTH_SHORT).show();
			if(convertView == null){
				v  = LayoutInflater.from(getContext()).inflate(R.layout.item_food, parent,false);
			}else{
				v = convertView;
			}
			setUpViews(v, position);
			return v;
		}
		
		public boolean setUpViews(View v, int position){
			Food item;
			try{
				item =  (Food)adapter.getItem(position);
				tvDescription = (TextView)v.findViewById(R.id.tvDescription);
				tvTitle = (TextView)v.findViewById(R.id.tvTitle);
				ivFood = (ImageView)v.findViewById(R.id.ivFoodImage);
			}catch(Exception e){
				return false;
			}

			tvDescription.setText(item.getDescription());
			tvTitle.setText(item.getTitle());
			ivFood.setImageResource(android.R.color.transparent);
		       
			Drawable image = (Drawable)Functions.getResource(v, item.getImage(), Constants.DRAWABLE);
			if(image == null){ //set a default image
				ivFood.setImageResource(R.drawable.ic_launcher);
			}else{
				ivFood.setImageDrawable(image);
			}
			
			return true;
		}
		
	};
	

}