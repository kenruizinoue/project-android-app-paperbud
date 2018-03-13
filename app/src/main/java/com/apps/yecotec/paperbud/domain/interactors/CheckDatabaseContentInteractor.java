package com.apps.yecotec.paperbud.domain.interactors;

import com.apps.yecotec.paperbud.domain.interactors.base.BaseInteractor;

/**
 * Created by kenruizinoue on 12/4/17.
 */

public interface CheckDatabaseContentInteractor extends BaseInteractor {
    interface Callback {
        void onDatabaseIsEmpty();
        void onDatabaseIsNotEmpty();
    }
}
