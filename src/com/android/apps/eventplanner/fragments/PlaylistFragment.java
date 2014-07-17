package com.android.apps.eventplanner.fragments;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.apps.eventplanner.MusicActivity;
import com.android.apps.eventplanner.PlaylistArrayAdapter;
import com.android.apps.eventplanner.R;
import com.android.apps.eventplanner.models.Song;
import com.android.apps.eventplanner.utils.Constants.EventType;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class PlaylistFragment extends Fragment {

	ArrayList<Song> playList= new ArrayList<Song>();
	
	PlaylistArrayAdapter fAdapter;
	ListView lvSongs;
	final MediaPlayer mediaPlayer = new MediaPlayer();
	String user="";
	String playListId="";

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//playList=Song.getSongs();
		
		EventType type = ((MusicActivity) getActivity()).getEventType();
		String searchTerm =type.name().toLowerCase();
		
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		
		
		AsyncHttpClient client = new AsyncHttpClient();
		StringBuilder url =  new StringBuilder();
		url.append("https://api.spotify.com/v1/search?q=" + Uri.encode(searchTerm)+"&type=track&limit=10");
		fAdapter = new PlaylistArrayAdapter(getActivity(), playList);
		
		client.get(url.toString(), 
				new JsonHttpResponseHandler(){
			
			
			@Override
			public void onSuccess(JSONObject response) {
				// TODO Auto-generated method stub
				JSONArray jsonResults =null;
				
				try{
					
					jsonResults=response.getJSONObject("tracks").getJSONArray("items");
					//imageList.clear();
					
					for(Song s: Song.fromJSONArray(jsonResults)){
						
						Log.d("INFO", "title:"+s.getTitle());
						Log.d("INFO", "artist:"+s.getArtist());
						Log.d("INFO", "cover art:"+s.getCoverUrl());
					}
					
					playList=Song.fromJSONArray(jsonResults);
					fAdapter.addAll(playList);
					
					Log.d("INFO", Song.fromJSONArray(jsonResults).toString());
					
					Log.d("INFO", jsonResults.toString());
					
				}
				catch(JSONException e){
					
					e.printStackTrace();
					
				}
				
}
			
			
		});

		
		
		
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_playlist, container, false);
		lvSongs = (ListView)v.findViewById(R.id.lvPlayList);
		lvSongs.setAdapter(fAdapter);
		
		lvSongs.setOnItemClickListener(new  OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Song s = playList.get(position);
				playSong(s.getPreviewUrl());
				//getAuth();	
			//i.putExtra("result", imageResult);
				//startActivity(i);
			}
		});
		
		return v;

	}
	
	
	public void playSong(String url) {
		
		
		// Set type to streaming
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			//mediaPlayer.reset();
		}
		
		// Listen for if the audio file can't be prepared
		mediaPlayer.setOnErrorListener(new OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				// ... react appropriately ...
		        // The MediaPlayer has moved to the Error state, must be reset!
				return false;
			}
		});
		// Attach to when audio file is prepared for playing
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.start();
			}
		});
		// Set the data source to the remote URL
		try {
			mediaPlayer.setDataSource(url);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Trigger an async preparation which will file listener when completed
		mediaPlayer.prepareAsync(); 
	}
	
	
	
	public void addSong(String accessToken){
	
		
		
		  String userid="";
		
		  userid = getUserId(accessToken);
		  
		  try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  Log.d("userinfo", user);
		  
		//  String playId = createEmptyPlaylist(user, accessToken);
		  try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  Log.d("playinfo", playListId);
		     

		 
		
		 
	}
	
	
	public void addTracks(String userid, String playListId, String accessToken){
		
		  String url = "https://api.spotify.com/v1/users/"+userid+ "/playlists/"+playListId+"/tracks"; 
			 AsyncHttpClient client = new AsyncHttpClient();
	         //client.addHeader(arg0, arg1);
	        client.addHeader("Authorization", "Bearer " +accessToken);
	        //String json ="{\"name\":\"A New Playlist\", \"public\":false}"; 
	        
	        JSONArray jsonArray = new JSONArray();
	        //jsonArray.put("spotify:track:4iV5W9uYEdYUVa79Axb7Rh");
	        //jsonArray.put("spotify:track:1301WleyT98MSxVHPZCA6M");
	        
	        for(Song s : playList)
	        {
	        	jsonArray.put(s.getUri());
	        }
	       
	        
	        
	        
	        
	        StringEntity entity=null;
			try {
				entity = new StringEntity(jsonArray.toString(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
	        entity.setContentType("application/json");
	         //client.post(context, url, entity, "application/json", responseHandler);
	        client.post(getActivity().getApplicationContext(), url, entity, "application/json", new JsonHttpResponseHandler(){

				@Override
				public void onFailure(Throwable arg0, JSONObject jsonRes) {
					// TODO Auto-generated method stub
					Log.d("INFO", jsonRes.toString());
					super.onFailure(arg0, jsonRes);
				}

				@Override
				public void onSuccess(JSONObject arg0) {
					// TODO Auto-generated method stub
					Log.d("INFO", "success from adding tracks");
					super.onSuccess(arg0);
				}
	     	   
	     	   
	        });
	         
	      

	}
	
	public String getUserId(final String accessToken)
	{
		
		 String url = "https://api.spotify.com/v1/me";
		
		 AsyncHttpClient client = new AsyncHttpClient();
         //client.addHeader(arg0, arg1);
        client.addHeader("Authorization", "Bearer " +accessToken);
		 
        client.get(url, new JsonHttpResponseHandler(){
        	
        	@Override
			public void onFailure(Throwable arg0, JSONObject jsonRes) {
				// TODO Auto-generated method stub
				Log.d("INFO", arg0.getMessage());
				super.onFailure(arg0, jsonRes);
			}

			@Override
			public void onSuccess(JSONObject arg0) {
				// TODO Auto-generated method stub
				Log.d("INFO", "success");
				
				try {
					user=arg0.getString("id");
					createEmptyPlaylist(user, accessToken);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				super.onSuccess(arg0);
			}

        	
        });
		
        return user;
	}
	
	
	public String createEmptyPlaylist(final String userid, final String accessToken){
		
		final String playListUrl= "https://api.spotify.com/v1/users/"+userid+"/playlists/";
		  JSONObject json = new JSONObject();
		  
	        try {
				json.accumulate("name", userid+" list" + Math.random()*100);
				json.accumulate("public", Boolean.FALSE);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  
	   	 AsyncHttpClient client = new AsyncHttpClient();
         //client.addHeader(arg0, arg1);
        client.addHeader("Authorization", "Bearer " +accessToken);
        //String json ="{\"name\":\"A New Playlist\", \"public\":false}"; 
    
        StringEntity entity=null;
		try {
			entity = new StringEntity(json.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
        entity.setContentType("application/json");
         //client.post(context, url, entity, "application/json", responseHandler);
        client.post(getActivity().getApplicationContext(), playListUrl, entity, "application/json", new JsonHttpResponseHandler(){

			@Override
			public void onFailure(Throwable arg0, JSONObject jsonRes) {
				// TODO Auto-generated method stub
				Log.d("INFO", jsonRes.toString());
				super.onFailure(arg0, jsonRes);
			}

			@Override
			public void onSuccess(JSONObject jsonRes) {
				// TODO Auto-generated method stub
				Log.d("INFO", "success");
				try {
					
					playListId=jsonRes.getString("id");
					addTracks(userid, playListId, accessToken);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.onSuccess(jsonRes);
			}
     	   
     	   
        });
         
      
        return playListId;
		  	 
    
	}
	
	
	
	public ArrayList<Song> getPlaylist() {
		return playList;
	}
	
}
