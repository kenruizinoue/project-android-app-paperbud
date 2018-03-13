package com.apps.yecotec.paperbud.domain.interactors.implementation;

import com.apps.yecotec.paperbud.data.remote.ApiUtils;
import com.apps.yecotec.paperbud.data.remote.NewsApiService;
import com.apps.yecotec.paperbud.domain.executors.Executor;
import com.apps.yecotec.paperbud.domain.executors.MainThread;
import com.apps.yecotec.paperbud.domain.interactors.base.AbstractInteractor;
import com.apps.yecotec.paperbud.domain.interactors.FetchDataInteractor;
import com.apps.yecotec.paperbud.domain.repositories.NewsRepository;
import com.apps.yecotec.paperbud.domain.utils.Converter;
import com.apps.yecotec.paperbud.domain.models.Article;
import com.apps.yecotec.paperbud.domain.models.NewsApiResponse;
import com.apps.yecotec.paperbud.framework.models.SimpleNews;

import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by kenruizinoue on 12/3/17.
 */

public class FetchDataInteractorImpl extends AbstractInteractor implements FetchDataInteractor {

    private FetchDataInteractor.Callback mCallback;
    private NewsRepository mNewsRepository;
    private NewsApiService mService;

    public FetchDataInteractorImpl(Executor threadExecutor,
                                   MainThread mainThread,
                                   Callback callback,
                                   NewsRepository newsRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mNewsRepository = newsRepository;
    }

    @Override
    public void run() {

        mService = ApiUtils.getService();//initialize service

        //asynchronous call with enqueue method
        mService.getNews().enqueue(new retrofit2.Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {

                //verify that api call was successful
                if(response.isSuccessful()) {

                    final List<Article> articles = response.body().getArticles();

                    //make an asynchronous call for database transaction
                    Observable<List<SimpleNews>> newsDataObservable = Observable.fromCallable(new Callable<List<SimpleNews>>() {
                        @Override
                        public List<SimpleNews> call() {
                            //clean database
                            mNewsRepository.deleteAll();
                            //insert all data as News[] to the database
                            mNewsRepository.insertAll(Converter.listToArray(Converter.articleToNews(articles)));
                            //get data from database and convert to SimpleNews object type
                            return  Converter.newsToSimpleNews(mNewsRepository.getAll());

                        }
                    });

                    newsDataObservable
                            .subscribeOn(Schedulers.io())//use
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    new Action1<List<SimpleNews>>() {
                                        //receive data from call() method defined at newsDataObservable
                                        @Override
                                        public void call(List<SimpleNews> newsData) {
                                            //this callback perform from main thread
                                            mCallback.onFinishFetching(newsData);
                                        }
                                    }
                            );

                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                //Log.d("MainActivity", "error fetching data from API");
                mCallback.onErrorFetching();
            }
        });

    }

}
