package com.android.apps.eventplanner;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.apps.eventplanner.models.Song;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class PlaylistArrayAdapter extends ArrayAdapter<Song> {
	
	ImageView ivCover;
	TextView tvSongTitle; 
	TextView tvAlbum;
	ImageLoader imageLoader;
	Button btCreate;
	String accessToken;
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
		View v;
		if(convertView == null){
			v  = LayoutInflater.from(getContext()).inflate(R.layout.playlist_item, parent,false);
		}else{
			v = convertView;
		}
		
		setUpViews(v, position);
			
		return v;
	}

	public PlaylistArrayAdapter(Context context, ArrayList<Song> playList) {
	super(context, R.layout.playlist_item, playList);
	
		 imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
		// TODO Auto-generated constructor stub
	}

	
	public boolean setUpViews(View v, int position){
		
		Song item;
		
		try{
			item = (Song) this.getItem(position);
			tvAlbum = (TextView)v.findViewById(R.id.tvAlbum);
			tvSongTitle = (TextView)v.findViewById(R.id.tvSongTitle);
			ivCover = (ImageView)v.findViewById(R.id.ivCover);
			btCreate = (Button) v.findViewById(R.id.btCreate);
			//ibAddTrack= (ImageButton)v.findViewById(R.id.ibAddTrack);
		}catch(Exception e){
			return false;
		}

		tvAlbum.setText(item.getAlbum());
		tvSongTitle.setText(item.getTitle());
		ivCover.setImageResource(android.R.color.transparent);
		
		 
	     imageLoader.displayImage(item.getCoverUrl(), ivCover);
	       
		//Drawable image = (Drawable)Functions.getResource(v, item.getImage(), Constants.DRAWABLE);
		//if(image == null){ //set a default image
			ivCover.setImageResource(R.drawable.ic_launcher);
		//}else{
			//ivFood.setImageDrawable(image);
		//}
		
		return true;
	}
	
	
	
		
}
	
	
	

