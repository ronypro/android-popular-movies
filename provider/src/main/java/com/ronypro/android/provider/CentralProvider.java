package com.ronypro.android.provider;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.util.SparseArray;

public abstract class CentralProvider extends ContentProvider {

	private static final int INSERT_ERROR = -1;
	private static final int NO_ROWS_UPDATED = 0;
	private static final int NO_ROWS_DELETED = 0;

	private final String authority;
	private UriMatcher uriMatcher;
	private SQLiteOpenHelper databaseOpenHelper;
	private int lastResolverIndex;
	private SparseArray<PathConfig> pathsConfig;

	protected CentralProvider(String authority) {
		this.authority = authority;
	}

	@CallSuper
	@Override
	public boolean onCreate() {
		databaseOpenHelper = createDatabaseOpenHelper();
		buildUriMatcher();
		return true;
	}

	public UriMatcher buildUriMatcher() {
		pathsConfig = new SparseArray<>();
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		registerPaths();
		return uriMatcher;
	}

	protected abstract SQLiteOpenHelper createDatabaseOpenHelper();

	protected abstract void registerPaths();

	protected void registerPath(PathProvider pathProvider) {
		pathProvider.registerPaths(this);
	}

	public CommandRegister registerPath(String path, String type) {
		int pathIndex = nextResolverIndex();
		uriMatcher.addURI(authority, path, pathIndex);
		PathConfig pathConfig = new PathConfig(type);
		pathsConfig.put(pathIndex, pathConfig);
		return new CommandRegister(this, pathIndex);
	}

	void registerInsert(int pathIndex, InsertUriCommand insertUriResolver) {
		PathConfig pathConfig = getPathConfig(pathIndex);
		pathConfig.insertResolver = insertUriResolver;
	}

	void registerUpdate(int pathIndex, UpdateCommand updateCommand) {
		PathConfig pathConfig = getPathConfig(pathIndex);
		pathConfig.updateCommand = updateCommand;
	}

	void registerDelete(int pathIndex, DeleteCommand deleteCommand) {
		PathConfig pathConfig = getPathConfig(pathIndex);
		pathConfig.deleteCommand = deleteCommand;
	}

	void registerQuery(int pathIndex, QueryCommand queryCommand) {
		PathConfig pathConfig = getPathConfig(pathIndex);
		pathConfig.queryCommand = queryCommand;
	}

	void registerQueryAll(int pathIndex, QueryAllCommand queryAllCommand) {
		PathConfig pathConfig = getPathConfig(pathIndex);
		pathConfig.queryCommand = new QueryAllCommandDelegate(queryAllCommand);
	}

	void registerQueryItem(int pathIndex, QueryItemCommand queryItemCommand) {
		PathConfig pathConfig = getPathConfig(pathIndex);
		pathConfig.queryCommand = new QueryItemCommandDelegate(queryItemCommand);
	}

	void registerBuilkInsert(int pathIndex, InsertCommand insertCommand) {
		PathConfig pathConfig = getPathConfig(pathIndex);
		pathConfig.bulkInsertCommand = insertCommand;
	}

	private int nextResolverIndex() {
		return ++lastResolverIndex;
	}

	private PathConfig getPathConfig(int pathIndex) {
		final PathConfig pathConfig = pathsConfig.get(pathIndex);
		if (pathConfig == null)
			throw new NullPointerException("No PathConfig to index " + pathIndex);
		return pathConfig;
	}

	public PathConfig resolveUri(Uri uri) {
		final int match = uriMatcher.match(uri);
		final PathConfig pathConfig = pathsConfig.get(match);
		if (pathConfig == null)
			throw new UnsupportedOperationException("Unknown uri: " + uri);
		return pathConfig;
	}

