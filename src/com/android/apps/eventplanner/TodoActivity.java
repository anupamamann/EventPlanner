package com.android.apps.eventplanner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.apps.eventplanner.models.TodoListItem;

public class TodoActivity extends Activity {

	ListView lvTodos;
	TodoListArrayAdapter adapter;
	List<TodoListItem> todos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		todos = new ArrayList<TodoListItem>();
		todos.add(new TodoListItem("Mona","Chitnis"));
		adapter = new TodoListArrayAdapter(this, todos);
		lvTodos = (ListView) findViewById(R.id.lvTodos);
		lvTodos.setAdapter(adapter);
		lvTodos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(TodoActivity.this, "Clicked " + position,
						Toast.LENGTH_SHORT).show();
				
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
