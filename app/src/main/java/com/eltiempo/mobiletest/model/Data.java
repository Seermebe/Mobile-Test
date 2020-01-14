package com.eltiempo.mobiletest.model;

import java.util.List;

public class Data {

    private String location;
    private List<String> keywords;
    private String photographer;
    private String date_created;
    private String center;
    private String media_type;
    private String nasa_id;
    private String title;
    private String description;
    private boolean favorite;

    public Data() {
    }

    public Data(String location, List<String> keywords, String photographer, String date_created, String center, String media_type, String nasa_id, String title, String description, boolean favorite) {
        this.location = location;
        this.keywords = keywords;
        this.photographer = photographer;
        this.date_created = date_created;
        this.center = center;
        this.media_type = media_type;
        this.nasa_id = nasa_id;
        this.title = title;
        this.description = description;
        this.favorite = favorite;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKeywordsStr() {
        String str = null;

        for (String s : keywords) {
            if (str == null) {
                str = s;
            } else {
                str += ", " + s;
            }
        }

        return str;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getNasa_id() {
        return nasa_id;
    }

    public void setNasa_id(String nasa_id) {
        this.nasa_id = nasa_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
