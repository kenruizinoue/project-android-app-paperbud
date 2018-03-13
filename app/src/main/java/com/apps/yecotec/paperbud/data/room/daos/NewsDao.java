package com.apps.yecotec.paperbud.data.room.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.apps.yecotec.paperbud.data.room.entities.News;

import java.util.List;

/**
 * Created by kenruizinoue on 12/2/17.
 */

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news")
    List<News> getAll();

    @Insert
    void insertAll(News... news);

    @Query("SELECT COUNT(*) from news")
    int countNews();

    @Query("DELETE FROM news")
    void deleteAll();
}
