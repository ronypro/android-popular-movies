package com.ronypro.android.database.helper;

import android.database.Cursor;
import android.os.Bundle;

import java.util.Date;

public class ProjectionCursorHelper extends CursorHelper {

	private int columnIndex;

	public ProjectionCursorHelper(Cursor cursor) {
		super(cursor);
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
		return this.columnIndex++;
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
