package com.apps.yecotec.paperbud.framework.presentation;

import com.apps.yecotec.paperbud.domain.executors.Executor;
import com.apps.yecotec.paperbud.domain.executors.MainThread;

/**
 * Created by kenruizinoue on 12/1/17.
 */

public abstract class AbstractPresenter {
    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}