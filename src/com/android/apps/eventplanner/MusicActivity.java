package com.android.apps.eventplanner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.apps.eventplanner.fragments.PlaylistFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.authentication.SpotifyAuthentication;

public class MusicActivity extends FragmentActivity {

	
	private static final String CLIENT_ID = "65df51ff3c39489ca222f79aab263d61";
    private static final String CLIENT_SECRET  ="14f8bbc79f294295b50a206bc85739fd";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "uniq://callback";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		
	//	SpotifyApi spotify = new SpotifyApi();
		//spotify.getTracks();
					
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.flContainer, new PlaylistFragment());
		ft.commit();
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
           
           PlaylistFragment playFrag = (PlaylistFragment) 
                   getSupportFragmentManager().findFragmentById(R.id.flContainer);
           
           playFrag.addSong(response.getAccessToken());
          
        }
	}
	
	
public void createPlaylist(View v){
	
	SpotifyAuthentication.openAuthWindow(CLIENT_ID, "token", REDIRECT_URI,
            new String[]{"user-read-private", "playlist-modify","playlist-modify-private"}, null, this); 
}
	
	
}	

