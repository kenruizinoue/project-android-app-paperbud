package com.apps.yecotec.paperbud.data.remote;

/**
 * Created by kenruizinoue on 12/3/17.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://newsapi.org";

    public static NewsApiService getService() {
        //create client with specified interface
        return RetrofitClient.getClient(BASE_URL).create(NewsApiService.class);
    }
}