package com.android.apps.eventplanner;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.apps.eventplanner.models.EventRecommendation;
import com.android.apps.eventplanner.models.Events;
import com.android.apps.eventplanner.utils.Constants;
import com.android.apps.eventplanner.utils.Functions;

public class EventTypeArrayAdapter extends ArrayAdapter<EventRecommendation> {
	
	ImageButton ivEvent;
	TextView tvEventDescription;
	
	public EventTypeArrayAdapter(Context context, List<EventRecommendation> events){
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
				Intent intent = new Intent(getContext(), TodoActivity.class);
				intent.putExtra(Constants.EVENT_TYPE, eventType.toString());
				getContext().startActivity(intent);
				
			}
		});
		
		
		return v;
		
	}
	
	
	public boolean setUpViews(View v, int position){
		EventRecommendation eventInfo;
		try{
			eventInfo = this.getItem(position);
			tvEventDescription = (TextView)v.findViewById(R.id.tvEventDescription);
			ivEvent = (ImageButton)v.findViewById(R.id.imageEventBtn);
		}catch(Exception e){
			return false;
		}

		Log.d("type:", eventInfo.getType().toString() +":" + eventInfo.getEventImage());
		tvEventDescription.setText(eventInfo.getType().toString());
		Drawable image = (Drawable)Functions.getResource(v, eventInfo.getEventImage(), Constants.DRAWABLE);
		if(image == null){ //set a default image
			ivEvent.setImageResource(R.drawable.ic_launcher);
		}else{
			ivEvent.setImageDrawable(image);
		}
		ivEvent.setContentDescription(eventInfo.getType().toString());
		return true;
	
	}
	
	

}