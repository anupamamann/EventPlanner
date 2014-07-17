package com.android.apps.eventplanner.fragments;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.apps.eventplanner.FoodMenuArrayAdapter;
import com.android.apps.eventplanner.R;
import com.android.apps.eventplanner.models.Food;
import com.android.apps.eventplanner.models.FoodMenu;
import com.android.apps.eventplanner.utils.Constants;
import com.android.apps.eventplanner.utils.Constants.FoodType;
import com.android.apps.eventplanner.utils.Functions;
import com.android.apps.eventplanner.utils.SectionedAdapter;

public class FoodMenuFragment extends Fragment {
	
	List<FoodMenu> foodMenu;
	FoodMenu fMenu;
	ArrayList<Food> food;
	FoodMenuArrayAdapter fAdapter;
	ListView lvMenu;
	
	Menu mMenu;
	int currentPage;
	ImageView ivFood;
	TextView tvTitle; 
	TextView tvDescription;
	
	
	public static FoodMenuFragment newInstance(int page, FoodMenu fMenu) {
		FoodMenuFragment fragmentFirst = new FoodMenuFragment();
		Bundle args = new Bundle();
		args.putInt("currentPage", page);
		args.putSerializable("fMenu", fMenu);
		fragmentFirst.setArguments(args);
		return fragmentFirst;
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//foodMenu = FoodMenu.getFoodMenu();
		//food = Food.getFoodMenu();
		
		fMenu = (FoodMenu)getArguments().getSerializable("fMenu");
		currentPage = getArguments().getInt("currentPage");
		setHasOptionsMenu(true);
	}
	
	public FoodMenu getFood(){
		return this.fMenu;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_food_menu, container, false);
		lvMenu = (ListView)v.findViewById(R.id.lvMenu);
		
		//btAdd.setContentDescription(contentDescription);
//		Toast.makeText(getActivity(),"App:"+foodMenu.get(0).getFoodTypeMap().get(FoodType.APPETIZER).get(0).getDescription()+"",Toast.LENGTH_LONG).show();
		
		for(FoodType f: fMenu.getFoodTypeMap().keySet()){
			//Toast.makeText(getActivity(),f.getType() + ":" + fMenu.getFoodTypeMap().get(f).get(0).getDescription() , Toast.LENGTH_SHORT).show();
			adapter.addSection(f.getType(),new ArrayAdapter<Food>(getActivity(),R.layout.item_food, fMenu.getFoodTypeMap().get(f)));
		}
		/*for(FoodType f: foodMenu.get(0).getFoodTypeMap().keySet()){
			Toast.makeText(getActivity(),f.getType() + ":" + foodMenu.get(0).getFoodTypeMap().get(f).get(0).getDescription() , Toast.LENGTH_SHORT).show();
			adapter.addSection(f.getType(),new ArrayAdapter<Food>(getActivity(),R.layout.item_food, foodMenu.get(0).getFoodTypeMap().get(f)));
		}*/
		
		lvMenu.setAdapter(adapter);
		
	/*	if(Events.getInstance() !=null){
			mMenu.findItem(R.id.miAdd).setVisible(false);
		}else{
			mMenu.findItem(R.id.miAdd).setVisible(true);
		}*/
		
		
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
	

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		mMenu = menu;
		
		inflater.inflate(R.menu.menu_todo, menu);
	}
	
	
	@Override
	
	public boolean onOptionsItemSelected(MenuItem item) {
	   // handle item selection
	   switch (item.getItemId()) {
	      case R.id.miAdd:
	          Toast.makeText(getActivity(), "Open create dialog", Toast.LENGTH_SHORT).show();
	          //
	          
	          //disable Add button
	          if(mMenu != null){
	        	  mMenu.findItem(R.id.miAdd).setVisible(false);
	          }
	         return true;
	      default:
	         return super.onOptionsItemSelected(item);
	   }
	}



	
}
