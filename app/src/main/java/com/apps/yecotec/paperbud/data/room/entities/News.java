package com.apps.yecotec.paperbud.data.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by kenruizinoue on 12/2/17.
 */

@Entity(tableName = "news")
public class News {

    public News(String title, String publishedDate, String imgUrl, String description, String contentUrl, String timeStamp) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.imgUrl = imgUrl;
        this.description = description;
        this.contentUrl = contentUrl;
        this.timeStamp = timeStamp;
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "news_title")
    private String title;

    @ColumnInfo(name = "news_published_date")
    private String publishedDate;

    @ColumnInfo(name = "news_img_url")
    private String imgUrl;

    @ColumnInfo(name = "news_description")
    private String description;

    @ColumnInfo(name = "news_content_url")
    private String contentUrl;

    @ColumnInfo(name = "news_time_stamp")
    private String timeStamp;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}