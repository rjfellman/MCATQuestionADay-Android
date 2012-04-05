package com.mcatquestion.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	
	private Context mContext;
	
	public ImageAdapter (Context c){
		mContext = c;
	}
	
	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85,85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8,8,8,8);
			System.out.println("IF");
		} else {
			imageView = (ImageView) convertView;
			System.out.println("ELSE");
		}
		
		imageView.setImageResource(mThumbIds[position]);
		return imageView;
	}
	
	private Integer[] mThumbIds = {
			R.drawable.todayq, R.drawable.todayq,
			R.drawable.todayq, R.drawable.todayq,
			R.drawable.todayq, R.drawable.todayq
	};

}
