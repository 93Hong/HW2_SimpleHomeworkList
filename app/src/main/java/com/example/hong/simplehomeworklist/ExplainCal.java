/*
* 2016.4.12 Hong Gi Wook
* Assignment #2 –Simple Homework List
* Explain mini calculator
*
* Design
* - Explain mini calculator
* - If button clicked, start mini calculator activity
* - Using startActivityForResult, results returned by activity could be picked up by listener
*   then show the result using toast message
*
* */

package com.example.hong.simplehomeworklist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hong on 2016-04-12.
 */
public class ExplainCal extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.explain_calculator);
	}

	// if button clicked, start mini calculator activity
	public void onButtonClicked(View v) {
		Bundle bundle = new Bundle(); // type‐safe collection of <name, value> pairs
		Intent intent = new Intent(this, SimpleCalculator.class);

		intent.putExtras(bundle); // put bundle into intent
		// to get results back from the called activity
		startActivityForResult(intent, 101); // requestCode 101
	}

	// results returned by activity could be picked up by listener
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// use requestCode to find out who is talking back to us
		try {
			if ((requestCode == 101 ) && (resultCode == Activity.RESULT_OK)) {

				Bundle myResults = data.getExtras(); // look into the bundle sent to activity for data items
				String s = myResults.getString("result"); // get a String value in the bundle
				Toast.makeText(getApplicationContext(), "Result : " + s, Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Problems ‐ "+ requestCode +" "+ resultCode, Toast.LENGTH_SHORT).show();
		}
	}
}
