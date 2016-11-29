package com.ronypro.android.database.helper;

import android.database.Cursor;
import android.os.Bundle;

import java.util.Date;

public class CursorHelper {

	private final Cursor cursor;

	private int[] columnsIndexes;
	private int columnIndex;

	public CursorHelper(Cursor cursor, String[] columns) {
		super();
		this.cursor = cursor;
		if (columns != null) {
			setColumnIndexesFromNames(columns);
		}
	}

	public CursorHelper(Cursor cursor) {
		this(cursor, null);
	}

	public void close() {
		this.cursor.close();
	}

	public byte[] getBlob(int columnIndex) {
		return this.cursor.getBlob(columnIndex);
	}

	public byte[] getBlob() {
		int idx = getColumnIndex();
		if (idx < 0)
			return null;
		return this.cursor.getBlob(idx);
	}

	public int getCount() {
		return this.cursor.getCount();
	}

	public double getDouble() {
		int idx = getColumnIndex();
		if (idx < 0)
			return 0d;
		return getDouble(idx);
	}

	public double getDouble(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return 0d;
		return this.cursor.getDouble(columnIndex);
	}

	public Bundle getExtras() {
		return this.cursor.getExtras();
	}

	public float getFloat() {
		int idx = getColumnIndex();
		if (idx < 0)
			return 0f;
		return getFloat(idx);
	}

	public float getFloat(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return 0f;
		return this.cursor.getFloat(columnIndex);
	}

	public int getInt() {
		int idx = getColumnIndex();
		if (idx < 0)
			return 0;
		return getInt(idx);
	}

	public int getInt(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return 0;
		return this.cursor.getInt(columnIndex);
	}

	public long getLong() {
		int idx = getColumnIndex();
		if (idx < 0)
			return 0l;
		return getLong(idx);
	}

	public long getLong(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return 0l;
		return this.cursor.getLong(columnIndex);
	}

	public short getShort() {
		int idx = getColumnIndex();
		if (idx < 0)
			return 0;
		return getShort(idx);
	}

	public short getShort(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return 0;
		return this.cursor.getShort(columnIndex);
	}

	public boolean getBoolean() {
		int idx = getColumnIndex();
		if (idx < 0)
			return false;
		return getBoolean(idx);
	}

	public boolean getBoolean(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return false;
		return this.cursor.getInt(columnIndex) == 1;
	}

	public String getString() {
		int idx = getColumnIndex();
		if (idx < 0)
			return null;
		return getString(idx);
	}

	public String getString(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return null;
		return this.cursor.getString(columnIndex);
	}

	public Date getDate() {
		int idx = getColumnIndex();
		if (idx < 0)
			return null;
		return getDate(idx);
	}

	public Date getDate(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return null;
		return new Date(this.cursor.getLong(columnIndex));
	}

	public Double getWDouble() {
		int idx = getColumnIndex();
		if (idx < 0)
			return null;
		return getWDouble(idx);
	}

	public Double getWDouble(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return null;
		return this.cursor.getDouble(columnIndex);
	}

	public Float getWFloat() {
		int idx = getColumnIndex();
		if (idx < 0)
			return null;
		return getWFloat(idx);
	}

	public Float getWFloat(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return null;
		return this.cursor.getFloat(columnIndex);
	}

	public Integer getWInt() {
		int idx = getColumnIndex();
		if (idx < 0)
			return null;
		return getWInt(idx);
	}

	public Integer getWInt(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return null;
		return this.cursor.getInt(columnIndex);
	}

	public Long getWLong() {
		int idx = getColumnIndex();
		if (idx < 0)
			return null;
		return getWLong(idx);
	}

	public Long getWLong(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return null;
		return this.cursor.getLong(columnIndex);
	}

	public Short getWShort() {
		int idx = getColumnIndex();
		if (idx < 0)
			return null;
		return getWShort(idx);
	}

	public Short getWShort(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return null;
		return this.cursor.getShort(columnIndex);
	}

	public Boolean getWBoolean() {
		int idx = getColumnIndex();
		if (idx < 0)
			return null;
		return getWBoolean(idx);
	}

	public Boolean getWBoolean(int columnIndex) {
		if (this.cursor.isNull(columnIndex))
			return null;
		return this.cursor.getInt(columnIndex) == 1;
	}

	public int getType(int columnIndex) {
		return this.cursor.getType(columnIndex);
	}

	public boolean getWantsAllOnMoveCalls() {
		return this.cursor.getWantsAllOnMoveCalls();
	}

	public boolean isAfterLast() {
		return this.cursor.isAfterLast();
	}

	public boolean isBeforeFirst() {
		return this.cursor.isBeforeFirst();
	}

	public boolean isClosed() {
		return this.cursor.isClosed();
	}

	public boolean isFirst() {
		return this.cursor.isFirst();
	}

	public boolean isLast() {
		return this.cursor.isLast();
	}

	public boolean isNull(int columnIndex) {
		return this.cursor.isNull(columnIndex);
	}

	public boolean move(int offset) {
		this.columnIndex = 0;
		return this.cursor.move(offset);
	}

	public boolean moveToFirst() {
		this.columnIndex = 0;
		return this.cursor.moveToFirst();
	}

	public boolean moveToLast() {
		this.columnIndex = 0;
		return this.cursor.moveToLast();
	}

	public boolean moveToNext() {
		this.columnIndex = 0;
		return this.cursor.moveToNext();
	}

	public boolean moveToPosition(int position) {
		this.columnIndex = 0;
		return this.cursor.moveToPosition(position);
	}

	public boolean moveToPrevious() {
		this.columnIndex = 0;
		return this.cursor.moveToPrevious();
	}

	private int getColumnIndex() {
		int i = this.columnIndex++;
		if (this.columnsIndexes == null)
			return i;
		if (i < this.columnsIndexes.length)
			return this.columnsIndexes[i];
		return -1;
	}

	private int[] getColumnIndexes(String[] columns) {
		int[] indexes = new int[columns.length];
		int i = 0;
		int space;
		int point;
		for (String column : columns) {
			space = column.lastIndexOf(' ');
			if (space > -1) {
				column = column.substring(space + 1);
			} else {
				point = column.indexOf('.');
				if (point > -1) {
					column = column.substring(point + 1);
				}
			}
			indexes[i++] = this.cursor != null? this.cursor.getColumnIndex(column) : -1;
		}
		return indexes;
	}

	public void setColumnIndexesFromNames(String[] columns) {
		setColumnIndexes(getColumnIndexes(columns));
	}

	private void setColumnIndexes(int[] columnsIndexes) {
		this.columnsIndexes = columnsIndexes;
	}

	public void gotoFirstColumn() {
		this.columnIndex = 0;
	}

	public void skipColumn() {
		skipColumns(1);
	}

	public void skipColumns(int columns) {
		this.columnIndex += columns;
	}

	public int getPosition() {
		return this.cursor.getPosition();
	}

}
