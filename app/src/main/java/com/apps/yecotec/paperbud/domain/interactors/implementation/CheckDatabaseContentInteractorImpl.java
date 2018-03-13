package com.apps.yecotec.paperbud.domain.interactors.implementation;

import com.apps.yecotec.paperbud.domain.executors.Executor;
import com.apps.yecotec.paperbud.domain.executors.MainThread;
import com.apps.yecotec.paperbud.domain.interactors.base.AbstractInteractor;
import com.apps.yecotec.paperbud.domain.interactors.CheckDatabaseContentInteractor;
import com.apps.yecotec.paperbud.domain.repositories.NewsRepository;

/**
 * Created by kenruizinoue on 12/4/17.
 */

public class CheckDatabaseContentInteractorImpl extends AbstractInteractor implements CheckDatabaseContentInteractor {

    String TAG = this.getClass().getSimpleName();

    private CheckDatabaseContentInteractor.Callback mCallback;
    private NewsRepository mNewsRepository;

    public CheckDatabaseContentInteractorImpl(Executor threadExecutor,
                                              MainThread mainThread,
                                              Callback callback,
                                              NewsRepository newsRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mNewsRepository = newsRepository;
    }

    @Override
    public void run() {
        if (mNewsRepository.countNews() == 0) mCallback.onDatabaseIsEmpty();
        else mCallback.onDatabaseIsNotEmpty();
    }
}
