package com.sr1.growingtomato.activity;

import java.util.Random;

import com.sr1.growingtomato.R;
import com.sr1.growingtomato.module.RewardModule;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class RewardActivity extends Activity {

	int rewardList[] = { R.id.reward1, R.id.reward2, R.id.reward3,
			R.id.reward4, R.id.reward5, R.id.reward6, };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_reward);
	}

	public void pick(View v) {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		int positon = random.nextInt() % 6;
		positon = positon > 0 ? positon : -positon;
		for (int viewId : rewardList) {
			if(viewId!=v.getId())
				findViewById(viewId).setVisibility(View.GONE);
		}
		
		//TranslateAnimation animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta)
		
		RewardModule rewardModule = new RewardModule(this);
		TextView reward = (TextView) findViewById(R.id.reward);
		reward.setText(rewardModule.getRewardList().get(positon).getName());
		findViewById(R.id.resultLayout).setVisibility(View.VISIBLE);
	}

	public void endPickReward(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
