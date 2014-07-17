package com.android.apps.eventplanner.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Song {
	
	
	private String title;
	private String album;
	private String artist;
	private String previewUrl;
	private String coverUrl;
	private String uri;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	
	
   
	
	public static Song fromJSON(JSONObject jsonObject){
		Song song = new Song();
		
		
		try{
			song.title = jsonObject.getString("name");
			song.artist=jsonObject.getJSONArray("artists").getJSONObject(0).getString("name");
			song.album=jsonObject.getJSONObject("album").getString("name");
			song.previewUrl= jsonObject.getString("preview_url");
			
			JSONArray imageArray =jsonObject.getJSONObject("album").getJSONArray("images");
			song.coverUrl=imageArray.getJSONObject(imageArray.length()-1).getString("url");
			song.setUri(jsonObject.getString("uri"));
			//song.artist = jsonObject.getString("artist");
			
		}
		catch (JSONException e){
			e.printStackTrace();
			return null;
		}
		
		return song;
	}

	public static ArrayList<Song> fromJSONArray(JSONArray jsonArray)
	{
		 ArrayList<Song> songs = new ArrayList<Song>(jsonArray.length());
		for (int i=0; i < jsonArray.length(); i++) {
	          JSONObject songJson = null;
	          try {
	          	songJson = jsonArray.getJSONObject(i);
	          } catch (Exception e) {
	              e.printStackTrace();
	              continue;
	          }

	          Song song = Song.fromJSON(songJson);
	          if (song != null) {
	          	songs.add(song);
	          }
	      }

		return songs;
	}
	public String getPreviewUrl() {
		return previewUrl;
	}
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

}
