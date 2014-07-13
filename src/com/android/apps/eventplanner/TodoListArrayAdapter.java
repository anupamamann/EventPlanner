package com.android.apps.eventplanner;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.apps.eventplanner.models.TodoListItem;

public class TodoListArrayAdapter extends ArrayAdapter<TodoListItem> {
	private TextView tvTodoHeading;
	private TextView tvTodoPreview;
	private FrameLayout flStatus;
	
	public TodoListArrayAdapter(Context context, List<TodoListItem> todos) {
		super(context, R.layout.item_todo, todos);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TodoListItem t = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
		}
		setupViews(convertView);
		tvTodoHeading.setText(t.getHeading().name());
		tvTodoPreview.setText(t.getPreview());
		//flStatus.setBackground(R.drawable.ic_icon);
		return convertView;
	}
	
	private void setupViews(View v) {
		tvTodoHeading = (TextView) v.findViewById(R.id.tvTodoHeading);
		tvTodoPreview = (TextView) v.findViewById(R.id.tvTodoPreview);
		flStatus = (FrameLayout) v.findViewById(R.id.flStatus);
	}
}
