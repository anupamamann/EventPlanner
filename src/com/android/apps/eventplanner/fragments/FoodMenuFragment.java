package com.android.apps.eventplanner.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
		fMenu = (FoodMenu)getArguments().getSerializable("fMenu");
		
		currentPage = getArguments().getInt("currentPage");
		Log.d("Fragment Page:", currentPage + ":URL:"+fMenu.getUrls());

		
//			lvMenu.setAdapter(adapter);
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

		Log.d("View", fMenu.getUrls().toString());
		 
		 Map<FoodType, ArrayList<Food>> foodSectionalList = getSectionalFoodMenu(fMenu);
			for(FoodType f: foodSectionalList.keySet()){
		 adapter.addSection(f.getType(),new ArrayAdapter<Food>(getActivity(),R.layout.item_food, fMenu.getFoods()));
			}
		
			/*Map<FoodType, ArrayList<Food>> foodSectionalList = getSectionalFoodMenu(fMenu);
		for(FoodType f: foodSectionalList.keySet()){
			adapter.addSection(f.getType(),new ArrayAdapter<Food>(getActivity(),R.layout.item_food, foodSectionalList.get(f)));
		}*/
		lvMenu.setAdapter(adapter);
		return v;

	}

	public Map<FoodType, ArrayList<Food>> getSectionalFoodMenu(FoodMenu fMenu){
		Map<FoodType, ArrayList<Food>> foodSectionalList = new HashMap<FoodType, ArrayList<Food>>();
		for(Food f: fMenu.getFoods()){
			if(foodSectionalList.get(f.getType()) == null){
				ArrayList<Food> fList = new ArrayList<Food>();
				foodSectionalList.put(f.getType(), fList);
			}else{
				ArrayList<Food> fList = foodSectionalList.get(f.getType());
				fList.add(f);
				foodSectionalList.put(f.getType(), fList);
			}
		}
		return foodSectionalList;

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
			//Toast.makeText(getActivity(), "Open create dialog", Toast.LENGTH_SHORT).show();
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
