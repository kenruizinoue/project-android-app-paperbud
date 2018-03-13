package com.apps.yecotec.paperbud.threading;

import com.apps.yecotec.paperbud.domain.executors.MainThread;

/**
 * Created by kenruizinoue on 12/5/17.
 */

public class TestMainThread implements MainThread {

    @Override
    public void post(Runnable runnable) {
        // tests can run on this thread, no need to invoke other threads
        runnable.run();
    }
}