package com.sr1.growingtomato.adapter;

import java.util.ArrayList;

import com.sr1.growingtomato.R;
import com.sr1.growingtomato.activity.TimingActivity;
import com.sr1.growingtomato.entity.Class;
import com.sr1.growingtomato.entity.Task;
import com.sr1.growingtomato.module.ClassesModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TaskListAdapter extends BaseAdapter {

	ArrayList<Task> taskList;
	Context context;

	public TaskListAdapter(Context context, ArrayList<Task> taskList) {
		this.context = context;
		this.taskList = taskList;
	}

	@Override
	public int getCount() {
		return taskList.size();
	}

	@Override
	public Object getItem(int position) {
		return taskList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View item = inflater.inflate(R.layout.item_task, null);

		final Task task = taskList.get(position);

		TextView name = (TextView) item.findViewById(R.id.name);
		TextView classname = (TextView) item.findViewById(R.id.class_name);
		Button doit = (Button) item.findViewById(R.id.doit);

		name.setText(task.getName());

		ClassesModule classesModule = new ClassesModule(context);
		Class class1 = classesModule.get(task.getClassId());
		classname.setText(class1.getName());

		doit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String message = context.getResources().getString(
						R.string.fall_to_a_task);
				Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context, TimingActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("task", task);
				context.startActivity(intent);
			}
		});
		return item;
	}
}
