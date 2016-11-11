package com.daniel.grabilitycatalog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 5/11/2016.
 */
public class LinkAttribute {

    @Expose
    @SerializedName("rel")
    String rel;

    @Expose
    @SerializedName("type")
    String type;

    @Expose
    @SerializedName("href")
    String href;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
