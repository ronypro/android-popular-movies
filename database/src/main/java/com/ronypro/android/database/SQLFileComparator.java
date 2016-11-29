package com.ronypro.android.database;

import java.util.Comparator;

public class SQLFileComparator implements Comparator<String> {

	@Override
	public int compare(String file1, String file2) {
		int ordem1 = getOrdem(file1);
		int ordem2 = getOrdem(file2);
		return ordem1 - ordem2;
	}

	private int getOrdem(String file) {
		String ordem = file.substring(0, file.indexOf('_'));
		return Integer.parseInt(ordem);
	}
	
}
