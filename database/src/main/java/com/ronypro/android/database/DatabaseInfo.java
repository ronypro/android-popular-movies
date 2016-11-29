package com.ronypro.android.database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInfo {

	public static final String DEFAULT_ASSETS_ROOT_DIR = "db";
	public static final String DEFAULT_SQL_VERSION_DIR = "version_%d";

	public final String name;
	public final String dirName;
	public final int version;
	public final String assetsDir;
	public final String sqlVersionDir;

	public DatabaseInfo(String name, int version, String assetsDir, String sqlVersionDir) {
		super();
		this.name = name;
		this.version = version;
		this.dirName = getFileName(name).replaceAll("\\.", "_");
		this.assetsDir = assetsDir != null ? assetsDir : DEFAULT_ASSETS_ROOT_DIR + File.separator + this.dirName;
		this.sqlVersionDir = sqlVersionDir != null ? sqlVersionDir : DEFAULT_SQL_VERSION_DIR;
	}

	public DatabaseInfo(String name, int version, String assetsDir) {
		this(name, version, assetsDir, null);
	}

	public DatabaseInfo(String name, int version) {
		this(name, version, null, null);
	}

	public List<DatabaseUpdater> createUpdaters() {
        return new ArrayList<>();
    }

    private static String getFileName(String fileName) {
        int idx = fileName.lastIndexOf('.');
        if (idx < 0)
            return fileName;
        return fileName.substring(0, idx);
    }

}
