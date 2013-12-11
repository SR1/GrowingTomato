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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;
import android.widget.Toast;

public class RewardActivity extends Activity implements AnimationListener{

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
		Animation animation = new AlphaAnimation(1.0f, 0.0f);
		animation.setAnimationListener(this);
		animation.setDuration(1000);
		for (int viewId : rewardList) {
			if(viewId!=v.getId())
				findViewById(viewId).startAnimation(animation);
		}
		
		RewardModule rewardModule = new RewardModule(this);
		TextView reward = (TextView) findViewById(R.id.reward);
		reward.setText(rewardModule.getRewardList().get(positon).getName());
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

	@Override
	public void onAnimationEnd(Animation animation) {

		findViewById(R.id.pickLayout).setVisibility(View.GONE);
		View result = findViewById(R.id.resultLayout);
		result.setVisibility(View.VISIBLE);
		animation = new AlphaAnimation(0.1f, 1.0f);
		animation.setDuration(1000);
		result.startAnimation(animation);
		String message = getResources().getString(R.string.get_a_reward);
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackPressed() {
		this.finish();
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
