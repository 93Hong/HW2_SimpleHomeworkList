/*
* 2016.4.12 Hong Gi Wook
* Assignment #2 –Simple Homework List
* Explain time table
*
* Design
* - Explain time table
* - If button clicked, start time table activity
* */

package com.example.hong.simplehomeworklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by hong on 2016-04-12.
 */
public class ExplainTime extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.explain_timetable);
	}

	// if button clicked, start time table activity
	public void onButtonClicked(View v) {
		startActivity(new Intent(this, TimeTable.class));
	}
}
