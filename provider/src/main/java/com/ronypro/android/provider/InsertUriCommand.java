package com.ronypro.android.provider;

import android.net.Uri;

/**
 * Created by Rahony on 17/08/2016.
 */
public interface InsertUriCommand extends InsertCommand {

    Uri buildInsertedUri(long id);

}
