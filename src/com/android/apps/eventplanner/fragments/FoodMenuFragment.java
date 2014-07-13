package com.android.apps.eventplanner.fragments;

import java.util.ArrayList;

import android.app.ListActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.apps.eventplanner.FoodMenuArrayAdapter;
import com.android.apps.eventplanner.R;
import com.android.apps.eventplanner.models.Food;
import com.android.apps.eventplanner.utils.Constants;
import com.android.apps.eventplanner.utils.Functions;
import com.android.apps.eventplanner.utils.SectionedAdapter;

public class FoodMenuFragment extends Fragment {
	
	ArrayList<Food> food;
	FoodMenuArrayAdapter fAdapter;
	ListView lvMenu;
	
	//
	ImageView ivFood;
	TextView tvTitle; 
	TextView tvDescription;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		food = Food.getFoodMenu();
		//fAdapter = new FoodMenuArrayAdapter(getActivity(), menu);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_food_menu, container, false);
		lvMenu = (ListView)v.findViewById(R.id.lvMenu);
	//	lvMenu.setAdapter(fAdapter);
		
		adapter.addSection("Appetizers",new ArrayAdapter<Food>(getActivity(),R.layout.item_food,food));
		adapter.addSection("Entree", new ArrayAdapter<Food>(getActivity(),R.layout.item_food,	food));
		adapter.addSection("Dessert", new ArrayAdapter<Food>(getActivity(),R.layout.item_food,	food));

		lvMenu.setAdapter(adapter);
		
		return v;

	}
	
	SectionedAdapter adapter=new SectionedAdapter() {
		protected View getHeaderView(String caption, int index,
				View convertView,
				ViewGroup parent) {
			TextView result=(TextView)convertView;
			if (convertView==null) {
				result=(TextView)(LayoutInflater.from(getActivity()).inflate(R.layout.header,null));
			}

			result.setText(caption);

			return(result);
		}	

		@Override
		protected View getListView(int position, View convertView, ViewGroup parent) {
			View v;
			if(convertView == null){
				v  = LayoutInflater.from(getActivity()).inflate(R.layout.item_food, parent,false);
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
