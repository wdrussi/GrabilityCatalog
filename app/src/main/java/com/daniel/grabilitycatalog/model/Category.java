package com.daniel.grabilitycatalog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 5/11/2016.
 */

public class Category {

    @Expose
    @SerializedName("attributes")
    CategoryAttribute attributes;

    public CategoryAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(CategoryAttribute attributes) {
        this.attributes = attributes;
    }
}
