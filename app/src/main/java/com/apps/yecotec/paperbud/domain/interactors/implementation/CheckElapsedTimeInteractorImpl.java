package com.apps.yecotec.paperbud.domain.interactors.implementation;

import com.apps.yecotec.paperbud.domain.executors.Executor;
import com.apps.yecotec.paperbud.domain.executors.MainThread;
import com.apps.yecotec.paperbud.domain.interactors.base.AbstractInteractor;
import com.apps.yecotec.paperbud.domain.interactors.CheckElapsedTimeInteractor;
import com.apps.yecotec.paperbud.domain.repositories.NewsRepository;
import com.apps.yecotec.paperbud.domain.utils.TimeUtil;

import java.sql.Timestamp;

/**
 * Created by kenruizinoue on 12/4/17.
 */

public class CheckElapsedTimeInteractorImpl extends AbstractInteractor implements CheckElapsedTimeInteractor {

    String TAG = this.getClass().getSimpleName();

    private CheckElapsedTimeInteractor.Callback mCallback;
    private NewsRepository mNewsRepository;

    public CheckElapsedTimeInteractorImpl(Executor threadExecutor,
                                          MainThread mainThread,
                                          CheckElapsedTimeInteractor.Callback callback,
                                          NewsRepository newsRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mNewsRepository = newsRepository;
    }

    @Override
    public void run() {
        String startTime = mNewsRepository.getAll().get(0).getTimeStamp();
        String stopTime = new Timestamp(System.currentTimeMillis()).toString();

        if(TimeUtil.getTimeDifference(startTime, stopTime) > 12) mCallback.onMoreThan12Hours();
        else mCallback.onLessThan12Hours();
    }
}
