package com.daniel.grabilitycatalog.activities;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.daniel.grabilitycatalog.R;
import com.daniel.grabilitycatalog.interfaces.ICatalogResponse;
import com.daniel.grabilitycatalog.network.CatalogTask;
import com.daniel.grabilitycatalog.service.AppProvider;
import com.daniel.grabilitycatalog.utils.Util;

/**
 * Created by Daniel on 5/11/2016.
 */
public class LaunchActivity extends AppCompatActivity implements ICatalogResponse {


    private final static String TAG = MainActivity.class.getName();
    private String url ;
    Intent intent;

    AppProvider appProvider;

    private boolean existData;

    public static boolean IS_TABLET = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        if (IS_TABLET) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        setContentView(R.layout.activity_launch_screen);

        if (savedInstanceState == null) {
            if (Util.validateCatalog(this)) {
                existData = true;
                getCatalog(url,  existData);
            } else {
                existData = false;
                getCatalog(url,  existData);
            }
        }
    }

    public void init(){
        url = getResources().getString(R.string.string_url);
        IS_TABLET = Util.isTablet(this);
        appProvider = new AppProvider();
        existData = false;
    }

    private void getCatalog(String url,boolean existData) {
        CatalogTask catalogTask = new CatalogTask(this,this);
        catalogTask.execute(url,existData);
    }

    @Override
    public void responseCatalog(Boolean response) {
        if (response) {
            goToMainActivity();
        } else {
            Log.d(TAG, "Fail Response");
        }
    }

    /**
     *
     */
    private void goToMainActivity() {
        intent = new Intent(LaunchActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }



}
