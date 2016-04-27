/*
* 2016.4.12 Hong Gi Wook
* Assignment #2 –Simple Homework List
* Homework List
*
* Design
* - There are four homework lists
* - Each have icon and title
* - If you click list, start activity about clicked list
*
* */

package com.example.hong.simplehomeworklist;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * Created by hong on 2016-04-11.
 */
public class HomeworkList extends ListActivity { // extends listActivity

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// list icon and title
		String[] homeworks = getResources().getStringArray(R.array.presidents_array);
		Integer[] imageId = {
				R.drawable.image1,
				R.drawable.image2,
				R.drawable.image3,
				R.drawable.image4
		};

		// adapter acts as a bridge between an AdapterView and data
		ListArrayAdapter adapter = new ListArrayAdapter(this, homeworks, imageId);
		setListAdapter(adapter); // fill the entire screen of the activity with a ListView
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// according to clicked list, start explain activity or change password activity
		switch (position) {
			case 0:
				startActivity(new Intent(this, ExplainCal.class));
				break;
			case 1:
				startActivity(new Intent(this, ExplainTip.class));
				break;
			case 2:
				startActivity(new Intent(this, ExplainTime.class));
				break;
			case 3:
				startActivity(new Intent(this, ChangePwd.class));
				break;
			default:
				break;
		}
	}
}
