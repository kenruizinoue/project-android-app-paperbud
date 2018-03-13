package com.apps.yecotec.paperbud.framework.models;

/**
 * Created by kenruizinoue on 12/3/17.
 */

public class SimpleNews {
    public SimpleNews(String title, String publishedDate, String imgUrl, String description, String contentUrl) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.imgUrl = imgUrl;
        this.description = description;
        this.contentUrl = contentUrl;
    }

    private String title;

    private String publishedDate;

    private String imgUrl;

    private String description;

    private String contentUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
