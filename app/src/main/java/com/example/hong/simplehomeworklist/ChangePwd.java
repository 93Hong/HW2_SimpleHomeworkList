/*
* 2016.4.12 Hong Gi Wook
* Assignment #2 –Simple Homework List
* Change Passwords
*
* Design
* - There are two buttons
* - First one is 'ok'. If clicked, check password and save (Exception handling)
* - Next 'cancel'. If clicked, finish activity
*
* */


package com.example.hong.simplehomeworklist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hong on 2016-04-12.
 */
// Implement event Listener of buttons using implements interface on the Activity
public class ChangePwd extends AppCompatActivity implements View.OnClickListener{
	EditText text;
	// create a reference to the shared preferences object
	SharedPreferences sh_Pref;
	// obtain an editor to add data to my SharedPreferences object
	SharedPreferences.Editor toEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changepwd_layout);

		text = (EditText)findViewById(R.id.pas);
		findViewById(R.id.okButton).setOnClickListener(this);
		findViewById(R.id.cancelButton).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) { // about all onClick events
		switch (v.getId()) {
			case R.id.okButton : // ok button -> save password using shared preferences
				String newPwd = text.getText().toString();
				if (newPwd.length() == 4 && isNumeric(newPwd)) // 4 digit and numeric
					savePass(newPwd);
				else
					Toast.makeText(getApplicationContext(), "Exception : Weird password", Toast.LENGTH_SHORT).show();
				finish();
				break;
			case R.id.cancelButton :
				Toast.makeText(getApplicationContext(), "Cancel button clicked", Toast.LENGTH_SHORT).show();
				finish();
				break;
			default:
				break;
		}
	}

	// save password in sharedPreferences container
	public void savePass(String str) {
		sh_Pref = getSharedPreferences("Login Check", MODE_PRIVATE);
		toEdit = sh_Pref.edit();
		toEdit.putString("Password", str); // set a String value in the preferences editor
		toEdit.commit(); // commit your preferences changes
	}

	// check input password whether numeric or not
	public boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
