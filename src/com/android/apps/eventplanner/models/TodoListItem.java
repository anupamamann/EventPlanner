package com.android.apps.eventplanner.models;

import com.android.apps.eventplanner.R;

public class TodoListItem {
	
	
	private Type heading;
	private String preview;
	private int icon;
	
	public enum Type {
		FOOD("Food"), VENUE("Venue"), THEME("Theme"), MUSIC("Music");
		
		private String type;
		
		private Type(String t) {
			this.type = t;
		}
	}
	
	public TodoListItem(Type heading, String preview) {
		this.heading = heading;
		switch (heading) {
		case FOOD:
			this.icon = R.drawable.ic_todo_food;
			break;
		case VENUE:
			this.icon = R.drawable.ic_todo_venue;
			break;
		case THEME:
			this.icon = R.drawable.ic_todo_theme;
			break;
		case MUSIC:
			this.icon = R.drawable.ic_todo_music;
		}
		this.preview = preview;
	}
	
	public Type getHeading() {
		return heading;
	}
	public String getPreview() {
		return preview;
	}
	public int getIcon() {
		return icon;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
}
