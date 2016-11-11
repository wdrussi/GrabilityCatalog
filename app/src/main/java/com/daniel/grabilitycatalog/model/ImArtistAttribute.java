package com.daniel.grabilitycatalog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 5/11/2016.
 */
public class ImArtistAttribute {

    @Expose
    @SerializedName("href")
    String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
