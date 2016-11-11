package com.daniel.grabilitycatalog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 5/11/2016.
 */
public class Link {

    @Expose
    @SerializedName("attributes")
    LinkAttribute attributes;

    public LinkAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(LinkAttribute attributes) {
        this.attributes = attributes;
    }
}
