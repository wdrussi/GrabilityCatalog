package com.daniel.grabilitycatalog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 5/11/2016.
 */
public class IdAttribute {

    @Expose
    @SerializedName("im:id")
    String imId;

    @Expose
    @SerializedName("im:bundleId")
    String imBundleId;

    public String getImId() {
        return imId;
    }

    public void setImId(String imId) {
        this.imId = imId;
    }

    public String getImBundleId() {
        return imBundleId;
    }

    public void setImBundleId(String imBundleId) {
        this.imBundleId = imBundleId;
    }
}
