package com.android.apps.eventplanner;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.apps.eventplanner.fragments.CreateEventFragment;
import com.android.apps.eventplanner.fragments.DatePickerFragment.DateChangeListner;
import com.android.apps.eventplanner.models.Events;
import com.android.apps.eventplanner.models.TodoListItem;
import com.android.apps.eventplanner.utils.Constants;
import com.android.apps.eventplanner.utils.Constants.EventType;

public class TodoActivity extends FragmentActivity implements DateChangeListner {

	ListView lvTodos;
	TodoListArrayAdapter adapter;
	List<TodoListItem> todos;
	TodoListItem selectedFood;
	TodoListItem selectedVenue;
	TodoListItem selectedMusic;
	Events eventInstance;
	CreateEventFragment createFragment;
	EventType eventType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		todos = new ArrayList<TodoListItem>();
		eventInstance = Events.getInstance();
		
		Intent i = getIntent();
		if (i.hasExtra(Constants.EVENT_TYPE)) {
			String eventTypeStr = i.getStringExtra(Constants.EVENT_TYPE);
			eventType = EventType.valueOf(eventTypeStr.toUpperCase());
		}
				
		selectedFood = new TodoListItem(TodoListItem.Type.FOOD,"");
		if (eventInstance != null && eventInstance.getFood() != null)
			selectedFood.setPreview(eventInstance.getFood().getFoods().get(0).toString());
		todos.add(selectedFood);
		selectedMusic = new TodoListItem(TodoListItem.Type.MUSIC,"");
		if (eventInstance != null && eventInstance.getMusic() != null)
			selectedMusic.setPreview(eventInstance.getMusic().get(0).toString());
		todos.add(selectedMusic);
		selectedVenue = new TodoListItem(TodoListItem.Type.VENUE,"");
		if (eventInstance != null && eventInstance.getVenue() != null)
			selectedVenue.setPreview(eventInstance.getVenue().getName());
		todos.add(selectedVenue);
		todos.add(new TodoListItem(TodoListItem.Type.THEME,""));
		adapter = new TodoListArrayAdapter(this, todos);
		lvTodos = (ListView) findViewById(R.id.lvTodos);
		lvTodos.setAdapter(adapter);
		lvTodos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TodoListItem listItem = adapter.getItem(position);
				Intent i = null;
				switch (listItem.getHeading()) {
					case FOOD:
						i = new Intent(TodoActivity.this, ItemDetailActivity.class);
						break;
					case VENUE:
						i = new Intent(TodoActivity.this, VenueActivity.class);
						break;
					case MUSIC:
						i = new Intent(TodoActivity.this, MusicActivity.class);
						break;
					default:
					Toast.makeText(TodoActivity.this, "Under construction",
							Toast.LENGTH_SHORT).show();
				}
				if (eventType != null)
					i.putExtra(Constants.EVENT_TYPE, eventType.name());
				startActivity(i);
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_univ, menu);
        return true;
	}
	
	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(Events.getInstance() != null) {
            menu.removeItem(R.id.miCreateNewEvent);
        }
        return true;
    }
	
	public void onSave(MenuItem mi) {
		//do nothing
	}
	
	public void onCreateNewEvent(MenuItem mi) {
		openCreateNewEventDialog();
	}
	
	private void openCreateNewEventDialog() {
		createFragment = CreateEventFragment.newInstance("New Event");
		createFragment.show(this.getSupportFragmentManager(), "compose_dialog");
	}

	@Override
	public void onDateSelected(String text) {
		createFragment.onDateSet(text);
	}

}
