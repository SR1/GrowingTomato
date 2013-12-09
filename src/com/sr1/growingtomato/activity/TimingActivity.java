package com.sr1.growingtomato.activity;

import com.sr1.growingtomato.R;
import com.sr1.growingtomato.controller.MediaModel;
import com.sr1.growingtomato.controller.ScreenOnModel;
import com.sr1.growingtomato.controller.Timer;
import com.sr1.growingtomato.controller.TimerControllerInterface.OnTimeUpListener;
import com.sr1.growingtomato.controller.ValidateViewHandler;
import com.sr1.growingtomato.controller.ValidateViewHandler.OnValidateListener;
import com.sr1.growingtomato.util.DateUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TimingActivity extends Activity implements OnLongClickListener,
		OnValidateListener, OnTimeUpListener {

	Timer timer;
	Button giveupBtn;
	MediaModel media;
	ValidateViewHandler handler;
	ScreenOnModel screenOn;
	TextView timing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_timing);
		giveupBtn = (Button) findViewById(R.id.giveup);

		media = new MediaModel(this);
		media.playTicTac();

		timing = (TextView) findViewById(R.id.timing);
		screenOn = new ScreenOnModel(this);
		handler = new ValidateViewHandler(this);
		timer = new Timer();

		giveupBtn.setOnLongClickListener(this);
		timer.startTimer();
	}

	public void encourage(View v) {
		String message = getResources().getString(R.string.encourage_message);
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	public void soundsTroggle(View v) {
		if (media.isPlaying()) {
			media.stopPlayTicTac();
			((TextView) v).setText("开启声音");
		} else {
			media.playTicTac();
			((TextView) v).setText("关闭声音");
		}
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
	}

	@Override
	protected void onPause() {
		stopUpdateView();
		screenOn.off();
		super.onPause();
	}

	@Override
	public void onValidate() {
		timing.setText(DateUtil.TransToString(timer.getRemainingTime()));
	}

	/***
	 * 定时刷新界面
	 */
	public void startUpdateView() {
		if (handler == null)
			handler = new ValidateViewHandler(this);
		handler.sendEmptyMessage(ValidateViewHandler.UPDATE_LOOP);
	}

	/***
	 * 停止刷新界面
	 */
	public void stopUpdateView() {
		handler.removeMessages(ValidateViewHandler.UPDATE_LOOP);
	}

}
