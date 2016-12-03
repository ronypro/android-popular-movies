package com.ronypro.android.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.util.Pair;

/**
 * Created by Rahony on 16/08/2016.
 */
public abstract class TablePathProvider implements PathProvider, InsertUriCommand, UpdateCommand, DeleteCommand, QueryAllCommand, QueryItemCommand, UpdateItemCommand, DeleteItemCommand {

    private static final int DEFAULT_ID_POSITION_IN_ITEM_URI = 1;

    @CallSuper
    @Override
    public void registerPaths(CentralProvider provider) {
        final Pair<String, String> pathPair = getPathAndType();
        final String path = pathPair.first;
        final String pathType = pathPair.second;

        final Pair<String, String> itemPathPair = getItemPathAndType();
        final String itemPath = itemPathPair.first;
        final String itemPathType = itemPathPair.second;

        provider.registerPath(path, pathType)
                .registerInsert(this)
                .registerBuilkInsert(this)
                .registerUpdate(this)
                .registerDelete(this)
                .registerQueryAll(this);

        provider.registerPath(itemPath, itemPathType)
                .registerUpdateItem(this)
                .registerDeleteItem(this)
                .registerQueryItem(this);
    }

    public long insert(SQLiteDatabase database, Uri uri, ContentValues values) {
        return database.insert(getTableName(), null, values);
    }

    @Override
    public int update(SQLiteDatabase database, Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return database.update(getTableName(), values, selection,
                selectionArgs);
    }

    @Override
    public int updateItem(SQLiteDatabase database, Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return database.update(
                getTableName(),
                values,
                getIdColumnName() + " = ?",
                new String[]{getIdFromUri(uri)}
        );
    }

    @Override
    public int delete(SQLiteDatabase database, Uri uri, String selection, String[] selectionArgs) {
        return database.delete(getTableName(), selection, selectionArgs);
    }

    @Override
    public int deleteItem(SQLiteDatabase database, Uri uri, String selection, String[] selectionArgs) {
        return database.delete(
                getTableName(),
                getIdColumnName() + " = ?",
                new String[]{getIdFromUri(uri)}
        );
    }

    @NonNull
    @Override
    public Cursor queryAll(SQLiteDatabase database, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return database.query(
                getTableName(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    @NonNull
    @Override
    public Cursor queryItem(SQLiteDatabase database, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return database.query(
                getTableName(),
                projection,
                getIdColumnName() + " = ?",
                new String[]{getIdFromUri(uri)},
                null,
                null,
                null
        );
    }

    protected abstract Pair<String,String> getPathAndType();

    protected abstract Pair<String,String> getItemPathAndType();

    protected abstract String getTableName();

    protected String getIdColumnName() {
        return BaseColumns._ID;
    }

    protected String getIdFromUri(Uri uri) {
        return getIdFromUri(uri, DEFAULT_ID_POSITION_IN_ITEM_URI);
    }

    protected String getIdFromUri(Uri uri, int position) {
        return uri.getPathSegments().get(position);
    }

}
