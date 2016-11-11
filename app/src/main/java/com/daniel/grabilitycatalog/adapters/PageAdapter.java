package com.daniel.grabilitycatalog.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.daniel.grabilitycatalog.R;
import com.daniel.grabilitycatalog.fragments.PagerFragment;
import com.daniel.grabilitycatalog.utils.Util;

/**
 * Created by Daniel on 5/11/2016.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    Context context;

    public PageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        return PagerFragment.viewFragments[i];
    }

    @Override
    public int getCount() {
        return PagerFragment.NUM_PAGES;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return getNamePage(context, position);
    }


    private String getNamePage(Context context, int category) {

        for (int i = 0; i < PagerFragment.viewFragments.length; i++) {
            if (i == category) {
                return Util.searchCategoryName(context, category);
            }
        }
        return null;
    }


}
