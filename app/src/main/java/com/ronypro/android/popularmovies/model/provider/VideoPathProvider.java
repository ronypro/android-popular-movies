package com.ronypro.android.popularmovies.model.provider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Pair;

import com.ronypro.android.popularmovies.contract.database.MoviesContract;
import com.ronypro.android.provider.CentralProvider;
import com.ronypro.android.provider.DeleteCommand;
import com.ronypro.android.provider.QueryCommand;
import com.ronypro.android.provider.TablePathProvider;

/**
 * Created by rahony on 28/11/16.
 */

public class VideoPathProvider extends TablePathProvider {

    private static final String PATH_VIDEO = MoviesContract.PATH_VIDEO;
    private static final String PATH_VIDEO_BY_ID = MoviesContract.PATH_VIDEO + "/#";
    private static final String PATH_VIDEO_BY_MOVIE_ID = MoviesContract.PATH_VIDEO + "/" + MoviesContract.PATH_MOVIE + "/#";

    private static final String SELECTION_BY_MOVIE_ID = MoviesContract.VideoEntry.COLUMN_MOVIE_ID + "=?";
    private static final int MOVIE_ID_POSITION_IN_URI = 2;

    @Override
    protected Pair<String, String> getPathAndType() {
        return new Pair<>(PATH_VIDEO, MoviesContract.VideoEntry.CONTENT_TYPE);
    }

    @Override
    protected Pair<String, String> getItemPathAndType() {
        return new Pair<>(PATH_VIDEO_BY_ID, MoviesContract.VideoEntry.CONTENT_ITEM_TYPE);
    }

    @Override
    protected String getTableName() {
        return MoviesContract.VideoEntry.TABLE_NAME;
    }

    @Override
    public Uri buildInsertedUri(long id) {
        return MoviesContract.VideoEntry.buildVideoUri(id);
    }

    @Override
    public void registerPaths(CentralProvider provider) {
        super.registerPaths(provider);
        provider.registerPath(PATH_VIDEO_BY_MOVIE_ID, MoviesContract.VideoEntry.CONTENT_TYPE)
                .registerQuery(new QueryCommand() {
                    @NonNull
                    @Override
                    public Cursor query(SQLiteDatabase database, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
                        return queryByMovie(database, uri, projection, sortOrder);
                    }
                })
                .registerDelete(new DeleteCommand() {
                    @Override
                    public int delete(SQLiteDatabase database, Uri uri, String selection, String[] selectionArgs) {
                        return deleteByMovie(database, uri);
                    }
                });
    }

    private Cursor queryByMovie(SQLiteDatabase database, Uri uri, String[] projection, String sortOrder) {
        String movieId = getIdFromUri(uri, MOVIE_ID_POSITION_IN_URI);
        return queryAll(database, uri, projection, SELECTION_BY_MOVIE_ID, new String[] { movieId }, sortOrder);
    }

    private int deleteByMovie(SQLiteDatabase database, Uri uri) {
        String movieId = getIdFromUri(uri, MOVIE_ID_POSITION_IN_URI);
        return delete(database, uri, SELECTION_BY_MOVIE_ID, new String[] { movieId });
    }
}
