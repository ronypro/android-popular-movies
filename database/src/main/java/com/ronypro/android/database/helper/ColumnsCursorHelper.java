package com.ronypro.android.database.helper;

import android.database.Cursor;
import android.os.Bundle;

import java.util.Date;

public class ColumnsCursorHelper extends CursorHelper {

	private int[] columnsIndexes;
	private int columnIndex;

	public ColumnsCursorHelper(Cursor cursor, String[] columns) {
		super(cursor);
		if (columns != null) {
			setColumnIndexesFromNames(columns);
		}
	}

	public boolean move(int offset) {
		this.columnIndex = 0;
		return super.move(offset);
	}

	public boolean moveToFirst() {
		this.columnIndex = 0;
		return super.moveToFirst();
	}

	public boolean moveToLast() {
		this.columnIndex = 0;
		return super.moveToLast();
	}

	public boolean moveToNext() {
		this.columnIndex = 0;
		return super.moveToNext();
	}

	public boolean moveToPosition(int position) {
		this.columnIndex = 0;
		return super.moveToPosition(position);
	}

	public boolean moveToPrevious() {
		this.columnIndex = 0;
		return super.moveToPrevious();
	}

	protected int getColumnIndex() {
		int i = this.columnIndex++;
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
			indexes[i++] = getColumnIndex(column);
		}
		return indexes;
	}

	private void setColumnIndexesFromNames(String[] columns) {
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

}
