package com.daniel.grabilitycatalog.managers;

import android.net.Uri;

/**
 * Created by Daniel on 5/11/2016.
 */

public class DatabaseContract {
    public static final String CONTENT_AUTHORITY = "com.daniel.grabilitycatalog";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_APP = "app";
    public static final String PATH_CATEGORY = "category";
    public static final String PATH_IMAGE = "image";

}