package com.android.apps.eventplanner;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.apps.eventplanner.fragments.CreateEventFragment;
import com.android.apps.eventplanner.fragments.CreateEventFragment.CreateEventListener;
import com.android.apps.eventplanner.fragments.DatePickerFragment.DateChangeListner;
import com.android.apps.eventplanner.fragments.FoodMenuFragment;
import com.android.apps.eventplanner.models.Events;
import com.android.apps.eventplanner.models.FoodMenu;
import com.android.apps.eventplanner.utils.Constants;
import com.android.apps.eventplanner.utils.Constants.EventType;
import com.android.apps.eventplanner.utils.Functions;
import com.android.apps.eventplanner.utils.SmartFragmentStatePagerAdapter;

public class ItemDetailActivity extends FragmentActivity implements CreateEventListener, DateChangeListner {
	SmartFragmentStatePagerAdapter adapterViewPager;
	ViewPager vpPager;
	
	ImageButton btNext;
	ImageButton btPrevious;
	TextView tvNotification;
	CreateEventFragment createFragment;
	Menu menu;
	MenuItem miSummary;
	EventType eventType;
	
	
	public static class MyPagerAdapter extends SmartFragmentStatePagerAdapter  {
		private static int NUM_ITEMS = 3;
		static String fragmentName;
		static String fragmentInstance;
		static EventType eType;
		
		
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
        public static void setFragment(int totalPages, String customFragmentClassName, String staticMethod, EventType type){
        	NUM_ITEMS = totalPages;
        	fragmentName = customFragmentClassName;
        	fragmentInstance = staticMethod;
        	eType = type;
        }
        
        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
        	 
        	// return Class.forname(fragmentName).getMethod(fragmentInstance, null).invoke(page, array).
        		Log.d("Food URL:",FoodMenu.getFoodMenu(eType).get(position).getUrls() + "" + ":position " + position );
              return FoodMenuFragment.newInstance(position,  FoodMenu.getFoodMenu(eType).get(position));
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
		
		
		//btAdd = (ImageButton)findViewById(R.id.btnAdd);
		btNext = (ImageButton)findViewById(R.id.btnNext);
		btPrevious = (ImageButton)findViewById(R.id.btnPrevious);
		tvNotification = (TextView)findViewById(R.id.tvNotification);
		
		
		vpPager = (ViewPager) findViewById(R.id.vpPager);
		adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
		
		//you must be coming on this activity from a particular to do list item
		//based on the item in to do ..pull up the fragment to display
		//I'm pulling foodMenu fragment as of now
		
		// getIntent Object
				String typeFromIntent = getIntent()
						.getStringExtra(Constants.EVENT_TYPE);
				if (typeFromIntent != null)
					eventType = EventType.valueOf(typeFromIntent.toUpperCase());
				else
					eventType = Events.getInstance().getType();
				Log.i("EVENT", eventType.name());
				EventType eType = null;
				if (typeFromIntent != null) {
					eType = EventType.valueOf(typeFromIntent.toUpperCase());
				}
		
		
		//getFood for given Event
			
		MyPagerAdapter.setFragment(FoodMenu.getFoodMenu(eType).size(), "FoodMenuFragment", "newInstance",eType);
		vpPager.setAdapter(adapterViewPager);
		
		 vpPager.setOnPageChangeListener(new OnPageChangeListener() {
	        	
	        	// This method will be invoked when a new page becomes selected.
	        	@Override
	        	public void onPageSelected(int position) {
	        		if(position==0){
	        			//Toast.makeText(ItemDetailActivity.this,"previous invisible: " + position, Toast.LENGTH_SHORT).show();
	        			btPrevious.setClickable(false);
	        		}
	        		if(position == (MyPagerAdapter.NUM_ITEMS - 1)){
	        			//Toast.makeText(ItemDetailActivity.this,"next invisible: " + position, Toast.LENGTH_SHORT).show();
	        			btNext.setClickable(false);
	        		}
	        		
	        	}
	        	
	        	// This method will be invoked when the current page is scrolled
	        	@Override
	        	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	        		// Code goes here, handle the case where 
	        		if(position==0){
	        			//Toast.makeText(ItemDetailActivity.this,"previous invisible: " + position, Toast.LENGTH_SHORT).show();
	        			btPrevious.setClickable(false);
	        		}
	        		if(position == (MyPagerAdapter.NUM_ITEMS - 1)){
	        			//Toast.makeText(ItemDetailActivity.this,"next invisisble: " + position, Toast.LENGTH_SHORT).show();
	        			btNext.setClickable(false);
	        		}
	        	}
	        	
	        	// Called when the scroll state changes: 
	        	// SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
	        	@Override
	        	public void onPageScrollStateChanged(int state) {
	        		// Code goes here
	        	}
	        });
		
		
		 	 
