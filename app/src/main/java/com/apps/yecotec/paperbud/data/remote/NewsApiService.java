package com.apps.yecotec.paperbud.data.remote;

/**
 * Created by kenruizinoue on 12/3/17.
 */

import com.apps.yecotec.paperbud.domain.models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApiService {
    @GET("/v2/top-headlines?language=en&apiKey=$USE_YOUR_NEWS_API$") // Place your news api
    Call<NewsApiResponse> getNews();
}
