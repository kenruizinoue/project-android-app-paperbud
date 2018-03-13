package com.apps.yecotec.paperbud;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.apps.yecotec.paperbud.data.room.AppDatabase;
import com.apps.yecotec.paperbud.data.room.daos.NewsDao;
import com.apps.yecotec.paperbud.data.room.entities.News;
import com.apps.yecotec.paperbud.domain.utils.Converter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by kenruizinoue on 12/5/17.
 */

@RunWith(AndroidJUnit4.class)
public class NewsDaoTest {
    AppDatabase db;
    NewsDao newsDao;

    News[] mockedNewsArray = new News[5];

    @Before
    public void setup() {
        db = AppDatabase.createTestDB(InstrumentationRegistry.getTargetContext());
        newsDao = db.newsDao();

        mockedNewsArray[0] = (new News("title for news 1",
                "published Date 1",
                "image Url 1",
                "description 1",
                "content Url 1",
                "timestamp 1"));

        mockedNewsArray[1] = (new News("title for news 2",
                "published Date 2",
                "image Url 2",
                "description 2",
                "content Url 2",
                "timestamp 2"));
        mockedNewsArray[2] = (new News("title for news 3",
                "published Date 3",
                "image Url 3",
                "description 3",
                "content Url 3",
                "timestamp 3"));
        mockedNewsArray[3] = (new News("title for news 4",
                "published Date 4",
                "image Url 4",
                "description 4",
                "content Url 4",
                "timestamp 4"));
        mockedNewsArray[4] = (new News("title for news 5",
                "published Date 5",
                "image Url 5",
                "description 5",
                "content Url 5",
                "timestamp 5"));
    }

    @Test
    public void executeTest() {
        //assert that database is empty
        assertEquals(0, newsDao.countNews());

        //insert News objects to the db
        newsDao.insertAll(mockedNewsArray);

        assertNews(newsDao, mockedNewsArray);

    }

    @After
    public void finish() {
        db.close();
    }

    private void assertNews(NewsDao newsDao, News[] newsArray) {
        List<News> results = newsDao.getAll();

        //assert that retrieved data is not null
        assertNotNull(results);

        //assert that retrieved data size is 5
        assertEquals(5, newsDao.countNews());

        //assert that passed list's objects and retrieved data's objects are the same
        int iterator = 0;
        for (News news: newsArray) {
            assertTrue(areIdentical(news, results.get(iterator)));
            iterator++;
        }
    }

    private boolean areIdentical(News one, News two) {
        if(one.getTitle().equals(two.getTitle())
                & one.getPublishedDate().equals(two.getPublishedDate())
                & one.getImgUrl().equals(two.getImgUrl())
                & one.getDescription().equals(two.getDescription())
                & one.getContentUrl().equals(two.getContentUrl())
                & one.getTimeStamp().equals(two.getTimeStamp()))
            return true;

        return false;
    }
}
