package com.sr1.growingtomato.activity;

import java.util.ArrayList;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.sr1.growingtomato.R;
import com.sr1.growingtomato.adapter.TaskListAdapter;
import com.sr1.growingtomato.entity.Task;
import com.sr1.growingtomato.module.TaskModule;
import com.umeng.analytics.MobclickAgent;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	public static final String name = "MainActivity";
	private SlidingMenu menu;
	ActionBar actionBar;
	ListView taskList;

	TaskModule taskModule;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar = getActionBar();
		taskList = (ListView) findViewById(R.id.taskList);
		taskModule = new TaskModule(this);
		
		initialMenu();
		setAdapter();

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

	private void setAdapter() {

		ArrayList<Task> tasks = taskModule.getTaskList();

		if (tasks.size() < 1) {
			findViewById(R.id.no_data_hint).setVisibility(View.VISIBLE);
			findViewById(R.id.taskList).setVisibility(View.GONE);
		} else {
			TaskListAdapter adapter = new TaskListAdapter(this,
					taskModule.getTaskList());
			taskList.setAdapter(adapter);
			findViewById(R.id.no_data_hint).setVisibility(View.GONE);
			findViewById(R.id.taskList).setVisibility(View.VISIBLE);
		}

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
