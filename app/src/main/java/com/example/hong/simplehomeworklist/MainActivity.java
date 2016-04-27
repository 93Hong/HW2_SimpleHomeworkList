/*
* 2016.4.12 Hong Gi Wook
* Assignment #2 –Simple Homework List
*
* 10 java file
* (main, changePassword, homeworkList, arrayAdapter, explainActivity * 3, homework * 3)
*
* 9 xml file
* (main, changePassword, homeworkList, explainActivity * 3, homework * 3)
* activity_main(land) for change in the screen orientation
*
* Design
* - At first, there are 4 editText for input(password - 4 digit)
* - No need to change focus(using mouse) because of automatically focus change (Using clearFocus, requestFocus)
* - Each editText require one digit password (Using textWatcher and length()==1)
* - There is no button. So process should start automatically without explicitly pressing a button
* - The characters for password box should be hidden with asterisk(*) characters
* - If password correct, automatically start activity HomeworkList.class which represents list of assignment before
* - If not, toast "Wrong Password" message
* - Use SharedPreferences
* 	If there was a saved password, bring it and renew
* 	So compare user input password and renewed password
* 	If not, compare 0000 (initial password) and user input password
*
* */

package com.example.hong.simplehomeworklist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	// variable of widget
	EditText editText1, editText2, editText3, editText4;
	SharedPreferences sh_Pref;
	String password;
	StringBuilder sb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		applySharedPreferences(); // create a references for shared preferences object
		enterPassword(); // add TextChangedListener to 4 editText
	}

	@Override
	protected void onResume() {
		super.onResume();
		applySharedPreferences(); // create a references for shared preferences object
	}

	// initialize variables
	private void init() {
		sb = new StringBuilder();
		editText1 = (EditText)findViewById(R.id.pas1);
		editText2 = (EditText)findViewById(R.id.pas2);
		editText3 = (EditText)findViewById(R.id.pas3);
		editText4 = (EditText)findViewById(R.id.pas4);
	}

	public void applySharedPreferences() {
		sh_Pref = getSharedPreferences("Login Check", MODE_PRIVATE);
		// If there was a saved password, bring it and renew
		if (sh_Pref != null && sh_Pref.contains("Password"))
			password = sh_Pref.getString("Password", "0000");
	}

	// compare user input password and saved password
	// if password correct, automatically start activity HomeworkList.class which represents list of assignment before
	public void checkPassword() {
		if (sb.toString().equals(password)) { // correct password
			startActivity(new Intent(this, HomeworkList.class));
			sb.setLength(0); // clear string builder
		}
		else { // wrong password
			Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
			sb.setLength(0);
		}
	}

	// using TextWatcher, input password
	// each editText require one digit password
	public void enterPassword() {
		editText1.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
				if(editText1.length() == 1)	{ // 1-digit password entered
					sb.append(editText1.getText().toString()); // append input to string builder
					editText1.requestFocus();
					editText1.clearFocus();
					editText2.requestFocus(); // translate focus to next edit text
				}
			}
		});

		editText2.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(editText2.length() == 1)	{
					sb.append(editText2.getText().toString());
					editText2.requestFocus();
					editText2.clearFocus();
					editText3.requestFocus();
				}
			}
		});

		editText3.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(editText3.length() == 1)	{
					sb.append(editText3.getText().toString());
					editText3.requestFocus();
					editText3.clearFocus();
					editText4.requestFocus();
				}
			}
		});

		editText4.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(editText4.length() == 1)	{
					sb.append(editText4.getText().toString());
					editText4.requestFocus();
					editText4.clearFocus();
					// if all passwords entered, clear password field then check password
					editText1.setText("");
					editText2.setText("");
					editText3.setText("");
					editText4.setText("");
					checkPassword();// compare user input password and saved password
				}
			}
		});
	}
}
