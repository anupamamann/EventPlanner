package com.android.apps.eventplanner.utils;

import android.content.res.Resources;
import android.view.View;

public class Functions {
	
	/**
	 * 
	 * @param v view object 
	 * @param key name of the desired resource
	 * @param type type of the desired resource e.g strings, drawable
	 * @return resource object, cast the resource into desired type  
	 */
	public static Object getResource(View v, String key, String type){
		Resources resource = v.getContext().getResources();
		int imageId = resource.getIdentifier(key, type, v.getContext().getPackageName());
		if(imageId == 0)
			return null;
		if(type.equalsIgnoreCase(Constants.DRAWABLE))
			return resource.getDrawable(imageId);
		else if(type.equalsIgnoreCase(Constants.Strings))
			return resource.getString(imageId);
		else
			return resource.getString(imageId);
		}
		
	}


