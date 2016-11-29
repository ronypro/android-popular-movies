package com.ronypro.android.database.helper;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class EntityCursorHelper<T> implements Iterable<T> {

	private CursorHelper mCursorHelper;
	private boolean markedToClose;

	public EntityCursorHelper(Cursor cursor) {
		this.mCursorHelper = new CursorHelper(cursor, getColumns());
	}

	public EntityCursorHelper<T> closeFinally() {
		markedToClose = true;
		return this;
	}

	public T getFirstModel() {
		if (mCursorHelper != null && mCursorHelper.moveToFirst()) {
			return getEntity();
		}
		return null;
	}

	public T getNextModel() {
		if (mCursorHelper != null && mCursorHelper.moveToNext()) {
			return getEntity();
		}
		return null;
	}

	public T getModelInPosition(int position) {
		if (mCursorHelper != null && mCursorHelper.moveToPosition(position)) {
			return getEntity();
		}
		return null;
	}

	public T getLastEntity() {
		if (mCursorHelper != null && mCursorHelper.moveToLast()) {
			return getEntity();
		}
		return null;
	}

	public List<T> getEntities() {
		List<T> entities = new ArrayList<>();
		T entity;
		while ((entity = getNextModel()) != null) {
			entities.add(entity);
		}
		return entities;
	}

	public List<T> getAllEntities() {
		List<T> entities = new ArrayList<>();
		if (mCursorHelper != null) {
			mCursorHelper.moveToPosition(-1);
			for (T entity : this) {
				entities.add(entity);
			}
		}
		return entities;
	}

	public T getSingleEntity() {
		try {
			return getNextModel();
		} finally {
			closeIfMarked();
		}
	}

	private void closeIfMarked() {
		if (markedToClose) {
			close();
		}
	}

	protected T getEntity() {
		return parseToModel(mCursorHelper);
	}

	public int getCount() {
		if (null == mCursorHelper) return 0;
		return mCursorHelper.getCount();
	}

	public void close() {
		mCursorHelper.close();
		mCursorHelper = null;
	}

	protected abstract String[] getColumns();

	protected abstract T parseToModel(CursorHelper cursorHelper);

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		if (mCursorHelper != null)
			return mCursorHelper.getCount();
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		mCursorHelper.moveToPosition(-1);
		return new MyIterator();
	}

	private class MyIterator implements Iterator<T> {

		@Override
		public boolean hasNext() {
			boolean hasNext;
			if (mCursorHelper.getPosition() < 0) {
				hasNext = mCursorHelper.getCount() > 0;
			} else {
				hasNext = mCursorHelper.getPosition() < mCursorHelper.getCount() - 1;
			}
			if (!hasNext) {
				closeIfMarked();
			}
			return hasNext;
		}

		@Override
		public T next() {
			try {
				return getNextModel();
			} catch (RuntimeException e) {
				closeIfMarked();
				throw e;
			}
		}

		@Override
		public void remove() {
			//Não suportado
		}

	}

}
