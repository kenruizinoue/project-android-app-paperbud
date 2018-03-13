package com.apps.yecotec.paperbud.framework.presentation.presenters_impl;

import com.apps.yecotec.paperbud.domain.executors.Executor;
import com.apps.yecotec.paperbud.domain.executors.MainThread;
import com.apps.yecotec.paperbud.domain.interactors.implementation.CheckDatabaseContentInteractorImpl;
import com.apps.yecotec.paperbud.domain.interactors.implementation.CheckElapsedTimeInteractorImpl;
import com.apps.yecotec.paperbud.domain.interactors.implementation.FetchDataInteractorImpl;
import com.apps.yecotec.paperbud.domain.interactors.implementation.FetchLocalDataInteractorImpl;
import com.apps.yecotec.paperbud.domain.interactors.CheckDatabaseContentInteractor;
import com.apps.yecotec.paperbud.domain.interactors.CheckElapsedTimeInteractor;
import com.apps.yecotec.paperbud.domain.interactors.FetchDataInteractor;
import com.apps.yecotec.paperbud.domain.interactors.FetchLocalDataInteractor;
import com.apps.yecotec.paperbud.domain.repositories.NewsRepository;
import com.apps.yecotec.paperbud.framework.models.SimpleNews;
import com.apps.yecotec.paperbud.framework.presentation.AbstractPresenter;
import com.apps.yecotec.paperbud.framework.presentation.presenters.MainPresenter;

import java.util.List;

/**
 * Created by kenruizinoue on 12/2/17.
 */

public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        FetchDataInteractor.Callback,
        CheckDatabaseContentInteractor.Callback,
        CheckElapsedTimeInteractor.Callback,
        FetchLocalDataInteractor.Callback {

    String TAG = this.getClass().getSimpleName();

    private MainPresenter.View mView;
    private NewsRepository mNewsRepository;

    public MainPresenterImpl(Executor executor,
                             MainThread mainThread,
                             View view,
                             NewsRepository newsRepository) {
        super(executor, mainThread);
        mView = view;
        mNewsRepository = newsRepository;
    }

    //called from MainActivity
    @Override
    public void startFetching(boolean showProgressBar) {

        if(showProgressBar) mView.showLoading();

        FetchDataInteractor fetchDataInteractor = new FetchDataInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mNewsRepository);

        fetchDataInteractor.execute();
    }

    //called from MainActivity
    @Override
    public void startFetchingLocalData() {
        //Log.d(TAG, "called startFetchingLocalData method");
        mView.showLoading();
        FetchLocalDataInteractor fetchLocalDataInteractor =
                new FetchLocalDataInteractorImpl(
                        mExecutor,
                        mMainThread,
                        this,
                        mNewsRepository);

        fetchLocalDataInteractor.execute();
    }

    //called from MainActivity
    @Override
    public void checkDatabaseContent() {
        mView.showLoading();
        CheckDatabaseContentInteractor checkDatabaseContentInteractor =
                new CheckDatabaseContentInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mNewsRepository);

        checkDatabaseContentInteractor.execute();
    }

    //called from MainActivity
    @Override
    public void checkElapsedTime() {
        CheckElapsedTimeInteractor checkElapsedTimeInteractor =
                new CheckElapsedTimeInteractorImpl(
                        mExecutor,
                        mMainThread,
                        this,
                        mNewsRepository);

        checkElapsedTimeInteractor.execute();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onFinishFetching(List<SimpleNews> simpleNews) {
        //Log.d(TAG, "Title: " + simpleNews.get(0).getTitle());
        mView.hideLoading();
        mView.deliverNews(simpleNews);

    }

    @Override
    public void onErrorFetching() {
        mView.hideLoading();
        //show error message
    }

    @Override
    public void onDatabaseIsEmpty() {
        //Log.d(TAG, "onDatabaseIsEmpty");
        mView.databaseIsEmpty();
    }

    @Override
    public void onDatabaseIsNotEmpty() {
        //Log.d(TAG, "onDatabaseIsNotEmpty");
        mView.databaseIsNotEmpty();
    }

    @Override
    public void onMoreThan12Hours() {
        mView.moreThan12Hours();
    }

    @Override
    public void onLessThan12Hours() {
        mView.lessThan12Hours();
    }

    @Override
    public void onFinishFetchingLocalData(List<SimpleNews> simpleNews) {
        mView.hideLoading();
        mView.deliverNews(simpleNews);
    }
}
