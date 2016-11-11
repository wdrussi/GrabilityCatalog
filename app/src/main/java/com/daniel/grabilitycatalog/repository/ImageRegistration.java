package com.daniel.grabilitycatalog.repository;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.daniel.grabilitycatalog.entities.ImageEntity;
import com.daniel.grabilitycatalog.model.Entry;
import com.daniel.grabilitycatalog.model.ImImage;

/**
 * Created by Daniel on 5/11/2016.
 */

public class ImageRegistration{


    private final static String LOG_TAG = ImageRegistration.class.getSimpleName();


    private static ImageRegistration imageRegistrationInstance = new ImageRegistration();


    private ImageRegistration() {
    }


    public static ImageRegistration getImageRegistrationInstance() {
        return imageRegistrationInstance;
    }


    AppRepository appRepository = AppRepository.getAppRepoInstance();


    public void createImage(Context context, Entry entry) {

        ContentValues values = new ContentValues();
        String appId = appRepository.getAppByIdentifier(context, entry.getId().getAttributes().getImId());

        for (ImImage image : entry.getImImages()) {
            values.put(ImageEntity.APP_ID, appId);
            values.put(ImageEntity.LABEL, image.getLabel());
            values.put(ImageEntity.HEIGHT, image.getAttributes().getHeight());


            Uri uri = context.getContentResolver().insert(
                    ImageEntity.CONTENT_URI, values);
        }


    }
}
