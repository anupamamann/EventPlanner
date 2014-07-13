package com.android.apps.eventplanner.models;

import android.provider.ContactsContract.CommonDataKinds.Email;

public class Guest {
	
	private String name;
	private boolean rsvp;
	private int num;
	private Email email;
	
	public Guest(String name, boolean rsvp, int num, Email email) {
		this.name = name;
		this.rsvp = rsvp;
		this.num = num;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public boolean isRsvp() {
		return rsvp;
	}

	public int getNum() {
		return num;
	}
	
	public Email getEmail() {
		return email;
	}

}
