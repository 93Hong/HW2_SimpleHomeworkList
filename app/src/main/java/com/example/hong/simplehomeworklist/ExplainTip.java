/*
* 2016.4.12 Hong Gi Wook
* Assignment #2 –Simple Homework List
* Explain tip calculator
*
* Design
* - Explain tip calculator
* - If button clicked, start tip calculator activity
* */

package com.example.hong.simplehomeworklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by hong on 2016-04-12.
 */
public class ExplainTip extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.explain_tipcalculator);
	}

	// if button clicked, start tip calculator activity
	public void onButtonClicked(View v) {
		startActivity(new Intent(this, TipCalculator.class));
	}
}
