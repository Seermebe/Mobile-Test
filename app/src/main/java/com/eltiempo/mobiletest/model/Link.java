package com.eltiempo.mobiletest.model;

public class Link {

    private String rel;
    private String render;
    private String href;

    public Link() {
    }

    public Link(String rel, String render, String href) {
        this.rel = rel;
        this.render = render;
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
