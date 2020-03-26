package com.eltiempo.mobiletest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artists {

    private List<Artist> artist;
    @SerializedName("@attr")
    private AttrTracks attr;

    public Artists() {
    }

    public Artists(List<Artist> artist, AttrTracks attr) {
        this.artist = artist;
        this.attr = attr;
    }

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }

    public AttrTracks getAttr() {
        return attr;
    }

    public void setAttr(AttrTracks attr) {
        this.attr = attr;
    }
}
