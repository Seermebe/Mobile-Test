package com.eltiempo.mobiletest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tracks {

    private List<Track> track;
    @SerializedName("@attr")
    private AttrTracks attr;

    public Tracks() {
    }

    public Tracks(List<Track> track, AttrTracks attr) {
        this.track = track;
        this.attr = attr;
    }

    public List<Track> getTrack() {
        return track;
    }

    public void setTrack(List<Track> track) {
        this.track = track;
    }

    public AttrTracks getAttr() {
        return attr;
    }

    public void setAttr(AttrTracks attr) {
        this.attr = attr;
    }
}
