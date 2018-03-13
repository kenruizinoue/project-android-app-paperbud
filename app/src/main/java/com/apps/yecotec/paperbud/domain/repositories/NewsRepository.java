package com.apps.yecotec.paperbud.domain.repositories;

import com.apps.yecotec.paperbud.data.room.entities.News;

import java.util.List;

/**
 * Created by kenruizinoue on 12/2/17.
 */

public interface NewsRepository {
    void insertAll(News... news);
    List<News> getAll();
    void deleteAll();
    int countNews();
}
