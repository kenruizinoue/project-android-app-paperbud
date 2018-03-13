package com.apps.yecotec.paperbud;

import android.util.Log;

import com.apps.yecotec.paperbud.data.remote.ApiUtils;
import com.apps.yecotec.paperbud.data.remote.NewsApiService;
import com.apps.yecotec.paperbud.domain.models.Article;
import com.apps.yecotec.paperbud.domain.models.NewsApiResponse;

import org.junit.Before;
import org.junit.Test;


import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by kenruizinoue on 12/6/17.
 */

public class RetrofitClientTest {

    NewsApiService mService;

    @Before
    public void setup() {
        mService = ApiUtils.getService();
    }

    @Test
    public void executeTest() throws Exception {

        //synchronous call with enqueue method
        Response response = mService.getNews().execute();
        assertTrue(response.isSuccessful());

        assertTrue(response.body() instanceof NewsApiResponse);

        NewsApiResponse newsApiResponse = (NewsApiResponse) response.body();
        List<Article> articles = newsApiResponse.getArticles();

        //debug title from the first position article
        //System.out.println(articles.get(0).getTitle());

        //assert that there are more than 0 articles
        assertTrue(articles.size() > 0);

    }
}
