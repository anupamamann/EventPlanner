package com.android.apps.eventplanner.utils;

import android.util.Log;

import com.android.apps.eventplanner.models.TodoListItem.Type;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class GoogleClient {
	public static final String REST_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
	public static final String API_KEY= "AIzaSyANnzTDqLc5kmY5q-CeH6ofpCqwJLrLWpc";
	private AsyncHttpClient client;
	
	public GoogleClient() {
		client = new AsyncHttpClient();
	}
			
	public void getPlaces(Type type, String keyword, double lat, double lng, int radius,
			AsyncHttpResponseHandler handler) {
    	RequestParams params = new RequestParams();
    	params.put("key", API_KEY);
    	params.put("location", lat + "," + lng);
    	params.put("radius", radius + "");
    	params.put("types", type.name().toLowerCase());
    	params.put("keyword", keyword);
    	Log.w("EVENT", params.toString());
    	client.get(REST_URL, params, handler); //pass null for params if no params
    }

}