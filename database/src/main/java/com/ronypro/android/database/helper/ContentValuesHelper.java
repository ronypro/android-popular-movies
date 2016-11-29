package com.ronypro.android.database.helper;

import android.content.ContentValues;
import android.os.Parcel;

import java.util.Date;
import java.util.Map.Entry;
import java.util.Set;

public class ContentValuesHelper {

	private final ContentValues contentValues;

	public ContentValuesHelper() {
		this(new ContentValues());
	}

	public ContentValuesHelper(ContentValues contentValues) {
		super();
		this.contentValues = contentValues;
	}

	public void clear() {
		this.contentValues.clear();
	}

	public boolean containsKey(String key) {
		return this.contentValues.containsKey(key);
	}

	public int describeContents() {
		return this.contentValues.describeContents();
	}

	@Override
	public boolean equals(Object object) {
		return this.contentValues.equals(object);
	}

	public Object get(String key) {
		return this.contentValues.get(key);
	}

	public Boolean getAsBoolean(String key) {
		return this.contentValues.getAsBoolean(key);
	}

	public Byte getAsByte(String key) {
		return this.contentValues.getAsByte(key);
	}

	public byte[] getAsByteArray(String key) {
		return this.contentValues.getAsByteArray(key);
	}

	public Double getAsDouble(String key) {
		return this.contentValues.getAsDouble(key);
	}

	public Float getAsFloat(String key) {
		return this.contentValues.getAsFloat(key);
	}

	public Integer getAsInteger(String key) {
		return this.contentValues.getAsInteger(key);
	}

	public Long getAsLong(String key) {
		return this.contentValues.getAsLong(key);
	}

	public Short getAsShort(String key) {
		return this.contentValues.getAsShort(key);
	}

	public String getAsString(String key) {
		return this.contentValues.getAsString(key);
	}

	public Date getAsDate(String key) {
		Long ms = this.contentValues.getAsLong(key);
		if (ms == null)
			return null;
		return new Date(ms);
	}

	@Override
	public int hashCode() {
		return this.contentValues.hashCode();
	}

	public Set<String> keySet() {
		return this.contentValues.keySet();
	}

	public void put(String key, Boolean value) {
		this.contentValues.put(key, value);
	}

	public void put(String key, Byte value) {
		this.contentValues.put(key, value);
	}

	public void put(String key, byte[] value) {
		this.contentValues.put(key, value);
	}

	public void put(String key, Double value) {
		this.contentValues.put(key, value);
	}

	public void put(String key, Float value) {
		this.contentValues.put(key, value);
	}

	public void put(String key, Integer value) {
		this.contentValues.put(key, value);
	}

	public void put(String key, Long value) {
		this.contentValues.put(key, value);
	}

	public void put(String key, Short value) {
		this.contentValues.put(key, value);
	}

	public void put(String key, String value) {
        if (value == null || value.isEmpty())
            this.contentValues.putNull(key);
        else
		    this.contentValues.put(key, value);
	}

	public void put(String key, Date date) {
		if (date == null)
			this.contentValues.putNull(key);
		else
			this.contentValues.put(key, date.getTime());
	}

	public void putAll(ContentValues other) {
		this.contentValues.putAll(other);
	}

	public void putNull(String key) {
		this.contentValues.putNull(key);
	}

	public void remove(String key) {
		this.contentValues.remove(key);
	}

	public int size() {
		return this.contentValues.size();
	}

	@Override
	public String toString() {
		return this.contentValues.toString();
	}

	public Set<Entry<String, Object>> valueSet() {
		return this.contentValues.valueSet();
	}

	public void writeToParcel(Parcel parcel, int flags) {
		this.contentValues.writeToParcel(parcel, flags);
	}

	public ContentValues getContentValues() {
		return this.contentValues;
	}

}
