package com.apps.yecotec.paperbud.framework.presentation.presenters;

import com.apps.yecotec.paperbud.framework.models.SimpleNews;
import com.apps.yecotec.paperbud.framework.presentation.BasePresenter;
import com.apps.yecotec.paperbud.framework.ui.BaseView;

import java.util.List;

/**
 * Created by kenruizinoue on 12/2/17.
 */

public interface MainPresenter extends BasePresenter {
    interface View extends BaseView {
        void showLoading();
        void hideLoading();
        void deliverNews(List<SimpleNews> simpleNews);
        void databaseIsEmpty();
        void databaseIsNotEmpty();
        void moreThan12Hours();
        void lessThan12Hours();
        void itemSelected(SimpleNews simpleNews);
    }

    void startFetching(boolean showProgressBar);
    void startFetchingLocalData();
    void checkDatabaseContent();
    void checkElapsedTime();

}
