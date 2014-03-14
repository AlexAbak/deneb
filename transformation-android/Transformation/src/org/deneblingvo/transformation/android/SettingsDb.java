package org.deneblingvo.transformation.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.security.*;
import java.util.*;

public class SettingsDb extends SQLiteOpenHelper {

	public SettingsDb(Context context) {
		super(context, "transformationSettings", null, 1);
	}

	@Override
    public void onCreate(SQLiteDatabase db) {
		// создаем таблицу с полями
		db.execSQL("create table files ("
				   + "id integer primary key autoincrement,"
				   + "path text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

	public void addFile(String path) {
		ContentValues cv = new ContentValues();
		cv.put("path", path);
		SQLiteDatabase db = this.getWritableDatabase();
		db.insert("files", null, cv);
	}

	public void delFile(int id) {
		SQLiteDatabase db = this.getWritableDatabase();	
		db.delete("files", "id = ?", new String[]{ new Integer(id).toString() });
	}

	public Vector<SettingsFile> selectFiles() {
		SQLiteDatabase db = this.getWritableDatabase();
		Vector<SettingsFile> dict = new Vector<SettingsFile>();
		Cursor c = db.query("files", null, null, null, null, null, null);
		if (c.moveToFirst()) {
			int idIndex = c.getColumnIndex("id");
			int pathIndex = c.getColumnIndex("path");
			do {
				int id = c.getInt(idIndex);
				String path = c.getString(pathIndex);
				dict.add(new SettingsFile(id, path));
			} while (c.moveToNext());
		}
		return dict;
	}

}
