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
	 * ���ż�ʱ����
	 */
	public void playTicTac() {
		stopPlayTicTac();
		isPlaying = true;
		ticTac = MediaPlayer.create(context, R.raw.tictac);
		ticTac.setLooping(true);
		ticTac.start();
	}

	/***
	 * ֹͣ���ż�ʱ����
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
	 * ���ż�ʱ�������
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
