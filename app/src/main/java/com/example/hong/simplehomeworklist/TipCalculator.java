

/*
* 2016.3.24 Hong Gi Wook
* 2. Android HW #1-B: Simple Tip Calculator
*
* MainActivity.java, activity_main
*
* Simple Tip Calculator
* 15%, 20%, other option
* other is user based
*
* Design
* - Total Amount(Edit text)를 쓰고 option Radio button을 클릭 후 calculate button을 클릭하면
*	tip, total 값을 계산해서 Toast로 띄워줌
* - tip : TotalAmount * option value
* - total : TotalAmount + tip
* - option을 선택하지 않고 calculate button을 누르면 아무일도 발생하지 않음
* - xml의 큰 틀은 LinearLayout을 vertical로 지정 후 editText와 radio를 넣음
* - other radio button 클릭시 editText를 보여주고 그 외의 경우는 가려줌
* - other radio button에 값을 넣고 다른 radio button을 클릭하면 other radio button의 editText의 값을 초기화
* - 결과값은 소수 두째자리 까지 나오게 함
* - onSaveInstanceState로 화면을 돌려도 값이 유지되게 함
*
* Exception control
* - Total Amount에 음수 문자 그리고 아무것도 입력하지 않은 경우 Exception Toast를 띄움
* - option을 선택하지 않고 calculate button을 누르면 아무일도 발생하지 않음
*
* */
package com.example.hong.simplehomeworklist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class TipCalculator extends AppCompatActivity {
	// variable of widget
	Button button;
	RadioButton per15, per20, other;
	EditText editText, editText2;
	RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tipcalculator_layout);

		editText = (EditText)findViewById(R.id.editText);
		editText2 = (EditText)findViewById(R.id.editText2);

		radioGroup = (RadioGroup)findViewById(R.id.radio);
		per15 = (RadioButton)findViewById(R.id.per15);
		per20 = (RadioButton)findViewById(R.id.per20);
		other = (RadioButton)findViewById(R.id.other);

		// radipGroup의 radio button이 클릭되면 실행
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// 클릭된 버튼이 other radio button이면 editText를 보여줌
				if(checkedId == R.id.other)
					editText2.setVisibility(View.VISIBLE);
					// 그 외의 경우에는 editText를 숨김
				else {
					editText2.setVisibility(View.INVISIBLE);
					editText2.setText("");
				}
			}
		});

		button = (Button)findViewById(R.id.cal);

		// calculate button을 클릭하면 radio button에 따라 나눔
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String pay = editText.getText().toString();
				// exception handling (값이 입력이 안되었거나, 음수, 문자열)
				if (!checkException(pay))
					return;

				if (per15.isChecked()) { // 15% option
					double tip = calculateTip(pay, "15");
					double amount = tip + Double.parseDouble(pay);
					amount = Math.round(amount * 100d) / 100d;

					Toast.makeText(getApplicationContext(), "Tip:" + tip + "\nTotal:" + amount, Toast.LENGTH_SHORT).show();
				} else if (per20.isChecked()) { // 20% option

					double tip = calculateTip(pay, "20");
					double amount = tip + Double.parseDouble(pay);
					amount = Math.round(amount * 100d) / 100d;

					Toast.makeText(getApplicationContext(), "Tip:" + tip + "\nTotal:" + amount, Toast.LENGTH_SHORT).show();
				} else if (other.isChecked()) { // other option
					String tipPer = editText2.getText().toString();

					if (!checkException(tipPer)) // option editText에 제대로 된 값이 입력됬는지 확인
						return;

					double tip = calculateTip(pay, tipPer);
					double amount = tip + Double.parseDouble(pay);
					amount = Math.round(amount * 100d) / 100d;

					Toast.makeText(getApplicationContext(), "Tip:" + tip + "\nTotal:" + amount, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	// Remember Temporary Values
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		// Save UI state changes to the savedInstanceState.
		// This bundle will be passed to onCreate if the process is
		// killed and restarted.
		String txt = editText.getText().toString();
		String txt2= editText2.getText().toString();
		savedInstanceState.putString("TextEdit", txt);
		savedInstanceState.putString("TextOptionEdit", txt2);
		// etc.
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Restore UI state from the savedInstanceState.
		// This bundle has also been passed to onCreate.
		String myString = savedInstanceState.getString("TextEdit");
		String myString2 =  savedInstanceState.getString("TextEdit");
		editText.setText(myString);
		editText2.setText(myString2);
	}

	// calculate tip
	public double calculateTip(String pay, String tipPer) {
		double tip = Double.parseDouble(pay) * Double.parseDouble(tipPer) / 100;
		tip = Math.round(tip*100d) / 100d; // 소수 둘째자리 까지 표현
		return tip;
	}

	// exception handling (값이 입력이 안되었거나, 음수, 문자열)
	public boolean checkException(String pay) {
		if (pay.isEmpty() || !isNumeric(pay)) { // 값이 입력이 안되었거나 문자열인 경우
			Toast.makeText(getApplicationContext(), "Exception : " + "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (!isPositive(pay)) { // 음수값일 경우
			Toast.makeText(getApplicationContext(), "Exception : " + "음수는 올 수 없습니다", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	// 숫자가 입력됬는지 확인
	public boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	// 양수인지 확인
	public boolean isPositive(String str) {
		if (Double.parseDouble(str) < 0)
			return false;
		else
			return true;
	}
}