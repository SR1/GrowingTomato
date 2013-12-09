package com.sr1.growingtomato.activity;

import java.util.Random;

import com.sr1.growingtomato.R;
import com.sr1.growingtomato.module.RewardModule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class RewardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_reward);
	}

	public void pick(View v) {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		int positon = random.nextInt()%6;
		RewardModule rewardModule = new RewardModule(this);
		TextView reward = (TextView)findViewById(R.id.reward);
		reward.setText(rewardModule.getRewardList().get(positon).getName());
		findViewById(R.id.pickLayout).setVisibility(View.GONE);
	}

	public void endPickReward(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}
}
