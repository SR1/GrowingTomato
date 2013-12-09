package com.sr1.growingtomato.activity;

import android.app.Activity;
import android.content.Intent;

public class RewardActivity extends Activity {

	private void endPickReward() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}
}
