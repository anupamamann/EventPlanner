package com.android.apps.eventplanner;

import com.android.apps.eventplanner.utils.Constants;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class CategoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		//Note::get the Event ID from the intent
		//pull up the list of event planning categories for this particular event  
		Toast.makeText(CategoryActivity.this, getIntent().getStringExtra(Constants.EVENT_TYPE)+" got clicked", Toast.LENGTH_LONG).show();
	}
}
