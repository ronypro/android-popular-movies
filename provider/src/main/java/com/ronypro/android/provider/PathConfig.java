package com.ronypro.android.provider;

/**
 * Created by Rahony on 17/08/2016.
 */
public class PathConfig {

    public final String type;
    public InsertUriCommand insertResolver;
    public UpdateCommand updateCommand;
    public DeleteCommand deleteCommand;
    public QueryCommand queryCommand;
    public InsertCommand bulkInsertCommand;

    public PathConfig(String type) {
        this.type = type;
    }
}
