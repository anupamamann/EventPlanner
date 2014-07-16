package com.android.apps.eventplanner;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ImageResult implements Serializable {
	private static final long serialVersionUID = -1586689875926370629L;
	private String url;
	
	public ImageResult(JSONObject json) {
		try {
			this.url = json.getString("tbUrl");
			Log.i("EVENT", url);
		} catch (Exception e) {
			this.url = null;
		}
	}
	
	public String getUrl() {
		return url;
	}
	
	@Override
	public String toString() {
		return this.url;
	}

	public static ArrayList<ImageResult> fromJSONArray(
			JSONArray array) {
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for (int i = 0; i < array.length(); i++) {
			try {
				results.add(new ImageResult(array.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return results;
	}
	
}
