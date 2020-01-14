package com.eltiempo.mobiletest.model;

import java.util.List;

public class Collection {

    private List<Item> items;
    private String version;
    private String href;
    private Metadata metadata;

    public Collection() {
    }

    public Collection(List<Item> items, String version, String href, Metadata metadata) {
        this.items = items;
        this.version = version;
        this.href = href;
        this.metadata = metadata;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
