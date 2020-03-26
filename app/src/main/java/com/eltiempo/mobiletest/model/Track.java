package com.eltiempo.mobiletest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Track {

    private String name;
    private String duration;
    private String listeners;
    private String mbid;
    private String url;
    private Streamable streamable;
    private Artist artist;
    private List<Image> image;
    @SerializedName("@attr")
    private AttrTrack attr;

    public Track() {
    }

    public Track(String name, String duration, String listeners, String mbid, String url, Streamable streamable, Artist artist, List<Image> image, AttrTrack attr) {
        this.name = name;
        this.duration = duration;
        this.listeners = listeners;
        this.mbid = mbid;
        this.url = url;
        this.streamable = streamable;
        this.artist = artist;
        this.image = image;
        this.attr = attr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        int d = Integer.parseInt(duration);
        int minutos = d / 60;
        int segundos = d - minutos * 60;

        return redondear(minutos) + ":" + redondear(segundos);
    }

    private String redondear(int dato) {
        return dato < 10 ? "0" + dato : String.valueOf(dato);
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Streamable getStreamable() {
        return streamable;
    }

    public void setStreamable(Streamable streamable) {
        this.streamable = streamable;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public AttrTrack getAttr() {
        return attr;
    }

    public void setAttr(AttrTrack attr) {
        this.attr = attr;
    }

    public String getDescription() {
        String data = "";

        data =
                "Name: " + name + "\n" +
                        "Duration: " + getDuration() + "\n" +
                        "Listeners: " + listeners + "\n" +
                        "Artist: " + artist.getName() + "\n" +
                        "Rank: " + attr.getRank() + "\n";
        return data;
    }
}
