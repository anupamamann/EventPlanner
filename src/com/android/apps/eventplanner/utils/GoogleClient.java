package com.android.apps.eventplanner.utils;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.util.Log;

import com.android.apps.eventplanner.ImageResult;
import com.android.apps.eventplanner.ImageResultsArrayAdapter;
import com.android.apps.eventplanner.models.TodoListItem.Type;
import com.android.apps.eventplanner.utils.Constants.EventType;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
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
	
	public void getImages(final ImageResultsArrayAdapter imageAdapter, EventType query) {
		String url = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&"
				+ "&v=1.0&q=" + Uri.encode(query.name().toLowerCase() + " hall");
		
		client.get(url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				JSONArray imageJsonResults = null;
				imageAdapter.clear();
				try {
					imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
					imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable t, JSONObject response) {
				Log.e("EVENT", "Failure in Json Image results");
			}
		});
	}
	
	public void getImages(final List<String> imgUrls, EventType query) {
		String url = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&"
				+ "&v=1.0&q=" + Uri.encode(query.name().toLowerCase() + " hall");

		if (imgUrls == null)
		return;
			
		client.get(url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				JSONArray imageJsonResults = null;
				try {
					imageJsonResults = response.getJSONObject("responseData")
							.getJSONArray("results");
					for (int i = 0; i < imageJsonResults.length(); i++) {
						String url = imageJsonResults.getJSONObject(i).getString("url");
						imgUrls.add(url);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable t, JSONObject response) {
				Log.e("EVENT", "Failure in Json Image results");
			}
		});
	}
	

}