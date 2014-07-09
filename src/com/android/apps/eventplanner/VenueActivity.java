package com.android.apps.eventplanner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.myandroid.apps.eventplanner.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class VenueActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venue);
		//if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS)
			//Log.w("MONA", "SUCCESS");
		//else if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == ConnectionResult.SERVICE_MISSING)
			//Log.w("MONA", "MISSING");
	}
}
