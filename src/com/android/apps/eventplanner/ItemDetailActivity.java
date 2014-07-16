package com.android.apps.eventplanner;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.apps.eventplanner.ItemDetailActivity.MyPagerAdapter;
import com.android.apps.eventplanner.fragments.FoodMenuFragment;
import com.android.apps.eventplanner.models.FoodMenu;
import com.android.apps.eventplanner.utils.SmartFragmentStatePagerAdapter;

public class ItemDetailActivity extends FragmentActivity {
	SmartFragmentStatePagerAdapter adapterViewPager;
	ViewPager vpPager;
	
	ImageButton btAdd;
	ImageButton btNext;
	ImageButton btPrevious;
	
	
	public static class MyPagerAdapter extends SmartFragmentStatePagerAdapter  {
		private static int NUM_ITEMS = 3;
		static String fragmentName;
		static String fragmentInstance;
		
		public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
        
        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
        
        public static void setNumCount(int total_pages){
        	NUM_ITEMS = total_pages;
        }
        
        /**
         * 
         * @param totalPages - Number of Objects in the list view to be displayed 
         * @param customFragmentClassName - Custom Fragment   
         * @param staticInstanceMethod - static method 
         */
        public static void setFragment(int totalPages, String customFragmentClassName, String staticMethod){
        	NUM_ITEMS = totalPages;
        	fragmentName = customFragmentClassName;
        	fragmentInstance = staticMethod;
        }
        
        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
        	// return Class.forname(fragmentName).getMethod(fragmentInstance, null).invoke(page, array).
              return FoodMenuFragment.newInstance(position,  FoodMenu.getFoodMenu().get(position));
        }
         
        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
        	return "Page " + position;
        }
        
    }

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		//getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);
		View mActionBarView = getLayoutInflater().inflate(R.layout.my_action_bar, null);
		getActionBar().setCustomView(mActionBarView);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		
		btAdd = (ImageButton)findViewById(R.id.btnAdd);
		//btNext = (ImageButton)findViewById(R.id.btnNext);
		//btPrevious = (ImageButton)findViewById(R.id.btnPrevious);
		
		vpPager = (ViewPager) findViewById(R.id.vpPager);
		adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
		
		//you must be coming on this activity from a particular to do list item
		//based on the item in to do ..pull up the fragment to display
		//I'm pulling foodMenu fragment as of now
		MyPagerAdapter.setFragment(FoodMenu.getFoodMenu().size(), "FoodMenuFragment", "newInstance");
		vpPager.setAdapter(adapterViewPager);
		
		 vpPager.setOnPageChangeListener(new OnPageChangeListener() {
	        	
	        	// This method will be invoked when a new page becomes selected.
	        	@Override
	        	public void onPageSelected(int position) {
	        		Toast.makeText(ItemDetailActivity.this,"Selected page position: " + position, Toast.LENGTH_SHORT).show();
	        	}
	        	
	        	// This method will be invoked when the current page is scrolled
	        	@Override
	        	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	        		// Code goes here, handle the case where 
	        	}
	        	
	        	// Called when the scroll state changes: 
	        	// SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
	        	@Override
	        	public void onPageScrollStateChanged(int state) {
	        		// Code goes here
	        	}
	        });
		
		
		 btAdd.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//get FoodList 
					FoodMenuFragment selectedFood = (FoodMenuFragment)adapterViewPager.getRegisteredFragment(vpPager.getCurrentItem());
					//save object into current event
					//Toast.makeText(ItemDetailActivity.this, selectedFood.getFood().getUrls().toString(), Toast.LENGTH_LONG).show();
				}
			});
		
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
