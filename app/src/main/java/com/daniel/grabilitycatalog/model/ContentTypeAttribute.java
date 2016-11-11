package com.daniel.grabilitycatalog.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 5/11/2016.
 */

public class ContentTypeAttribute {


    @Expose
    @SerializedName("term")
    String term;

    @Expose
    @SerializedName("label")
    String label;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}
