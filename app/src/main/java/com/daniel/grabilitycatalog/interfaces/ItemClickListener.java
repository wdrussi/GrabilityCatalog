package com.daniel.grabilitycatalog.interfaces;

import android.view.View;

import com.daniel.grabilitycatalog.model.ApplicationData;

/**
 * Created by Daniel on 5/11/2016.
 */

public interface ItemClickListener {

    public void onItemClicked(View view, int position, ApplicationData data);
}