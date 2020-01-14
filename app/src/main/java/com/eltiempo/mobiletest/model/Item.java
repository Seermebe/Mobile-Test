package com.eltiempo.mobiletest.model;

import java.util.List;

public class Item {

    private List<Link> links;
    private String href;
    private List<Data> data;

    public Item() {
    }

    public Item(List<Link> links, String href, List<Data> data) {
        this.links = links;
        this.href = href;
        this.data = data;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
