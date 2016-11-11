package com.daniel.grabilitycatalog.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.daniel.grabilitycatalog.entities.CategoryEntity;
import com.daniel.grabilitycatalog.helpers.AppsDBHelper;
import com.daniel.grabilitycatalog.model.CategoryAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 5/11/2016.
 */

public class CategoryRepository {

    private final static String LOG_TAG = CategoryRepository.class.getSimpleName();

    private static CategoryRepository categoryRepoInstance = new CategoryRepository();

    private CategoryRepository() {

    }

    public static CategoryRepository getCateRepoInstance() {
        return categoryRepoInstance;
    }


    public String getCategoryByIdentifier(Context context, String categoryIdentifier) {
        AppsDBHelper appsDBHelper = new AppsDBHelper(context);
        String categoryId = null;
        SQLiteDatabase db = appsDBHelper.getReadableDatabase();
        if (db == null) {
            categoryId = null;
        }

        String query = "SELECT * FROM " + CategoryEntity.TABLE_NAME + " where " + CategoryEntity.IDENTIFIER + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{categoryIdentifier});

        if (cursor.moveToFirst()) {
            do {
                categoryId = cursor.getString(cursor.getColumnIndex(CategoryEntity._ID));
            } while (cursor.moveToNext());
        }

        return categoryId;
    }


    public Long getCountCategories(Context context) {
        Long count = null;
        AppsDBHelper appsDBHelper = new AppsDBHelper(context);
        SQLiteDatabase db = appsDBHelper.getReadableDatabase();

        String query = "SELECT count(c._id) FROM " + CategoryEntity.TABLE_NAME + " c";

        Cursor cursor = db.rawQuery(query, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                count = cursor.getLong(0);
            } while (cursor.moveToNext());
        }

        return count;
    }


    public List<CategoryAttribute> getCategories(Context context) {
        List<CategoryAttribute> categoryAttributes = new ArrayList<>();
        AppsDBHelper appsDBHelper = new AppsDBHelper(context);
        SQLiteDatabase db = appsDBHelper.getReadableDatabase();

        String query = "SELECT * FROM " + CategoryEntity.TABLE_NAME + " c";

        Cursor cursor = db.rawQuery(query, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                CategoryAttribute categoryAttribute = new CategoryAttribute();
                categoryAttribute.setImId(cursor.getString(cursor.getColumnIndex(CategoryEntity._ID)));
                categoryAttribute.setLabel(cursor.getString(cursor.getColumnIndex(CategoryEntity.LABEL)));
                categoryAttributes.add(categoryAttribute);
            } while (cursor.moveToNext());
        }

        return categoryAttributes;
    }
}
