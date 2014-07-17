package com.android.apps.eventplanner;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.Toast;

import com.android.apps.eventplanner.fragments.CreateEventFragment;
import com.android.apps.eventplanner.fragments.CreateEventFragment.CreateEventListener;
import com.android.apps.eventplanner.fragments.DatePickerFragment.DateChangeListner;
import com.android.apps.eventplanner.fragments.PlaylistFragment;
import com.android.apps.eventplanner.models.Events;
import com.android.apps.eventplanner.models.Song;
import com.android.apps.eventplanner.utils.Constants;
import com.android.apps.eventplanner.utils.Constants.EventType;
import com.loopj.android.http.AsyncHttpClient;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.authentication.SpotifyAuthentication;

public class MusicActivity extends FragmentActivity implements CreateEventListener, DateChangeListner {

	
	private static final String CLIENT_ID = "65df51ff3c39489ca222f79aab263d61";
    private static final String CLIENT_SECRET  ="14f8bbc79f294295b50a206bc85739fd";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "uniq://callback";
    FragmentManager fm;
    Menu menu;
    MenuItem miSummary;
    CreateEventFragment createFragment;
    EventType eventType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		
	//	SpotifyApi spotify = new SpotifyApi();
		//spotify.getTracks();
					
		fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.flContainer, new PlaylistFragment());
		ft.commit();
		
		// getIntent Object
		String typeFromIntent = getIntent()
				.getStringExtra(Constants.EVENT_TYPE);
		if (typeFromIntent != null)
			eventType = EventType.valueOf(typeFromIntent.toUpperCase());
		else
			eventType = Events.getInstance().getType();
		Log.i("EVENT MUSIC", eventType.name());
	}
	
	
@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		
		Toast.makeText(MusicActivity.this, "newIntent", Toast.LENGTH_LONG).show();
        super.onNewIntent(intent);
        Uri uri = intent.getData();
            
        if (uri != null) {
            AuthenticationResponse response = SpotifyAuthentication.parseOauthResponse(uri);
            Log.d("INFO",response.getAccessToken());
           
            AsyncHttpClient client = new AsyncHttpClient();
                 client.addHeader("Authorization", "Bearer " +response.getAccessToken() );
           
            PlaylistFragment playFrag = (PlaylistFragment) fm.findFragmentById(R.id.flContainer);
           
            playFrag.addSong(response.getAccessToken());
          
        }
	}
	
	
public void createPlaylist(View v){
	
	SpotifyAuthentication.openAuthWindow(CLIENT_ID, "token", REDIRECT_URI,
            new String[]{"user-read-private", "playlist-modify","playlist-modify-private"}, null, this); 
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	this.menu = menu;
	// Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_univ, menu);
    return true;
}

@Override
public boolean onPrepareOptionsMenu(Menu menu) {
    if(Events.getInstance() != null && miSummary == null) {
    	alterMenu();
    }
    return true;
}

	public void onSave(MenuItem mi) {
		PlaylistFragment playFrag = (PlaylistFragment) fm.findFragmentById(R.id.flContainer);
		ArrayList<Song> songs = playFrag.getPlaylist();
		//simple implementation
		/*Intent i = new Intent(this, TodoActivity.class);
		Events.getInstance().setMusic(songs);
		//i.putExtra("music", songs.get(0).getTitle() + ","
		//		+ songs.get(1).getTitle() + "," + songs.get(2).getTitle());
		startActivity(i);*/
		
		//better implementation
		//save object into current event
				Events currentEvent = Events.getInstance();
				if(currentEvent == null){
					//open dialog to notify and create event
					openCreateNewEventDialog();
					alterMenu();
				} else {
					currentEvent.setMusic(songs);
					showEventCreatedDialog("Item saved to " + currentEvent.getName());
				}
				Toast.makeText(MusicActivity.this,
						"Added " + songs.get(0).getTitle() + " etc to event", Toast.LENGTH_LONG)
						.show();
				currentEvent.setType(eventType);
	}
	

	public void onCreateNewEvent(MenuItem mi) {
		openCreateNewEventDialog();
	}
	
	public void showEventCreatedDialog(String message){
		AlertDialog.Builder alertNotification = new AlertDialog.Builder(this);
		alertNotification.setTitle(message);
		alertNotification.setCancelable(true);
		alertNotification.setPositiveButton("ok", new DialogInterface.OnClickListener(){
	            public void onClick(DialogInterface dialog, int which) {
	            	dialog.dismiss();
	            }

	        });
		alertNotification.create();
	     alertNotification.show();
	}
	
	private void openCreateNewEventDialog() {
		createFragment = CreateEventFragment.newInstance("New Event");
		createFragment.show(fm, "compose_dialog");
	}

	@Override
	public void onEventCreate(String eventName) {
		Toast.makeText(this, "New event:" + eventName + " created!", Toast.LENGTH_SHORT).show();
	}

	
	private void alterMenu() {
		 menu.removeItem(R.id.miCreateNewEvent);
	     miSummary = menu.add("Summary");
	     miSummary.setIcon(R.drawable.ic_saved_events);
	     miSummary.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	     miSummary.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent i = new Intent(MusicActivity.this, TodoActivity.class);
				startActivity(i);
				return true;
			}
	    	 
	     });
	}

	@Override
	public void onDateSelected(String text) {
		//CreateEventFragment frag  = (CreateEventFragment) 
        //        getSupportFragmentManager().findFragmentById(R.id.flContainer);
		createFragment.onDateSet(text);
		
	}
	
	public EventType getEventType() {
		return eventType;
	}
	
}	

