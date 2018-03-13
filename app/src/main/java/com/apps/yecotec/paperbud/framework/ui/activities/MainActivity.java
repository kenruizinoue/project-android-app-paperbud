package com.apps.yecotec.paperbud.framework.ui.activities;

import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.apps.yecotec.paperbud.R;
import com.apps.yecotec.paperbud.data.repositories_impl.NewsRepositoryImpl;
import com.apps.yecotec.paperbud.domain.executors.MainThreadImpl;
import com.apps.yecotec.paperbud.domain.executors.ThreadExecutor;
import com.apps.yecotec.paperbud.framework.models.SimpleNews;
import com.apps.yecotec.paperbud.framework.presentation.presenters.MainPresenter;
import com.apps.yecotec.paperbud.framework.presentation.presenters_impl.MainPresenterImpl;
import com.apps.yecotec.paperbud.framework.ui.adapters.NewsAdapter;
import com.apps.yecotec.paperbud.framework.utils.BitmapUtil;
import com.apps.yecotec.paperbud.framework.utils.SimpleNewsUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenter.View{

    String TAG = this.getClass().getSimpleName();

    private MainPresenter mMainPresenter;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    NewsAdapter mAdapter;
    @BindView(R.id.rv_news)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int densityDpi = (int)(metrics.density * 160f);

        Log.d(TAG, "Density in DPI: " + densityDpi);

        init();
    }

    private void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NewsAdapter(this, this);

        // instantiate the presenter
        mMainPresenter = new MainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new NewsRepositoryImpl(this, false)
        );

        //starting point for the app
        mMainPresenter.checkDatabaseContent();
    }

    //ui related methods
    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        //Log.d(TAG, "Trying to hide loading");
        // Stop refresh animation
        swipeRefreshLayout.setRefreshing(false);
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void deliverNews(List<SimpleNews> simpleNews) {
        //Log.d(TAG, "News Delivered!");
        mAdapter.refershItems(SimpleNewsUtil.filterNullTitleObject(simpleNews));
        mRecyclerView.setAdapter(mAdapter);

        //after fetching local or remote data from init(), assign pull to update listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                mMainPresenter.startFetching(false);
            }
        });
    }

    @Override
    public void databaseIsEmpty() {
        Log.d(TAG, "Database is empty");
        mMainPresenter.startFetching(true);
    }

    @Override
    public void databaseIsNotEmpty() {
        Log.d(TAG, "Database is not empty");
        mMainPresenter.checkElapsedTime();
    }

    @Override
    public void moreThan12Hours() {
        Log.d(TAG, "Elapsed more than 12 hours");
        //update news data
        mMainPresenter.startFetching(true);
    }

    @Override
    public void lessThan12Hours() {
        Log.d(TAG, "Not elapsed more than 12 hours");
        //fetch data from database
        mMainPresenter.startFetchingLocalData();
    }

    //called from NewsAdapter
    @Override
    public void itemSelected(SimpleNews simpleNews) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

        //for showing title at tool bar
        builder.setShowTitle(true);
        builder.setCloseButtonIcon(BitmapUtil.getBitmapFromDrawable(this, R.drawable.ic_arrow_back));

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(simpleNews.getContentUrl()));
    }
}
