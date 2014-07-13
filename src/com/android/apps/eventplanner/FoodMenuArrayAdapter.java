package com.android.apps.eventplanner;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.apps.eventplanner.models.Food;
import com.android.apps.eventplanner.utils.Constants;
import com.android.apps.eventplanner.utils.Functions;


public class FoodMenuArrayAdapter extends ArrayAdapter<Food> {
	
	ImageView ivFood;
	TextView tvTitle; 
	TextView tvDescription;
	
	public FoodMenuArrayAdapter(Context context, List<Food> menu){
		super(context,R.layout.item_food, menu);
		
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
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
			item = this.getItem(position);
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
	
	

}