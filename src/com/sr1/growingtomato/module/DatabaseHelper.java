package com.sr1.growingtomato.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String TAG = "DatabaseHelper";

	private static final String DATABASE_NAME = "GrowingTomato.db";
	private static final int DATABASE_VERSION = 1;

	public static final String TASKS = "TASKS";
	public static final String TASKS_ID = "id";
	public static final String TASKS_NAME = "name";
	public static final String TASKS_REMARK = "remark";
	public static final String TASKS_ADD_DATE = "addDate";
	public static final String TASKS_END_DATE = "endDate";
	public static final String TASKS_IS_FINISHED = "isFinished";
	public static final String TASKS_CLASS_ID = "classId";
	public static final String TASKS_REWARD = "reward";

	private static final String CREATE_TABLE_TASKS = "CREATE TABLE IF NOT EXISTS tasks ("
			+ "id INTEGER PRIMARY KEY, "
			+ "name TEXT NOT NULL, "
			+ "remark TEXT, "
			+ "addDate DATETIME NOT NULL DEFAULT (datetime('now','localtime')), "
			+ "endDate DATETIME NOT NULL DEFAULT 0, "
			+ "isFinished BOOLEAN DEFAULT false, "
			+ "classId INTEGER NOT NULL DEFAULT 0, "
			+ "reward TEXT NOT NULL DEFAULT '' );";

	private static final String TASKS_SAMPLE_DATA = "INSERT INTO `tasks` (name, classId) VALUES ('比昨天的我更优秀！', 1);";

	public static final String CLASSES = "classes";
	public static final String CLASSES_ID = "id";
	public static final String CLASSES_NAME = "name";
	public static final String CLASSES_ADD_DATE = "addDate";
	public static final String CLASSES_END_DATE = "endDate";
	public static final String CLASSES_IS_FINISHED = "isFinished";

	private static final String CREATE_TABLE_CLASSES = "CREATE TABLE IF NOT EXISTS classes ("
			+ "id INTEGER PRIMARY KEY, "
			+ "name TEXT NOT NULL, "
			+ "addDate DATETIME NOT NULL DEFAULT (datetime('now','localtime')), "
			+ "endDate DATETIME NOT NULL DEFAULT 0, "
			+ "isFinished BOOLEAN DEFAULT false);";
	private static final String CLASSES_SAMPLE_DATA = "INSERT INTO `classes` (name) VALUES ('全面发展');";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		Log.v(TAG, "onCreate--->");
		db.execSQL(CREATE_TABLE_CLASSES);
		db.execSQL(CREATE_TABLE_TASKS);
		db.execSQL(TASKS_SAMPLE_DATA);
		db.execSQL(CLASSES_SAMPLE_DATA);

	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int currentVersion) {
		Log.v(TAG, "onUpgrade--->");
		// 更新数据库时删除旧有数据
		db.execSQL("DROP TABLE IF EXISTS " + CLASSES);
		db.execSQL("DROP TABLE IF EXISTS " + TASKS);
		onCreate(db);
	}
}
