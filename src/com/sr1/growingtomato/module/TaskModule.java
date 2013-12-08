package com.sr1.growingtomato.module;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;

import com.sr1.growingtomato.entity.Reward;
import com.sr1.growingtomato.entity.Task;

public class TaskModule {

	Context context;

	public TaskModule(Context context) {
		this.context = context;
	}

	public ArrayList<Task> getTaskList() {
		SQLiteDatabase database = new DatabaseHelper(context)
				.getReadableDatabase();
		ArrayList<Task> tasks = new ArrayList<Task>();

		// set databas query parameters
		String table = DatabaseHelper.TASKS;
		String columns[] = null;
		String selection = DatabaseHelper.TASKS_IS_FINISHED + "=?";
		String selectionArgs[] = { "false" };
		String groupBy = null;
		String having = null;
		String orderBy = null;
		Cursor result = database.query(table, columns, selection,
				selectionArgs, groupBy, having, orderBy);
		// store task into tasklist
		tasks = parseTask(result);
		result.close();
		database.close();
		return tasks;
	}

	public void add(String name, String remark, int classId) {
		SQLiteDatabase database = new DatabaseHelper(context)
				.getWritableDatabase();

		// set data which will insert into database
		String table = DatabaseHelper.TASKS;
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.TASKS_NAME, name);
		if (remark != null)
			values.put(DatabaseHelper.TASKS_REMARK, remark);
		values.put(DatabaseHelper.TASKS_CLASS_ID, classId);
		database.insert(table, null, values);

		database.close();
	}

	public void setFinished(int taskId, Reward reward) {
		SQLiteDatabase database = new DatabaseHelper(context)
				.getWritableDatabase();
		
		String date = String.valueOf(DateFormat.format("yyyy-MM-dd hh:mm:ss", System.currentTimeMillis()));
		
		// set data which will insert into database
		String table = DatabaseHelper.TASKS;
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.TASKS_REWARD, reward.getName());
		values.put(DatabaseHelper.TASKS_END_DATE, date);
		values.put(DatabaseHelper.TASKS_IS_FINISHED, "true");
		String whereClause = DatabaseHelper.TASKS_ID + "=?";
		String whereArgs[] = { String.valueOf(taskId) };
		
		database.update(table, values, whereClause, whereArgs);
		database.close();

	}

	private ArrayList<Task> parseTask(Cursor result) {
		ArrayList<Task> tasks = new ArrayList<Task>();
		Task task;
		while (result.moveToNext()) {
			task = new Task();
			task.setId(result.getInt(result
					.getColumnIndex(DatabaseHelper.TASKS_ID)));
			task.setName(result.getString(result
					.getColumnIndex(DatabaseHelper.TASKS_NAME)));
			task.setRemark(result.getString(result
					.getColumnIndex(DatabaseHelper.TASKS_REMARK)));
			task.setAddDate(result.getString(result
					.getColumnIndex(DatabaseHelper.TASKS_ADD_DATE)));
			task.setEndDate(result.getString(result
					.getColumnIndex(DatabaseHelper.TASKS_END_DATE)));
			task.setFinished(Boolean.valueOf(result.getString(result
					.getColumnIndex(DatabaseHelper.TASKS_IS_FINISHED))));
			task.setClassId(result.getInt(result
					.getColumnIndex(DatabaseHelper.TASKS_CLASS_ID)));
			task.setReward(result.getString(result
					.getColumnIndex(DatabaseHelper.TASKS_REWARD)));
			tasks.add(task);
		}
		return tasks;
	}
}
