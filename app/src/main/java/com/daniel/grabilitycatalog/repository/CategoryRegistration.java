package com.daniel.grabilitycatalog.repository;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.daniel.grabilitycatalog.entities.CategoryEntity;
import com.daniel.grabilitycatalog.model.Category;

/**
 * Created by Daniel on 5/11/2016.
 */

public class CategoryRegistration {


    private final static String LOG_TAG = CategoryRegistration.class.getSimpleName();


    private static CategoryRegistration categoryRegistrationInstance = new CategoryRegistration();


    private CategoryRegistration() {
    }


    public static CategoryRegistration getCatRegistrationInstance() {
        return categoryRegistrationInstance;
    }


    public void createCategory(Context context, Category category) {

        ContentValues values = new ContentValues();
        values.put(CategoryEntity.IDENTIFIER, category.getAttributes().getImId());
        values.put(CategoryEntity.TERM, category.getAttributes().getTerm());
        values.put(CategoryEntity.LABEL, category.getAttributes().getLabel());

        Uri uri = context.getContentResolver().insert(
                CategoryEntity.CONTENT_URI, values);
    }
}
