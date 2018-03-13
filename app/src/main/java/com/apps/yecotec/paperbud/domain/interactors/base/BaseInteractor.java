package com.apps.yecotec.paperbud.domain.interactors.base;

/**
 * Created by kenruizinoue on 12/1/17.
 */

public interface BaseInteractor {
    /**
     * This is the main method that starts an interactor. It will make sure that the interactor operation is done on a
     * background thread.
     */
    void execute();
}
