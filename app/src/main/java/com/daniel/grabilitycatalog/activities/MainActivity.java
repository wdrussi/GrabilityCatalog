package com.daniel.grabilitycatalog.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.daniel.grabilitycatalog.R;
import com.daniel.grabilitycatalog.fragments.PagerFragment;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private AlertDialog.Builder dialog;
    private PagerFragment pagerFragment;
    public static int current_fragment = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goMainScreen();
        if (LaunchActivity.IS_TABLET) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    /**
     *
     */
    private void goMainScreen() {
        pagerFragment = new PagerFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_main, pagerFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        dialog = new AlertDialog.Builder(this);
        dialog.setMessage(getResources().getString(R.string.exit_app_msg))
                .setCancelable(false)
                .setPositiveButton(R.string.yes_option, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.no_option, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = dialog.create();
        alert.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("Pager_Current", pagerFragment.mPagerHandler.getCurrentItem());
        getSupportFragmentManager().putFragment(outState, "pagerFragment", pagerFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        current_fragment = savedInstanceState.getInt("Pager_Current");
        pagerFragment = (PagerFragment) getSupportFragmentManager().getFragment(savedInstanceState, "pagerFragment");
        super.onRestoreInstanceState(savedInstanceState);
    }

}
