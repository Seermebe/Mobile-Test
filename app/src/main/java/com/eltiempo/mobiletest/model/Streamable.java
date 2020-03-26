package com.eltiempo.mobiletest.model;

import com.google.gson.annotations.SerializedName;

public class Streamable {

    @SerializedName("#text")
    private String text;
    private String fulltrack;

    public Streamable() {
    }

    public Streamable(String text, String fulltrack) {
        this.text = text;
        this.fulltrack = fulltrack;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFulltrack() {
        return fulltrack;
    }

    public void setFulltrack(String fulltrack) {
        this.fulltrack = fulltrack;
    }
}
