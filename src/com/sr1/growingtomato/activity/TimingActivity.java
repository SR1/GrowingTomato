package com.sr1.growingtomato.activity;

import com.sr1.growingtomato.R;
import com.sr1.growingtomato.controller.MediaModel;
import com.sr1.growingtomato.controller.ScreenOnModel;
import com.sr1.growingtomato.controller.Timer;
import com.sr1.growingtomato.controller.TimerControllerInterface.OnTimeUpListener;
import com.sr1.growingtomato.controller.ValidateViewHandler;
import com.sr1.growingtomato.controller.ValidateViewHandler.OnValidateListener;
import com.sr1.growingtomato.util.DateUtil;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TimingActivity extends Activity implements OnLongClickListener,
		OnValidateListener, OnTimeUpListener {

	Timer timer;
	ImageButton giveupBtn;
	MediaModel media;
	ValidateViewHandler handler;
	ScreenOnModel screenOn;
	TextView timing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_timing);
		giveupBtn = (ImageButton) findViewById(R.id.giveup);

		media = new MediaModel(this);
		media.playTicTac();

		timing = (TextView) findViewById(R.id.timing);
		screenOn = new ScreenOnModel(this);
		handler = new ValidateViewHandler(this);
		timer = new Timer();
		timer.setOnTimeUpListener(this);

		giveupBtn.setOnLongClickListener(this);
		timer.startTimer();
	}

	public void encourage(View v) {
		String message = getResources().getString(R.string.encourage_message);
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	public void soundsTroggle(View v) {
		Drawable src;
		if (media.isPlaying()) {
			media.stopPlayTicTac();
			src = getResources().getDrawable(R.drawable.sounds_off);
		} else {
			media.playTicTac();
			src = getResources().getDrawable(R.drawable.sounds_on);
		}
		((ImageButton) v).setImageDrawable(src);
	}

	private void giveup() {
		timer.stopTimer();
		media.stopPlayTicTac();
		String message = ":( so sad!";
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}

	@Override
	public void onTimeUp() {
		media.stopPlayTicTac();
		media.playChime();
		stopUpdateView();
		String message = getResources().getString(R.string.finish_a_task);
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, RewardActivity.class);
		startActivity(intent);
		this.finish();
	}

	@Override
	public void onBackPressed() {
		moveTaskToBack(false);
	}

	@Override
	public boolean onLongClick(View v) {
		giveup();
		return true;
	}

	@Override
	protected void onResume() {
		startUpdateView();
		screenOn.on();
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		stopUpdateView();
		screenOn.off();
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	public void onValidate() {
		timing.setText(DateUtil.TransToString(timer.getRemainingTime()));
	}

	/***
	 * ��ʱˢ�½���
	 */
	public void startUpdateView() {
		if (handler == null)
			handler = new ValidateViewHandler(this);
		handler.sendEmptyMessage(ValidateViewHandler.UPDATE_LOOP);
	}

	/***
	 * ֹͣˢ�½���
	 */
	public void stopUpdateView() {
		handler.removeMessages(ValidateViewHandler.UPDATE_LOOP);
	}

}
