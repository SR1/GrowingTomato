package com.sr1.growingtomato.activity;

import com.sr1.growingtomato.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class TimingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_timing);
	}
}
