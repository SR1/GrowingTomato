package com.sr1.growingtomato;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.sr1.growingtomato.R;
import com.sr1.growingtomato.module.RewardModule;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	public static final String name = "MainActivity";
	private SlidingMenu menu;
	ActionBar actionBar;
	ListView taskList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar = getActionBar();
		taskList = (ListView)findViewById(R.id.taskList);
		initialMenu();
		
		RewardModule rewardModule = new RewardModule(this);
		rewardModule.setReward(1, "睡一觉", 3);
		Log.v(toString(), rewardModule.getRewardList().toString());
	}

	private void initialMenu() {
		
		// 设置滑动菜单的属性值
		menu = new SlidingMenu(this);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		// 设置滑动菜单的视图界面
		menu.setMenu(R.layout.menu_main);
	}

}
