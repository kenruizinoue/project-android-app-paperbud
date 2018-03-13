package com.apps.yecotec.paperbud.domain.interactors.implementation;

import com.apps.yecotec.paperbud.domain.executors.Executor;
import com.apps.yecotec.paperbud.domain.executors.MainThread;
import com.apps.yecotec.paperbud.domain.interactors.base.AbstractInteractor;
import com.apps.yecotec.paperbud.domain.interactors.FetchLocalDataInteractor;
import com.apps.yecotec.paperbud.domain.repositories.NewsRepository;
import com.apps.yecotec.paperbud.domain.utils.Converter;
import com.apps.yecotec.paperbud.framework.models.SimpleNews;

import java.util.List;

/**
 * Created by kenruizinoue on 12/4/17.
 */

public class FetchLocalDataInteractorImpl extends AbstractInteractor implements FetchLocalDataInteractor {

    String TAG = this.getClass().getSimpleName();

    private FetchLocalDataInteractor.Callback mCallback;
    private NewsRepository mNewsRepository;

    public FetchLocalDataInteractorImpl(Executor threadExecutor,
                                        MainThread mainThread,
                                        Callback callback,
                                        NewsRepository newsRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mNewsRepository = newsRepository;
    }

    @Override
    public void run() {

        final List<SimpleNews> simpleNews = Converter.newsToSimpleNews(mNewsRepository.getAll());

        // notify on the main thread, because this callback makes changes to the ui
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFinishFetchingLocalData(simpleNews);
            }
        });

    }
}
