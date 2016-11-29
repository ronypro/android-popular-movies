package com.ronypro.android.database;

import android.database.sqlite.SQLiteDatabase;

public abstract class DatabaseUpdater {

	private final int version;

	public DatabaseUpdater(int version) {
		super();
		this.version = version;
	}

	public int getVersion() {
		return this.version;
	}

	public abstract void onBeforeUpdate(SQLiteDatabase db);

	public abstract void onAfterUpdate(SQLiteDatabase db);

}