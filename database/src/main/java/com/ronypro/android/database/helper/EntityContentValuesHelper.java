package com.ronypro.android.database.helper;

import android.content.ContentValues;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public abstract class EntityContentValuesHelper<T> {

	@Retention(RetentionPolicy.SOURCE)
	@IntDef({INSERT, UPDATED})
	public @interface ModelParseOperation {
	}

	public static final int INSERT = 0;
	public static final int UPDATED = 1;

	public ContentValues getValuesForInsert(T entity) {
		return getValues(entity, INSERT).getContentValues();
	}

	public ContentValues[] getMultipleValuesForInsert(List<T> entitiesList) {
		int size = entitiesList.size();
		ContentValues contentValues[] = new ContentValues[size];
		for (int i = 0; i < size; i++) {
			contentValues[i] = getValues(entitiesList.get(i), INSERT).getContentValues();
		}
		return contentValues;
	}

	public ContentValues getValuesForUpdate(T entity) {
		return getValues(entity, UPDATED).getContentValues();
	}

	protected abstract ContentValuesHelper getValues(T entity, @ModelParseOperation int operation);

}
