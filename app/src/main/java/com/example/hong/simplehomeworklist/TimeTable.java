

/*
* 2016.3.23 Hong Gi Wook
* 1. Android HW. #1-A: Time Table
*
* MainActivity.java, activity_main
*
* Simple Time Table
* Today’s schedule should be highlighted using setAlpha()
*
* Design
* - LinearLayout의 orientation을 horizontal로 설정하고
* 	6개의 LinearLayout을 orientation vertical로 만든다
* 	1개는 시간에 대한 	LinearLayout / 5개는 요일에 대한 LinearLayout
*
* - LinearLayout을 ScrollView로 감싸 화면 전환시 화면보다 큰 view를 볼 수 있게한다
*
* - Calendar abstract class를 사용해 오늘의 요일을 integer값으로 받아와
* 	그 값에 따라 요일에 대한 LinearLayout의 alpha(투명도)를 부여해서 highlight 효과를 보여준다
*
* Exception control
* - 주말일 때 하이라이트가 안되므로 log에 exception을 적음
* */

package com.example.hong.simplehomeworklist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.Calendar;

public class TimeTable extends AppCompatActivity {
	// variable of widget
	LinearLayout mon, tue, wed, thu, fri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_layout); // ui와 control을 연결

		// find a child view with the given id
		mon = (LinearLayout) findViewById(R.id.mon);
		wed = (LinearLayout) findViewById(R.id.wed);
		tue = (LinearLayout) findViewById(R.id.tue);
		thu = (LinearLayout) findViewById(R.id.thu);
		fri = (LinearLayout) findViewById(R.id.fri);
	}

	// 앱 실행중 전화가 오거나 하여 백그라운드로 갔다가 다시 실행될 경우를 고려하여
	// onResume에 highlight를 보여주는 함수를 적음
	@Override
	protected void onResume() {
		super.onResume();
		Calendar today = Calendar.getInstance();
		highlight(today.get(Calendar.DAY_OF_WEEK));
	}

	// 요일 값을 parameter로 받아서 해당 요일에 alpha값을 부여
	public void highlight(int day) {
		float alpha = (float) 0.6;

		switch (day) {
			case 2:
				mon.setAlpha(alpha);
				break;
			case 3:
				tue.setAlpha(alpha);
				break;
			case 4:
				wed.setAlpha(alpha);
				break;
			case 5:
				thu.setAlpha(alpha);
				break;
			case 6:
				fri.setAlpha(alpha);
				break;
			// 토요일, 일요일의 경우
			default:
				Log.d("Exception", "Weekend");
				break;
		}
	}
}