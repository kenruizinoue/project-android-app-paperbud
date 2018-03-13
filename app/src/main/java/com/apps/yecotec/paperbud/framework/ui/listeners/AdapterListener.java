package com.apps.yecotec.paperbud.framework.ui.listeners;

/**
 * Created by kenruizinoue on 12/2/17.
 */

//this is a listener for updating view inside the adapter class
public interface AdapterListener {
    void onClickShowMore(int position);
    void onClickShowLess(int position);
}
