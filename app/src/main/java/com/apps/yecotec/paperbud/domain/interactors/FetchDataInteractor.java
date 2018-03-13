package com.apps.yecotec.paperbud.domain.interactors;


import com.apps.yecotec.paperbud.domain.interactors.base.BaseInteractor;
import com.apps.yecotec.paperbud.framework.models.SimpleNews;

import java.util.List;

/**
 * Created by kenruizinoue on 12/2/17.
 */

public interface FetchDataInteractor extends BaseInteractor {
    interface Callback {
        void onFinishFetching(List<SimpleNews> simpleNews);
        void onErrorFetching();
    }
}
