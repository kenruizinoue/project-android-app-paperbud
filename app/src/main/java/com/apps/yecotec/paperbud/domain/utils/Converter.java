package com.apps.yecotec.paperbud.domain.utils;

import com.apps.yecotec.paperbud.data.room.entities.News;
import com.apps.yecotec.paperbud.domain.models.Article;
import com.apps.yecotec.paperbud.framework.models.SimpleNews;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenruizinoue on 12/3/17.
 */

public class Converter {

    public static List<News> articleToNews(List<Article> articles) {

        List<News> news = new ArrayList<>();

        for (Article article: articles) {
            news.add(new News(
                    article.getTitle(),
                    article.getPublishedAt(),
                    article.getUrlToImage(),
                    article.getDescription(),
                    article.getUrl(),
                    new Timestamp(System.currentTimeMillis()).toString()
                    ));
        }

        return news;
    }

    public static List<SimpleNews> newsToSimpleNews(List<News> newses) {
        List<SimpleNews> simpleNews = new ArrayList<>();

        for (News news: newses) {
            simpleNews.add(new SimpleNews(
                    news.getTitle(),
                    news.getPublishedDate(),
                    news.getImgUrl(),
                    news.getDescription(),
                    news.getContentUrl()));
        }

        return simpleNews;
    }

    public static News[] listToArray(List<News> news) {
        News[] newsArray = news.toArray(new News[news.size()]);
        return newsArray;
    }

}
