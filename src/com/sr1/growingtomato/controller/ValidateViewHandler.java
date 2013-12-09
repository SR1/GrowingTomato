package com.sr1.growingtomato.controller;

import android.os.Handler;
import android.os.Message;

public class ValidateViewHandler extends Handler {
	
	public static final String TAG = "ValidateViewHandler";

	public static final int UPDATE_LOOP = 1;
	public static final int UPDATE_NOW = 2;
	
	private OnValidateListener listener;
	
	public ValidateViewHandler(OnValidateListener listener){
		this.listener = listener;
	}
	
	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case UPDATE_LOOP:
			if(listener!=null)
				listener.onValidate();
			sendEmptyMessageDelayed(UPDATE_LOOP, 500);
			break;

		case UPDATE_NOW:
			if(listener!=null)
				listener.onValidate();
			break;
		}
	}
	
	public interface OnValidateListener{
		/***
		 * Ñ­»·¸üÐÂ
		 */
		public void onValidate();
	}
}
