package com.daniel.grabilitycatalog.repository;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.daniel.grabilitycatalog.entities.AppEntity;
import com.daniel.grabilitycatalog.model.Entry;

/**
 * Created by Daniel on 5/11/2016.
 */

public class AppRegistration {


    private final static String LOG_TAG = AppRegistration.class.getSimpleName();


    private static AppRegistration appRegistrationInstance = new AppRegistration();


    private AppRegistration() {
    }


    public static AppRegistration getAppRegistrationInstance() {
        return appRegistrationInstance;
    }


    CategoryRepository categoryRepository = CategoryRepository.getCateRepoInstance();
    CategoryRegistration categoryRegistration = CategoryRegistration.getCatRegistrationInstance();


    public void createApp(Context context, Entry entry) {

        ContentValues values = new ContentValues();
        String categoryId = categoryRepository.getCategoryByIdentifier(context, entry.getCategory().getAttributes().getImId());
        if (categoryId == null) {
            categoryRegistration.createCategory(context, entry.getCategory());
            categoryId = categoryRepository.getCategoryByIdentifier(context, entry.getCategory().getAttributes().getImId());
        }

        values.put(AppEntity.CATEGORY_ID, categoryId);
        values.put(AppEntity.NAME, entry.getImName().getLabel());
        values.put(AppEntity.SUMMARY, entry.getSummary().getLabel());
        values.put(AppEntity.PRICE, entry.getImPrice().getAttributes().getAmount());
        values.put(AppEntity.RIGHTS, entry.getRights().getLabel());
        values.put(AppEntity.TITLE, entry.getTitle().getLabel());
        values.put(AppEntity.LINK, entry.getLink().getAttributes().getHref());
        values.put(AppEntity.IDENTIFIER, entry.getId().getAttributes().getImId());
        values.put(AppEntity.RELEASE_DATE, entry.getImReleaseDate().getLabel());

        Uri uri = context.getContentResolver().insert(
                AppEntity.CONTENT_URI, values);
    }
}
