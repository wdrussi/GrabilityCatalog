package com.daniel.grabilitycatalog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 5/11/2016.
 */
public class ImContentType {

    @Expose
    @SerializedName("attributes")
    ContentTypeAttribute attributes;

    public ContentTypeAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(ContentTypeAttribute attributes) {
        this.attributes = attributes;
    }
}
