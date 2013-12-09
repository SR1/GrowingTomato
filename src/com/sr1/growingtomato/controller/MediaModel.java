package com.sr1.growingtomato.controller;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.sr1.growingtomato.R;

public class MediaModel {

	private MediaPlayer ticTac;
	private MediaPlayer chime;
	boolean isPlaying = false;

	private Context context;

	public MediaModel(Context context) {
		this.context = context;
	}

	/***
	 * 播放计时声音
	 */
	public void playTicTac() {
		isPlaying = true;
		stopPlayTicTac();
		ticTac = MediaPlayer.create(context, R.raw.tictac);
		ticTac.setLooping(true);
		ticTac.start();
	}

	/***
	 * 停止播放计时声音
	 */
	public void stopPlayTicTac() {
		isPlaying = false;
		if (ticTac != null) {
			ticTac.stop();
			ticTac.release();
			ticTac = null;
		}
	}
	
	public boolean isPlaying(){
		return isPlaying;
	}
	
	/***
	 * 播放计时完成声音
	 */
	public void playChime() {
		chime = MediaPlayer.create(context, R.raw.chime);
		chime.setLooping(false);
		chime.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
		chime.start();
	}
}
