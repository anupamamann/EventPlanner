package com.android.apps.eventplanner.models;

import android.widget.ImageView;

public class TodoListItem {
	private String heading;
	private String preview;
	private ImageView status;
	
	public TodoListItem(String heading, String preview) {
		this.heading = heading;
		this.preview = preview;
	}
	
	public String getHeading() {
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
