package com.android.apps.eventplanner;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.apps.eventplanner.models.Venue;
import com.loopj.android.image.SmartImageView;

public class VenueListArrayAdapter extends ArrayAdapter<Venue> {

	private Context context;
	private int selectedPosition = 0;

	private static class ViewHolder {
		TextView tvVenueName;
		TextView tvVenueAddress;
		TextView tvVenueCapacity;
		LinearLayout llVenuePhotos;
		RadioButton radioSelectVenueList;
	}

	public VenueListArrayAdapter(Context context, List<Venue> venues) {
		super(context, R.layout.item_venue_list, venues);
		this.context = context;
	}
	
	public Venue getSelectedPosition() {
		return getItem(selectedPosition);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Venue v = getItem(position);
		ViewHolder viewHolder; // view lookup cache stored in tag

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_venue_list, parent, false);
			viewHolder = new ViewHolder();
			setupViews(viewHolder, convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// Populate the data into the template view using the data object
		viewHolder.tvVenueName.setText(v.getName());
		viewHolder.tvVenueAddress.setText(v.getAddress());
		viewHolder.tvVenueCapacity.setText(v.getCapacity() + "");
		List<String> venuePhotoUrls = v.getPhotos();
		for (String photo : venuePhotoUrls) {
			SmartImageView imageView = new SmartImageView(context);
			LayoutParams attribs = new LayoutParams(150, 150);
			attribs.leftMargin = 5;
			attribs.rightMargin = 5;
			imageView.setLayoutParams(attribs);
			imageView.setImageUrl(photo);
			viewHolder.llVenuePhotos.addView(imageView);
		}
		RadioButton r = viewHolder.radioSelectVenueList;
		r.setChecked(position == selectedPosition);
		r.setTag(position);
		r.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				selectedPosition = (Integer) view.getTag();
				notifyDataSetInvalidated();
			}
		});
		// Return the completed view to render on screen
		return convertView;
	}

	private void setupViews(ViewHolder viewHolder, View convertView) {
		viewHolder.tvVenueName = (TextView) convertView.findViewById(R.id.tvVenueName);
		viewHolder.tvVenueAddress = (TextView) convertView
				.findViewById(R.id.tvVenueAddress);
		viewHolder.tvVenueCapacity = (TextView) convertView
				.findViewById(R.id.tvVenueCapacity);
		viewHolder.llVenuePhotos = (LinearLayout) convertView
				.findViewById(R.id.llVenuePhotos);
		viewHolder.radioSelectVenueList = (RadioButton) convertView
				.findViewById(R.id.radioSelectVenueList);
	}
	
	public void onAddVenueList(View v) {
		Toast.makeText(context, getSelectedPosition().getName(), Toast.LENGTH_SHORT).show();
	}
}
