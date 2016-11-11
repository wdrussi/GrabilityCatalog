package com.daniel.grabilitycatalog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 5/11/2016.
 */
public class ImPrice {

    @Expose
    @SerializedName("label")
    String label;

    @Expose
    @SerializedName("attributes")
    PriceAttribute attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public PriceAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(PriceAttribute attributes) {
        this.attributes = attributes;
    }
}
