package com.apps.yecotec.paperbud;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.apps.yecotec.paperbud.data.repositories_impl.NewsRepositoryImpl;
import com.apps.yecotec.paperbud.data.room.entities.News;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by kenruizinoue on 12/6/17.
 */

@RunWith(AndroidJUnit4.class)
public class NewsRepositoryImplTest {

    String TAG = this.getClass().getSimpleName();

    NewsRepositoryImpl newsRepository;
    News[] mockedNewsArray = new News[5];

    @Before public void setup() {
        newsRepository = new NewsRepositoryImpl(InstrumentationRegistry.getTargetContext(), true);
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

    @Test public void execute() {

        //assert that database is empty
        assertEquals(newsRepository.countNews(), 0);

        newsRepository.insertAll(mockedNewsArray);

        List<News> results = newsRepository.getAll();

        //assert that retrieved data is not null
        assertNotNull(results);

        //assert that retrieved data size is 5
        assertEquals(results.size(), 5);

        //assert that passed list's objects and retrieved data's objects are the same
        int iterator = 0;
        for (News news: mockedNewsArray) {
            assertTrue(areIdentical(news, results.get(iterator)));
            iterator++;
        }

        newsRepository.deleteAll();

        //assert that database is empty
        assertEquals(newsRepository.countNews(), 0);
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
