package com.android.apps.eventplanner.fragments;


import com.android.apps.eventplanner.R;
import com.android.apps.eventplanner.models.Events;
import com.android.apps.eventplanner.utils.Constants.EventType;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreateEventFragment extends DialogFragment {
	
	private View view;
	EditText etName;
	EditText etDescription;
	TextView tvName;
	TextView tvDescription;
	Button btCreate;
	
	public interface CreateEventListener {
		void onEventCreate(String text);
	}
	
	public CreateEventFragment(){
		
	}
	
	public static CreateEventFragment newInstance(String title){
		CreateEventFragment comoseFrag = new CreateEventFragment();
		Bundle args = new Bundle();
		args.putString("title", title);
		comoseFrag.setArguments(args);
		return comoseFrag;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.fragment_create_activity, container);
		String title = getArguments().getString("title", "New Event ");
		getDialog().setTitle(title);

		etDescription = (EditText)view.findViewById(R.id.etDescription);
		etName = (EditText)view.findViewById(R.id.etName);
		tvDescription = (TextView)view.findViewById(R.id.tvDecription);
		tvName = (TextView)view.findViewById(R.id.tvName);
		btCreate = (Button)view.findViewById(R.id.btSave);
		btCreate.setClickable(false);
		
		btCreate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//getTitle
				
				String eventName = etName.getText().toString();
				String eventDescription = etDescription.getText().toString();
				
				Events event  = Events.getInstance();
				if(event == null){
					event = Events.createEvent();
				}
				
				event.setEventDescription(eventDescription);
				event.setName(eventName);
				event.setType(EventType.BIRTHDAY);
				
				CreateEventListener listener = (CreateEventListener) getActivity();
				listener.onEventCreate(eventName);
				
				dismiss();
			}
		});

		etName.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//length 
				if (s.length()>0){
					btCreate.setClickable(true);
					//btCreate.setVisibility(View.VISIBLE);
				}
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		return view;

	}

}
