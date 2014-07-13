package com.android.apps.eventplanner.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.apps.eventplanner.FoodMenuArrayAdapter;
import com.android.apps.eventplanner.R;
import com.android.apps.eventplanner.models.Food;

public class FoodMenuFragment extends Fragment {
	
	ArrayList<Food> menu;
	FoodMenuArrayAdapter fAdapter;
	ListView lvMenu;
	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		menu = Food.getFoodMenu();
		fAdapter = new FoodMenuArrayAdapter(getActivity(), menu);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_food_menu, container, false);
		lvMenu = (ListView)v.findViewById(R.id.lvMenu);
		lvMenu.setAdapter(fAdapter);
		return v;

	}
}