	@Override
	public String getType(Uri uri) {
		final PathConfig pathConfig = resolveUri(uri);
		if (pathConfig.type == null)
			throw new UnsupportedOperationException("Uri not has type: " + uri);
		return pathConfig.type;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
						String sortOrder) {
		final PathConfig pathConfig = resolveUri(uri);
		if (pathConfig.queryCommand == null)
			throw new UnsupportedOperationException("Uri not suport query: " + uri);
		final SQLiteDatabase database = databaseOpenHelper.getReadableDatabase();
		Cursor cursor = pathConfig.queryCommand.query(database, uri, projection, selection, selectionArgs, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		final PathConfig pathConfig = resolveUri(uri);
		if (pathConfig.insertResolver == null)
			throw new UnsupportedOperationException("Uri not suport insert: " + uri);
		final SQLiteDatabase database = databaseOpenHelper.getWritableDatabase();
		long id = pathConfig.insertResolver.insert(database, uri, values);
		if (id == INSERT_ERROR)
			throw new android.database.SQLException("Failed to insert row into " + uri);
		Uri insertedUri = pathConfig.insertResolver.buildInsertedUri(id);
		getContext().getContentResolver().notifyChange(uri, null, false);
		return insertedUri;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final PathConfig pathConfig = resolveUri(uri);
		if (pathConfig.updateCommand == null)
			throw new UnsupportedOperationException("Uri not suport update: " + uri);
		final SQLiteDatabase database = databaseOpenHelper.getWritableDatabase();
		int rowsUpdated = pathConfig.updateCommand.update(database, uri, values, selection, selectionArgs);
		if (rowsUpdated != NO_ROWS_UPDATED)
			getContext().getContentResolver().notifyChange(uri, null, false);
		return rowsUpdated;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		final PathConfig pathConfig = resolveUri(uri);
		if (pathConfig.deleteCommand == null)
			throw new UnsupportedOperationException("Uri not suport delete: " + uri);
		if (selection == null) selection = "1";
		final SQLiteDatabase database = databaseOpenHelper.getWritableDatabase();
		int rowsDeleted = pathConfig.deleteCommand.delete(database, uri, selection, selectionArgs);
		if (rowsDeleted != NO_ROWS_DELETED)
			getContext().getContentResolver().notifyChange(uri, null, false);
		return rowsDeleted;
	}

	@Override
	public int bulkInsert(Uri uri, ContentValues[] values) {
		final PathConfig pathConfig = resolveUri(uri);
		if (pathConfig.bulkInsertCommand == null)
			return super.bulkInsert(uri, values);
		final SQLiteDatabase database = databaseOpenHelper.getWritableDatabase();
		int insertCount = 0;
		database.beginTransaction();
		try {
			for (ContentValues value : values) {
				long id = pathConfig.bulkInsertCommand.insert(database, uri, value);
				if (id != INSERT_ERROR) insertCount++;
			}
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
		if (insertCount > 0)
			getContext().getContentResolver().notifyChange(uri, null, false);
		return insertCount;
	}

	@Override
	@TargetApi(11)
	public void shutdown() {
		databaseOpenHelper.close();
		super.shutdown();
	}

	private static class QueryAllCommandDelegate implements QueryCommand {

		private QueryAllCommand queryAllCommand;

		public QueryAllCommandDelegate(QueryAllCommand queryAllCommand) {
			this.queryAllCommand = queryAllCommand;
		}

		@NonNull
		@Override
		public Cursor query(SQLiteDatabase database, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
			return queryAllCommand.queryAll(database, uri, projection, selection, selectionArgs, sortOrder);
		}
	}

	private static class QueryItemCommandDelegate implements QueryCommand {

		private QueryItemCommand queryItemCommand;

		public QueryItemCommandDelegate(QueryItemCommand queryItemCommand) {
			this.queryItemCommand = queryItemCommand;
		}

		@NonNull
		@Override
		public Cursor query(SQLiteDatabase database, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
			return queryItemCommand.queryItem(database, uri, projection, selection, selectionArgs, sortOrder);
		}
	}

}
