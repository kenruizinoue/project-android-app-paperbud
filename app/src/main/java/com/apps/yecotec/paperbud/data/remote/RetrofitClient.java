package com.apps.yecotec.paperbud.data.remote;

/**
 * Created by kenruizinoue on 12/3/17.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    // Retrofit needs a base URL to build its instance
    // So we will pass a URL when calling RetrofitClient.getClient(String baseUrl)
    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create()) // Specifying Gson as convertor
                    .build();
        }
        return retrofit;
    }
}