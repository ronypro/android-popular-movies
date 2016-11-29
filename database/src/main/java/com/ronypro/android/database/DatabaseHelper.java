package com.ronypro.android.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.SparseArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String TAG = DatabaseHelper.class.getName();

	private static final String FOREIGN_KEYS_ON = "PRAGMA foreign_keys = ON";

    private final DatabaseInfo dataBaseInfo;
	private final Context context;

	public DatabaseHelper(Context context, DatabaseInfo dataBaseInfo) {
		super(context, dataBaseInfo.name, null, dataBaseInfo.version);
		this.dataBaseInfo = dataBaseInfo;
		this.context = context;
	}

	public Context getContext() {
		return this.context;
	}

	public DatabaseInfo getDataBaseInfo() {
		return this.dataBaseInfo;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		onUpgrade(db, 0, this.dataBaseInfo.version);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        SparseArray<DatabaseUpdater> updaters = createUpdaters();

        boolean updateFail = false;
        db.beginTransaction();
        try {
            for (int version = oldVersion + 1; version <= newVersion; version++) {
                DatabaseUpdater updater = updaters.get(version);
                try {
                    if (!upgradeTo(db, version, updater))
                        continue;
					Log.i(TAG, "Banco de dados atualizado para a versão " + version);
                } catch (Exception e) {
                    updateFail = true;
                    Log.e(TAG, "Erro ao atualizar para a versão " + version, e);
                    throw new SQLException("Erro ao atualizar base de dados!");
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            if (updateFail) {
                db.close();
            }
        }
		Log.i(TAG, "Banco de dados pronto para a versão " + newVersion);
	}

	private boolean upgradeTo(SQLiteDatabase db, int version, DatabaseUpdater updater) throws IOException {
		if (updater != null)
			updater.onBeforeUpdate(db);

		if (!upgradeTo(db, version) && updater == null) {
			Log.w(TAG, "Não existe atualização de banco de dados para a versão " + version);
			return false;
		}

		if (updater != null)
			updater.onAfterUpdate(db);

		return true;
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, "onDowngrade ignorado");
	}

	private SparseArray<DatabaseUpdater> createUpdaters() {
		SparseArray<DatabaseUpdater> updaters = new SparseArray<DatabaseUpdater>();
		List<DatabaseUpdater> list = this.dataBaseInfo.createUpdaters();
		if (list != null)
			for (DatabaseUpdater updater : list)
				updaters.put(updater.getVersion(), updater);
		return updaters;
	}

	public File getDataBaseFile() {
		return this.context.getDatabasePath(this.dataBaseInfo.name);
	}

	private boolean upgradeTo(SQLiteDatabase db, int version) throws IOException {
		String versionDir = this.dataBaseInfo.assetsDir + File.separator + String.format(this.dataBaseInfo.sqlVersionDir, version);

		String[] files;
		try {
			files = this.context.getAssets().list(versionDir);
		} catch (FileNotFoundException e) {
			return false;
		}

		if (files == null || files.length == 0)
			return false;

		Arrays.sort(files, new SQLFileComparator());

		versionDir += File.separator;

		for (String file : files) {
			execScripts(db, versionDir + file);
		}

		return true;
	}

	private void execScripts(SQLiteDatabase db, String sqlFile) throws IOException {
		Log.i(TAG, "Executando script " + sqlFile);
		InputStream inputStream = this.context.getAssets().open(sqlFile);
		SQLReader sqlReader = new SQLReader(inputStream);
		while (sqlReader.hasNext()) {
			String sql = sqlReader.next();
			db.execSQL(sql);
		}
		sqlReader.close();
	}

    @Override
    public SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase = super.getWritableDatabase();
        writableDatabase.execSQL(FOREIGN_KEYS_ON);
        return writableDatabase;
    }

}
