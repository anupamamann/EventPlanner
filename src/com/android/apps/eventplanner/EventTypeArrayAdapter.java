package com.android.apps.eventplanner;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.apps.eventplanner.utils.Constants;
import com.myandroid.apps.eventplanner.R;

public class EventTypeArrayAdapter extends ArrayAdapter<Events> {
	
	ImageButton ivEvent;
	TextView tvEventDescription;
	
	public EventTypeArrayAdapter(Context context, List<Events> events){
		super(context,R.layout.item_event_display, events);
		
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		if(convertView == null){
			v  = LayoutInflater.from(getContext()).inflate(R.layout.item_event_display, parent,false);
		}else{
			v = convertView;
		}
		
		setUpViews(v, position);
			
		ivEvent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String eventType = (String)v.getContentDescription();
				
				//call activity to generate event to-do list 
				Intent intent = new Intent(getContext(), CategoryActivity.class);
				intent.putExtra(Constants.EVENT_ID, eventType);
				getContext().startActivity(intent);
				
			}
		});
		
		
		return v;
		
	}
	
	
	public boolean setUpViews(View v, int position){
		Events eventInfo;
		try{
			eventInfo = this.getItem(position);
			tvEventDescription = (TextView)v.findViewById(R.id.tvEventDescription);
			ivEvent = (ImageButton)v.findViewById(R.id.imageEventBtn);
		}catch(Exception e){
			return false;
		}

		tvEventDescription.setText(eventInfo.getEventDescription());
		Resources resource = v.getContext().getResources();
		int imageId = resource.getIdentifier(eventInfo.getImageSrc(), "drawable", v.getContext().getPackageName());
		if(imageId == 0){ //set a default image
			ivEvent.setImageResource(R.drawable.ic_launcher);
		}else{
			ivEvent.setImageDrawable(resource.getDrawable(imageId));
		}
		ivEvent.setContentDescription(eventInfo.toString());
		
		return true;
	}
	
	

}