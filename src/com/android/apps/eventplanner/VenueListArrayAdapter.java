package com.android.apps.eventplanner;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.android.apps.eventplanner.models.Venue;
import com.loopj.android.image.SmartImageView;

public class VenueListArrayAdapter extends ArrayAdapter<Venue> {

	private static class ViewHolder {
		TextView tvVenueAddress;
		TextView tvVenueCapacity;
		LinearLayout llVenuePhotos;
	}

	public VenueListArrayAdapter(Context context, List<Venue> venues) {
		super(context, R.layout.item_venue_list, venues);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Venue v = getItem(position);
		ViewHolder viewHolder; // view lookup cache stored in tag

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.item_venue_list, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.tvVenueAddress = (TextView) convertView
					.findViewById(R.id.tvVenueAddress);
			viewHolder.tvVenueCapacity = (TextView) convertView
					.findViewById(R.id.tvVenueCapacity);
			convertView.setTag(viewHolder);

			viewHolder.llVenuePhotos = (LinearLayout) convertView
					.findViewById(R.id.llVenuePhotos);
			List<String> venuePhotoUrls = v.getPhotos();
			for (String photo : venuePhotoUrls) {
				SmartImageView imageView = new SmartImageView(getContext());
				LayoutParams attribs = new LayoutParams(150, 150);
				attribs.leftMargin = 5;
				attribs.rightMargin = 5;
				imageView.setLayoutParams(attribs);
				imageView.setImageUrl(photo);
				viewHolder.llVenuePhotos.addView(imageView);
			}
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// Populate the data into the template view using the data object
		viewHolder.tvVenueAddress.setText(v.getAddress());
		viewHolder.tvVenueCapacity.setText(v.getCapacity() + "");
		// Return the completed view to render on screen
		return convertView;
	}
}
