package com.daniel.grabilitycatalog.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.daniel.grabilitycatalog.R;
import com.daniel.grabilitycatalog.model.CategoryAttribute;
import com.daniel.grabilitycatalog.repository.AppRepository;
import com.daniel.grabilitycatalog.repository.CategoryRepository;
import com.daniel.grabilitycatalog.repository.ImageRepository;

import java.util.List;

/**
 * Created by Daniel on 5/11/2016.
 */

public class Util {

        public static boolean validateCatalog(Context context) {
            boolean existData = false;
            AppRepository appRepository = AppRepository.getAppRepoInstance();
            CategoryRepository categoryRepository = CategoryRepository.getCateRepoInstance();
            ImageRepository imageRepository = ImageRepository.getImageRepoInstance();

            Long countApps = appRepository.getCountApps(context);
            Long countCategories = categoryRepository.getCountCategories(context);
            Long countImages = imageRepository.getCountCategories(context);

            if (countApps != 0 && countCategories != 0 && countImages != 0) {
                existData = true;
            } else {
                existData = false;
            }
            return existData;
        }

        public static String searchCategoryName(Context context, int category) {
            String categoryName = "";
            CategoryRepository categoryRepository = CategoryRepository.getCateRepoInstance();
            List<CategoryAttribute> categories = categoryRepository.getCategories(context);
            for (CategoryAttribute c : categories) {
                if ((category+1) == Integer.parseInt(c.getImId())) {
                    categoryName = c.getLabel();
                    break;
                }
            }
            return categoryName;
        }

        public static String getShortAppName(String largeAppName){
            String shortAppName = "";
            String[] appName = largeAppName.split("-");
            shortAppName = appName[0];
            return shortAppName;
        }


        public static boolean isTablet(Context context) {
            return (context.getApplicationContext().getResources().getConfiguration().screenLayout
                    & Configuration.SCREENLAYOUT_SIZE_MASK)
                    >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        }

        public static boolean isConnect(Context context,int duration){
            ConnectivityManager connec = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                    connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

                return true;

            } else if (
                    connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                            connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

                Toast toast = Toast.makeText(context, context.getApplicationContext().getResources().getString(R.string.offline), duration);
                toast.show();
                return false;
            }
            return false;
        }




}
