package com.android.apps.eventplanner;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class VenuePhotosDialog extends DialogFragment {
	
	ListView lvVenuePics;
	AsyncHttpClient client;
	ImageResultsArrayAdapter imageAdapter;
	List<ImageResult> images;
	
	public VenuePhotosDialog() {
	}

	public static VenuePhotosDialog newInstance(String title) {
		VenuePhotosDialog frag = new VenuePhotosDialog();
		Bundle args = new Bundle();
		args.putString("title", title);
		frag.setArguments(args);
		return frag;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		client = new AsyncHttpClient();
		images = new ArrayList<ImageResult>();
		imageAdapter = new ImageResultsArrayAdapter(getActivity(), images);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.dialog_venue_photos, container);
		String title = getArguments().getString("title", "Venue Pics");
		getDialog().setTitle(title);
		
		lvVenuePics = (ListView) v.findViewById(R.id.lvVenuePics);
		lvVenuePics.setAdapter(imageAdapter);
		
		getImages();
		
		this.set
		return v;
	}
	
	private void getImages() {
		String url = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&"
				+ "&v=1.0&q=" + Uri.encode("Birthday hall");
		
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
	
}
