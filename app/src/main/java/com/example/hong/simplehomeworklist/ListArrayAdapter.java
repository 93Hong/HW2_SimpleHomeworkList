/*
* 2016.4.12 Hong Gi Wook
* Assignment #2 –Simple Homework List
* ListArrayAdapter
*
* This is adapter class which acts as a bridge between an AdapterView and data
*
* */

package com.example.hong.simplehomeworklist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hong on 2016-04-12.
 */
public class ListArrayAdapter extends ArrayAdapter<String> { // extends arrayAdapter
	private final Activity context;
	private final String[] homeworks;
	private final Integer[] imageId;

	// constructor
	public ListArrayAdapter(Activity context, String[] homeworks, Integer[] imageId) {
		super(context, R.layout.rowlayout, homeworks);

		this.context = context;
		this.homeworks = homeworks;
		this.imageId = imageId;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// instantiates a layout XML file into its corresponding View objects.
		LayoutInflater inflater = context.getLayoutInflater();

		View rowView= inflater.inflate(R.layout.rowlayout, null, true); // use custom layout
		TextView txtTitle = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

		txtTitle.setText(homeworks[position]); // set list title
		imageView.setImageResource(imageId[position]); // set list image

		return rowView;
	}
}