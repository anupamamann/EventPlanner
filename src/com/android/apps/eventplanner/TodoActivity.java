package com.android.apps.eventplanner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.apps.eventplanner.models.EventRecommendation;
import com.android.apps.eventplanner.models.TodoListItem;
import com.android.apps.eventplanner.utils.Constants.EventType;

public class TodoActivity extends Activity {

	ListView lvTodos;
	TodoListArrayAdapter adapter;
	List<TodoListItem> todos;
	EventType eventType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		todos = new ArrayList<TodoListItem>();
		
		//TODO get type from CategoryActivity and switch-case ladder
		//example birthday
		eventType = EventType.BIRTHDAY;  
		todos.add(new TodoListItem(TodoListItem.Type.FOOD,"Chilli Chicken"));
		todos.add(new TodoListItem(TodoListItem.Type.VENUE,"Lolapalooza"));
		todos.add(new TodoListItem(TodoListItem.Type.THEME,"Tinkerbell"));
		todos.add(new TodoListItem(TodoListItem.Type.MUSIC,"Lady Gaga"));
		adapter = new TodoListArrayAdapter(this, todos);
		lvTodos = (ListView) findViewById(R.id.lvTodos);
		lvTodos.setAdapter(adapter);
		lvTodos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TodoListItem listItem = adapter.getItem(position);
				Toast.makeText(TodoActivity.this, "Clicked " + listItem.getHeading(),
						Toast.LENGTH_SHORT).show();
				Intent i = null;
				switch (listItem.getHeading()) {
					case FOOD:
						i = new Intent(TodoActivity.this, EventRecomendationActivity.class); // temp
						break;
					case VENUE:
						i = new Intent(TodoActivity.this, VenueActivity.class);
						break;
					case THEME:
						i = new Intent(TodoActivity.this, ItemDetailActivity.class); // temp
						break;
					case MUSIC:
						i = new Intent(TodoActivity.this, ItemDetailActivity.class); // temp
				}
				startActivityForResult(i, 50);
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_univ, menu);
        return true;
	}
	
	public void onSavedEvents(MenuItem mi) {
		Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show();
	}
	
	public void onCreateNewEvent(MenuItem mi) {
		Toast.makeText(this, "NEW", Toast.LENGTH_SHORT).show();
		
	}
}
