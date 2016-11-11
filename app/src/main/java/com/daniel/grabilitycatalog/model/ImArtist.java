package com.daniel.grabilitycatalog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 5/11/2016.
 */
public class ImArtist {

    @Expose
    @SerializedName("label")
    String label;

    @Expose
    @SerializedName("attributes")
    ImArtistAttribute attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImArtistAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(ImArtistAttribute attributes) {
        this.attributes = attributes;
    }
}
