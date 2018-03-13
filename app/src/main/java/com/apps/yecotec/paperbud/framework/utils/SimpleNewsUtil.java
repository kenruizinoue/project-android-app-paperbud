package com.apps.yecotec.paperbud.framework.utils;

import com.apps.yecotec.paperbud.framework.models.SimpleNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenruizinoue on 12/5/17.
 */

public class SimpleNewsUtil {

    //create new List without SimpleNews object that content null as a title
    public static List<SimpleNews> filterNullTitleObject(List<SimpleNews> simpleNewsList) {
        List<SimpleNews> filteredList = new ArrayList<>();

        for (SimpleNews simpleNews: simpleNewsList) {
            if(simpleNews.getTitle() != null) filteredList.add(simpleNews);
        }

        return filteredList;
    }
}
