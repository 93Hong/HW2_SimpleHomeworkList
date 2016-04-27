

/*
* 2016.3.22 Hong Gi Wook
* 3. Android HW#1-C: A Mini Calculator
*
* MainActivity.java, activity_main, activity_main(land)
* activity_main(land) for change in the screen orientation
*
* Simple Stack Calculator
* Operator : + - * /
* Number : 0 ~ 9
* Other : c(clear), =
*
* Design
* - 숫자를 연속적으로 적으면 왼쪽에 붙음
* - '3 + 4 +' 를 입력 했을 때 '3 + 4' 가 계산되어서 Box에 값이 보임
* - 기기를 회전시키면 다른 layout을 불러옴
* - OnClickListener interface를 implements해서 onClick event를 한번에 처리
* - .xml은 RelativeLayout에 EditText와 TableLayout을 넣어 ui를 구성
* - useing onSaveInstanceState, Remember Temporary Values
* - 기기를 회전 시켰을 때 이전에 연산을 위해 피연산자나 연산자를 입력했다면
* 	돌린 후에도 그 값이 유지되게 onSaveInstanceState 를 사용함
*
* Exception control
* - 연속된 연산자 버튼 입력에 대해서 첫번째 연산자만 적용되도록 함
*   Toast를 띄워 알림
* - '=' 누를 때 피연산자가 2개가 아니면 아무일도 일어나지 않음
* - Box의 값이 결과 값일 경우에 숫자를 입력하면 입력한 숫자로 바뀜
* - Box의 값이 입력한 값일 경우에 숫자를 입력하면 오른쪽에 붙음
* - Box의 값이 0일 경우에 숫자를 입력하면 오른쪽에 붙지않고 입력한 숫자로 바뀜
* - '/' 연산에서 0으로 나눌경우 Toast를 띄우고 Box값을 0으로 초기화
*
* */

package com.example.hong.simplehomeworklist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Stack;


// Implement event Listener of buttons using implements interface on the Activity
public class SimpleCalculator extends Activity implements OnClickListener {

	// variable of widget
	EditText editText;
	Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
	Button clear, plus, mul, sub, div, result;

	Stack<Double> numbers = new Stack<>(); // 피연산자를 저장하는 스택
	Stack<String> operator = new Stack<>(); // 연산자를 저장하는 스택

