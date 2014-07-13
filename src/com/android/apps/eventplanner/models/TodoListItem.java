package com.android.apps.eventplanner.models;

import android.widget.ImageView;

public class TodoListItem {
	private Type heading;
	private String preview;
	private ImageView status;
	
	public enum Type {
		FOOD("Food"), VENUE("Venue"), THEME("Theme"), MUSIC("Music");
		
		private String type;
		
		private Type(String t) {
			this.type = t;
		}
	}
	
	public TodoListItem(Type heading, String preview) {
		this.heading = heading;
		this.preview = preview;
	}
	
	public Type getHeading() {
		return heading;
	}
	public String getPreview() {
		return preview;
	}
	public ImageView getStatus() {
		return status;
	}
	
	public void setStatus(ImageView v) {
		this.status = v;
	}
}
