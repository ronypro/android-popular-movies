package com.ronypro.android.provider;

/**
 * Created by Rahony on 17/08/2016.
 */
public class CommandRegister {

    private final CentralProvider provider;
    private final int pathIndex;

    public CommandRegister(CentralProvider provider, int pathIndex) {
        this.provider = provider;
        this.pathIndex = pathIndex;
    }

    public CommandRegister registerInsert(InsertUriCommand insertUriComand) {
        provider.registerInsert(pathIndex, insertUriComand);
        return this;
    }

    public CommandRegister registerUpdate(UpdateCommand updateCommand) {
        provider.registerUpdate(pathIndex, updateCommand);
        return this;
    }

    public CommandRegister registerDelete(DeleteCommand deleteCommand) {
        provider.registerDelete(pathIndex, deleteCommand);
        return this;
    }

    public CommandRegister registerQuery(QueryCommand queryCommand) {
        provider.registerQuery(pathIndex, queryCommand);
        return this;
    }

    public CommandRegister registerQueryAll(QueryAllCommand queryAllCommand) {
        provider.registerQueryAll(pathIndex, queryAllCommand);
        return this;
    }

    public CommandRegister registerQueryItem(QueryItemCommand queryItemCommand) {
        provider.registerQueryItem(pathIndex, queryItemCommand);
        return this;
    }

    public CommandRegister registerBuilkInsert(InsertCommand insertCommand) {
        provider.registerBuilkInsert(pathIndex, insertCommand);
        return this;
    }
    
}
