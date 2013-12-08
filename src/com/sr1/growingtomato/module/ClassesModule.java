package com.sr1.growingtomato.module;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sr1.growingtomato.entity.Class;
import com.sr1.growingtomato.util.DateUtil;

public class ClassesModule {

	Context context;

	public ClassesModule(Context context) {
		this.context = context;
	}

	public ArrayList<Class> getClasses() {
		SQLiteDatabase database = new DatabaseHelper(context)
				.getReadableDatabase();
		ArrayList<Class> classes = new ArrayList<Class>();

		// set databas query parameters
		String table = DatabaseHelper.CLASSES;
		String columns[] = null;
		String selection = DatabaseHelper.CLASSES_IS_FINISHED + "=?";
		String selectionArgs[] = { "false" };
		String groupBy = null;
		String having = null;
		String orderBy = null;
		Cursor result = database.query(table, columns, selection,
				selectionArgs, groupBy, having, orderBy);
		// store task into tasklist
		classes = parseClass(result);
		result.close();
		database.close();
		return classes;
	}

	public Class get(int classId) {
		SQLiteDatabase database = new DatabaseHelper(context)
				.getReadableDatabase();
		ArrayList<Class> classes = new ArrayList<Class>();

		// set databas query parameters
		String table = DatabaseHelper.CLASSES;
		String columns[] = null;
		String selection = DatabaseHelper.CLASSES_ID + "=?";
		String selectionArgs[] = { String.valueOf(classId) };
		String groupBy = null;
		String having = null;
		String orderBy = null;
		Cursor result = database.query(table, columns, selection,
				selectionArgs, groupBy, having, orderBy);
		// store task into tasklist
		classes = parseClass(result);
		result.close();
		database.close();
		if (classes.size() < 1)
			return null;
		return classes.get(0);
	}

	public void add(String name) {
		SQLiteDatabase database = new DatabaseHelper(context)
				.getWritableDatabase();

		// set data which will insert into database
		String table = DatabaseHelper.CLASSES;
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.CLASSES_NAME, name);
		database.insert(table, null, values);
		database.close();

	}

	public void setClassFinish(int classId) {
		SQLiteDatabase database = new DatabaseHelper(context)
				.getWritableDatabase();
		
		// set data which will insert into database
		String table = DatabaseHelper.CLASSES;
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.CLASSES_END_DATE, DateUtil.now());
		values.put(DatabaseHelper.CLASSES_IS_FINISHED, "true");
		String whereClause = DatabaseHelper.CLASSES_ID + "=?";
		String whereArgs[] = { String.valueOf(classId) };

		database.update(table, values, whereClause, whereArgs);
		database.close();

	}

	private ArrayList<Class> parseClass(Cursor result) {
		ArrayList<Class> classes = new ArrayList<Class>();
		Class cls;
		while (result.moveToNext()) {
			cls = new Class();
			cls.setId(result.getInt(result
					.getColumnIndex(DatabaseHelper.CLASSES_ID)));
			cls.setName(result.getString(result
					.getColumnIndex(DatabaseHelper.CLASSES_NAME)));
			cls.setAddDate(result.getString(result
					.getColumnIndex(DatabaseHelper.CLASSES_ADD_DATE)));
			cls.setEndDate(result.getString(result
					.getColumnIndex(DatabaseHelper.CLASSES_END_DATE)));
			cls.setFinished(Boolean.valueOf(result.getString(result
					.getColumnIndex(DatabaseHelper.CLASSES_IS_FINISHED))));
			classes.add(cls);
		}
		return classes;
	}
}