		 btNext.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(ItemDetailActivity.this,"Next", Toast.LENGTH_LONG).show();
					
				}
			});
			
			btPrevious.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(ItemDetailActivity.this,"Previous", Toast.LENGTH_LONG).show();
					
				}
			});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.menu_todo, menu);
		this.menu = menu;
		return true;
	}
	
	public void onBack(View v) {
		//Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
		
	}
	
	@Override
	public void onEventCreate(String eventName) {
		// TODO Auto-generated method stub
		showEventCreatedDialog("Event " + eventName +" created");
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if(Events.getInstance() != null && miSummary == null) {
            alterMenu();
        }
		/*if (Events.getInstance() != null) {
			menu.removeItem(R.id.miCreateNewEvent);
		}*/
		return true;
	}

	
	private void alterMenu() {
		 menu.removeItem(R.id.miCreateNewEvent);
	     miSummary = menu.add("Summary");
	     miSummary.setIcon(R.drawable.ic_saved_events);
	     miSummary.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	     miSummary.setOnMenuItemClickListener(new OnMenuItemClickListener() {

				@Override
				public boolean onMenuItemClick(MenuItem item) {
					Intent i = new Intent(ItemDetailActivity.this, TodoActivity.class);
					startActivity(i);
					return true;
				}
		    	 
		     });
	}
	
	public void showEventCreatedDialog(String message){
		AlertDialog.Builder alertNotification = new AlertDialog.Builder(this);
		alertNotification.setTitle(message);
		alertNotification.setCancelable(true);
		alertNotification.setPositiveButton("ok", new DialogInterface.OnClickListener(){
	            public void onClick(DialogInterface dialog, int which) {
	            	dialog.dismiss();
	            }

	        });
		alertNotification.create();
	     alertNotification.show();
	}


	@Override
	public void onDateSelected(String text) {
		//call other fragment and send date selected
		//CreateEventFragment frag  = (CreateEventFragment) 
        //        getSupportFragmentManager().findFragmentById(R.id.flContainer);
		createFragment.onDateSet(text);
		
	}
	
	public void onSave(MenuItem mi) {
		// TODO Auto-generated method stub
		// get FoodList
		Fragment selectedFragment = adapterViewPager
				.getRegisteredFragment(vpPager.getCurrentItem());

		// save object into current event
		Events currentEvent = Events.getInstance();
		if (currentEvent == null) {
			// open dialog to notify and create event
			FragmentManager fm = getSupportFragmentManager();
			createFragment = CreateEventFragment
					.newInstance("New Event");
			createFragment.show(fm, "compose_dialog");
			alterMenu();

		} else {
			if (selectedFragment instanceof FoodMenuFragment) {
				currentEvent.setFood(((FoodMenuFragment) selectedFragment)
						.getFood());
				showEventCreatedDialog("Item saved to "
						+ currentEvent.getName());

			}
			/*
			 * else if(selectedFragment instanceof MusicFragment){
			 * currentEvent.setMusic
			 * (((MusicFragment)selectedFragment).getPlaylist()); }
			 */
		}

		//Toast.makeText(ItemDetailActivity.this,	((FoodMenuFragment) selectedFragment).getFood().getUrls().toString(), Toast.LENGTH_LONG).show();
	}
		
		  public void onCreateNewEvent(MenuItem mi) {
		    	   	//open New Event Dialog
		    	   Toast.makeText(this, "NEW", Toast.LENGTH_SHORT).show();
		    	   FragmentManager fm = getSupportFragmentManager();
				   createFragment = CreateEventFragment
							.newInstance("New Event");
					createFragment.show(fm, "compose_dialog");
					alterMenu();
		              
		
		        }

	
}
