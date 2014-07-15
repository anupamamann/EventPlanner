package com.android.apps.eventplanner;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.apps.eventplanner.fragments.FoodMenuFragment;

public class ItemDetailActivity extends FragmentActivity {
	FoodMenuFragment menuList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		//getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);
		
		View mActionBarView = getLayoutInflater().inflate(R.layout.my_action_bar, null);
		getActionBar().setCustomView(mActionBarView);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		//you must be coming on this activity from a particular to do list item
		//based on the item in to do ..pull up the fragment to display
		//I'm pulling foodMenu fragment as of now
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.flContainer, new FoodMenuFragment());
		ft.commit();
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.menu_todo, menu);
		return true;
	}
	
	public void onBack(View v) {
		Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
		
	}
	
	
}
