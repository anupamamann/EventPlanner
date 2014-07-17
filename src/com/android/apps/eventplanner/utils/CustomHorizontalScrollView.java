package com.android.apps.eventplanner.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import com.android.apps.eventplanner.ImageResultsArrayAdapter;

public class CustomHorizontalScrollView extends HorizontalScrollView {

	public CustomHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setSmoothScrollingEnabled(true);
	}

	public void setAdapter(Context context, ImageResultsArrayAdapter mAdapter) {
		try {
			fillViewWithAdapter(mAdapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fillViewWithAdapter(ImageResultsArrayAdapter mAdapter) {
		ViewGroup parent = (ViewGroup) getChildAt(0);

		parent.removeAllViews();

		for (int i = 0; i < mAdapter.getCount(); i++) {
			parent.addView(mAdapter.getView(i, null, parent));
		}
	}

}
