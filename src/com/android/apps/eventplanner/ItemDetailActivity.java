package com.android.apps.eventplanner;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.android.apps.eventplanner.fragments.FoodMenuFragment;

public class ItemDetailActivity extends FragmentActivity {
	FoodMenuFragment menuList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		
		
		//you must be coming on this activity from a particular to do list item
		//based on the item in to do ..pull up the fragment to display
		//I'm pulling foodMenu fragment as of now
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.flContainer, new FoodMenuFragment());
		ft.commit();
	
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.menu_category, menu);
		return super.onCreateOptionsMenu(menu);
	}
}
