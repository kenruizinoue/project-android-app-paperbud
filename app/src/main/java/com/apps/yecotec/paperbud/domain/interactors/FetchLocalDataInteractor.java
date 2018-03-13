package com.apps.yecotec.paperbud.domain.interactors;

import com.apps.yecotec.paperbud.domain.interactors.base.BaseInteractor;
import com.apps.yecotec.paperbud.framework.models.SimpleNews;

import java.util.List;

/**
 * Created by kenruizinoue on 12/4/17.
 */

public interface FetchLocalDataInteractor extends BaseInteractor {
    interface Callback {
        void onFinishFetchingLocalData(List<SimpleNews> simpleNews);
    }
}