	boolean preIsNumber = false; // 직전에 누른 버튼이 피연산자인지
	boolean isResult = false; // Box의 값이 결과값인지

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplecalculator_layout); // ui와 control을 연결

		editText = (EditText) findViewById(R.id.editText); // find a child view with the given id

		btn0 = (Button) findViewById(R.id.zero);
		btn1 = (Button) findViewById(R.id.one);
		btn2 = (Button) findViewById(R.id.two);
		btn3 = (Button) findViewById(R.id.three);
		btn4 = (Button) findViewById(R.id.four);
		btn5 = (Button) findViewById(R.id.five);
		btn6 = (Button) findViewById(R.id.six);
		btn7 = (Button) findViewById(R.id.seven);
		btn8 = (Button) findViewById(R.id.eight);
		btn9 = (Button) findViewById(R.id.nine);
		clear = (Button) findViewById(R.id.clear);
		plus = (Button) findViewById(R.id.plus);
		mul = (Button) findViewById(R.id.mul);
		sub = (Button) findViewById(R.id.sub);
		div = (Button) findViewById(R.id.div);
		result = (Button) findViewById(R.id.result);

		// view에 onClickListener를 등록
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn0.setOnClickListener(this);
		clear.setOnClickListener(this);
		plus.setOnClickListener(this);
		mul.setOnClickListener(this);
		sub.setOnClickListener(this);
		div.setOnClickListener(this);
		result.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) { // onClickListener가 불리면 모두 여기로
		String tmp;

		switch (v.getId()) { // switch about view's id

			/////////////////  피연산자
			case R.id.zero:
				// Box의 값이 0이면 버튼의 값으로 Box를 초기화
				if (Double.parseDouble(editText.getText().toString()) == 0.0)
					editText.setText("");
				// 피연산자를 입력하는 중이라면 버튼의 값을 뒤에 붙임
				if (preIsNumber && !isResult) {
					tmp = editText.getText().toString() + "0";
					editText.setText(tmp);
				} else { // 결과값이 나온 후에 숫자를 누르면 해당 숫자로 초기화
					editText.setText("0");
					preIsNumber = true;
				}
				isResult = false; // Box의 값이 결과 값이 아니다
				break;
			case R.id.one:
				if (Double.parseDouble(editText.getText().toString()) == 0.0)
					editText.setText("");
				if (preIsNumber && !isResult) {
					tmp = editText.getText().toString() + "1";
					editText.setText(tmp);
				} else {
					editText.setText("1");
					preIsNumber = true;
				}
				isResult = false;
				break;
			case R.id.two:
				if (Double.parseDouble(editText.getText().toString()) == 0.0)
					editText.setText("");
				if (preIsNumber && !isResult) {
					tmp = editText.getText().toString() + "2";
					editText.setText(tmp);
				} else {
					editText.setText("2");
					preIsNumber = true;
				}
				isResult = false;
				break;
			case R.id.three:
				if (Double.parseDouble(editText.getText().toString()) == 0.0)
					editText.setText("");
				if (preIsNumber && !isResult) {
					tmp = editText.getText().toString() + "3";
					editText.setText(tmp);
				} else {
					editText.setText("3");
					preIsNumber = true;
				}
				isResult = false;
				break;
			case R.id.four:
				if (Double.parseDouble(editText.getText().toString()) == 0.0)
					editText.setText("");
				if (preIsNumber && !isResult) {
					tmp = editText.getText().toString() + "4";
					editText.setText(tmp);
				} else {
					editText.setText("4");
					preIsNumber = true;
				}
				isResult = false;
				break;
			case R.id.five:
				if (Double.parseDouble(editText.getText().toString()) == 0.0)
					editText.setText("");
				if (preIsNumber && !isResult) {
					tmp = editText.getText().toString() + "5";
					editText.setText(tmp);
				} else {
					editText.setText("5");
					preIsNumber = true;
				}
				isResult = false;
				break;
			case R.id.six:
				if (Double.parseDouble(editText.getText().toString()) == 0.0)
					editText.setText("");
				if (preIsNumber && !isResult) {
					tmp = editText.getText().toString() + "6";
					editText.setText(tmp);
				} else {
					editText.setText("6");
					preIsNumber = true;
				}
				isResult = false;
				break;
			case R.id.seven:
				if (Double.parseDouble(editText.getText().toString()) == 0.0)
					editText.setText("");
				if (preIsNumber && !isResult) {
					tmp = editText.getText().toString() + "7";
					editText.setText(tmp);
				} else {
					editText.setText("7");
					preIsNumber = true;
				}
				isResult = false;
				break;
			case R.id.eight:
				if (Double.parseDouble(editText.getText().toString()) == 0.0)
					editText.setText("");
				if (preIsNumber && !isResult) {
					tmp = editText.getText().toString() + "8";
					editText.setText(tmp);
				} else {
					editText.setText("8");
					preIsNumber = true;
				}
				isResult = false;
				break;
			case R.id.nine:
				if (Double.parseDouble(editText.getText().toString()) == 0.0)
					editText.setText("");
				if (preIsNumber && !isResult) {
					tmp = editText.getText().toString() + "9";
					editText.setText(tmp);
				} else {
					editText.setText("9");
					preIsNumber = true;
				}
				isResult = false;
				break;

			///////////////////////// operator

			case R.id.plus:
				if (!preIsNumber) { // 연산자를 연속적으로 눌렀을 경우
					Toast.makeText(getApplicationContext(), "Error : successive operator", Toast.LENGTH_SHORT).show();
				} else if (operator.isEmpty()) { // 연산자 스택이 비었을 경우(계산 안해도 됨)
					// 피연산자 하나와 연산자 하나를 각각의 스택에 넣음
					numbers.push(Double.parseDouble(editText.getText().toString()));
					operator.push("+");
				} else { // 연산자를 이미 입력한 경우 값을 계산후 첫번째 피연산자로 둠
					// 스택에서 연산자와 피연산자를 꺼낸 후 현재 editText의 값을 가져와 계산
					double a = numbers.pop();
					double b = Double.parseDouble(editText.getText().toString());
					String op = operator.pop();
					Double value = new Double(calculator(a, b, op)); // calculate a op b
					numbers.push(value); // 결과를 피연산자 스택에 저장

					// double인지 integer인지 판단해서 editText를 바꿈
					if (isInteger(value))
						op = value.intValue() + "";
					else
						op = value + "";
					editText.setText(op);
					operator.push("+");
				}
				preIsNumber = false;
				break;
			case R.id.mul:
				if (!preIsNumber) {
					Toast.makeText(getApplicationContext(), "Error : successive operator", Toast.LENGTH_SHORT).show();
				} else if (operator.isEmpty()) {
					numbers.push(Double.parseDouble(editText.getText().toString()));
					operator.push("*");
				} else {
					double a = numbers.pop();
					double b = Double.parseDouble(editText.getText().toString());
					String op = operator.pop();
					Double value = new Double(calculator(a, b, op)); // calculate a op b
					numbers.push(value); // 결과를 피연산자 스택에 저장

					if (isInteger(value))
						op = value.intValue() + "";
					else
						op = value + "";
					editText.setText(op);
					operator.push("*");
				}
				preIsNumber = false;
				break;
			case R.id.sub:
				if (!preIsNumber) {
					Toast.makeText(getApplicationContext(), "Error : successive operator", Toast.LENGTH_SHORT).show();
				} else if (operator.isEmpty()) {
					numbers.push(Double.parseDouble(editText.getText().toString()));
					operator.push("-");
				} else {
					double a = numbers.pop();
					double b = Double.parseDouble(editText.getText().toString());
					String op = operator.pop();
					Double value = new Double(calculator(a, b, op)); // calculate a op b
					numbers.push(value); // 결과를 피연산자 스택에 저장

					if (isInteger(value))
						op = value.intValue() + "";
					else
						op = value + "";
					editText.setText(op);
					operator.push("-");
				}
				preIsNumber = false;
				break;
			case R.id.div:
				if (!preIsNumber) {
					Toast.makeText(getApplicationContext(), "Error : successive operator", Toast.LENGTH_SHORT).show();
				} else if (operator.isEmpty()) {
					numbers.push(Double.parseDouble(editText.getText().toString()));
					operator.push("/");
				} else {
					double a = numbers.pop();
					double b = Double.parseDouble(editText.getText().toString());
					String op = operator.pop();
					Double value = new Double(calculator(a, b, op)); // calculate a op b
					numbers.push(value); // 결과를 피연산자 스택에 저장

					if (isInteger(value))
						op = value.intValue() + "";
					else
						op = value + "";
					editText.setText(op);
					operator.push("/");
				}
				preIsNumber = false;
				break;
			case R.id.clear:
				editText.setText("0");
				numbers.clear();
				operator.clear();
				preIsNumber = false;
				break;
			case R.id.result:
				// number operator number 의 관계가 성립된 경우 연산을 함
				if (!operator.isEmpty() && preIsNumber) {
					double a = numbers.pop();
					double b = Double.parseDouble(editText.getText().toString());
					String op = operator.pop();
					Double value = new Double(calculator(a, b, op)); // calculate a op b
					numbers.push(value); // 결과를 피연산자 스택에 저장

					if (isInteger(value))
						op = value.intValue() + "";
					else
						op = value + "";
					editText.setText(op);
					preIsNumber = true;
				}
				sendResult();
				break;
		}
	}

	public void sendResult() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String s = editText.getText().toString();

		bundle.putString("result", editText.getText().toString());
		intent.putExtras(bundle);
		setResult(Activity.RESULT_OK, intent);
		finish();
	}

	// check is integer or not
	public static boolean isInteger(double dou) {
		if (dou % 1 == 0.0)
			return true;
		return false;
	}

	// Remember Temporary Values
	// 화면을 돌렸을 때도 연산이 지속되기 위해서 연산자나 피연산자를 입력한 상태라면
	// 입력했던 연산자 혹은 피연산자를 저장함
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save UI state changes to the savedInstanceState.
		// This bundle will be passed to onCreate if the process is
		// killed and restarted.

		if (!numbers.isEmpty())
			savedInstanceState.putDouble("number", numbers.pop());
		if (!operator.isEmpty())
			savedInstanceState.putString("operator", operator.pop());
		super.onSaveInstanceState(savedInstanceState);
	}

	// 화면을 돌렸을 때 이전에 연산 중이었다면 연산자나 피연산자를 불러와서 연산을 계속 진행함
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Restore UI state from the savedInstanceState.
		// This bundle has also been passed to onCreate.

		if (!savedInstanceState.isEmpty()) {
			numbers.push(savedInstanceState.getDouble("number"));
			if (savedInstanceState.getString("operator") != null)
				operator.push(savedInstanceState.getString("operator"));
			else
				preIsNumber = true;
		}
	}

	// 피연산자와 연산자를 바탕으로 실제 계산을 하는 함수
	public double calculator(double a, double b, String op) {
		double value = 0;
		isResult = true; // Box의 값이 결과 값임을 알려줌
		if (op.equals("+")) {
			value = a + b;
		} else if (op.equals("-")) {
			value = a - b;
		} else if (op.equals("/")) {
			if (b == 0.0) { // divide by zero exception
				Toast.makeText(getApplicationContext(), "Error : divide zero exception", Toast.LENGTH_SHORT).show();
			} else {
				value = a / b;
			}
		} else if (op.equals("*")) {
			value = a * b;
		}
		return value;
	}
}