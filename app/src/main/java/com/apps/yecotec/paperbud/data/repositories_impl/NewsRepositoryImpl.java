package com.apps.yecotec.paperbud.data.repositories_impl;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.apps.yecotec.paperbud.data.room.AppDatabase;
import com.apps.yecotec.paperbud.data.room.entities.News;
import com.apps.yecotec.paperbud.domain.repositories.NewsRepository;

import java.util.List;

/**
 * Created by kenruizinoue on 12/2/17.
 */

public class NewsRepositoryImpl implements NewsRepository {

    String TAG = this.getClass().getSimpleName();

    AppDatabase db;

    public NewsRepositoryImpl(Context context, boolean test) {
        if(test) {
            db = AppDatabase.createTestDB(context);
        } else {
            db = Room.databaseBuilder(context, AppDatabase.class, "database-news").build();
        }
    }

    @Override
    public void insertAll(News... news) {
        db.newsDao().insertAll(news);
        //Log.d(TAG,"Records: " + db.newsDao().countNews());
    }

    @Override
    public List<News> getAll() {
        return db.newsDao().getAll();
    }

    @Override
    public void deleteAll() {
        db.newsDao().deleteAll();
    }

    @Override
    public int countNews() {
        //Log.d(TAG, "countNews method called");
        return db.newsDao().countNews();
    }

}
