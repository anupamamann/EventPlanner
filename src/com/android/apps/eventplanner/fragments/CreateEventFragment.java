package com.android.apps.eventplanner.fragments;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.apps.eventplanner.R;
import com.android.apps.eventplanner.models.Events;
import com.android.apps.eventplanner.utils.Constants.EventType;

public class CreateEventFragment extends DialogFragment{

	private View view;
	EditText etName;
	EditText etDescription;
	TextView tvName;
	TextView tvDescription;
	TextView tvCurrentDate;
	Button btCreate;
	Button btChangeDate;
	Context ctx;

	private int year;
	private int month;
	private int day;

	static final int DATE_DIALOG_ID = 999;

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

		tvCurrentDate = (TextView)view.findViewById(R.id.tvCurrentDate);
		btChangeDate = (Button)view.findViewById(R.id.btnChangeDate);

		tvDescription = (TextView)view.findViewById(R.id.tvDecription);
		tvName = (TextView)view.findViewById(R.id.tvName);

		btCreate = (Button)view.findViewById(R.id.btSave);
		btCreate.setClickable(false);

		setCurrentDateOnView();
		addListenerOnButton();


		btCreate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//getTitle

				String eventName = etName.getText().toString();
				String eventDescription = etDescription.getText().toString();
				String dateString = tvCurrentDate.getText().toString();
				Date date = new Date();
				try {
					date = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(dateString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Events event  = Events.getInstance();
				if(event == null){
					event = Events.createEvent();
				}

				event.setEventDescription(eventDescription);
				event.setName(eventName);
				event.setType(EventType.BIRTHDAY);
				event.setDate(date);

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

	//display current date 
	public void setCurrentDateOnView() {

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// set current date into textview
		tvCurrentDate.setText(new StringBuilder()
		// Month is 0 based, just add 1
		.append(month + 1).append("-").append(day).append("-")
		.append(year).append(" "));

		// set current date into datepicker
		//dpResult.init(year, month, day, null);

	}

	public void addListenerOnButton() {



		btChangeDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment();
			    newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
//			    onCreateDialog(123);
			}

		});

	}
	
	/*@Override
	protected Dialog onCreateDialog(int id) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }*/

	
	/*public DatePickerDialog.OnDateSetListener datePickerListener 
	= new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			tvCurrentDate.setText(new StringBuilder().append(month + 1)
					.append("-").append(day).append("-").append(year)
					.append(" "));

			// set selected date into datepicker also
			//dpResult.init(year, month, day, null);

		}
	};*/

	
	
    public void onDateSet(String date) {
        // Do something with the date chosen by the user
    	
  
		// set selected date into textview
    	tvCurrentDate.setText(date);
    }
}
