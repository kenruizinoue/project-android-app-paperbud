package com.apps.yecotec.paperbud.domain.executors;

import com.apps.yecotec.paperbud.domain.interactors.base.AbstractInteractor;

/**
 * Created by kenruizinoue on 12/1/17.
 */

//This executor is responsible for running interactors on background threads.
public interface Executor {

    /**
     * This method should call the interactor's run method and thus start the interactor. This should be called
     * on a background thread as interactors might do lengthy operations.
     *
     * @param interactor The interactor to run.
     */
    void execute(final AbstractInteractor interactor);
}
