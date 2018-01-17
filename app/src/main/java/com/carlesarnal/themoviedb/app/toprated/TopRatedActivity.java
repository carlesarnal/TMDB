package com.carlesarnal.themoviedb.app.toprated;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.carlesarnal.themoviedb.R;
import com.carlesarnal.themoviedb.api.model.Movie;
import com.carlesarnal.themoviedb.app.App;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by carles on 17/01/18.
 */

public class TopRatedActivity extends AppCompatActivity implements TopRatedContract.View,
        SwipeRefreshLayout.OnRefreshListener, EndlessScrollListener.ScrollToBottomListener, TopRatedAdapter.ItemClickListener {


    @Inject
    TopRatedPresenter presenter;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView contentView;
    @BindView(R.id.errorText)
    View errorView;
    @BindView(R.id.progressBar)
    View loadingView;

    private TopRatedAdapter moviesAdapter;
    private EndlessScrollListener endlessScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated);
        ButterKnife.bind(this);

        setupContentView();
        DaggerMainComponent.builder()
                .appComponent(App.getAppComponent(getApplication()))
                .mainModule(new TopRatedModule(this))
                .build()
                .inject(this);
    }

    private void setupContentView() {
        swipeRefreshLayout.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        endlessScrollListener = new EndlessScrollListener(linearLayoutManager, this);
        contentView.setLayoutManager(linearLayoutManager);
        contentView.addOnScrollListener(endlessScrollListener);
    }

    @Override
    public void onRefresh() {
        endlessScrollListener.onRefresh();
        presenter.onPullToRefresh();
    }

    @Override
    public void onScrollToBottom() {
        presenter.onScrollToBottom();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void showLoading(boolean isRefresh) {
        if (isRefresh) {
            if (!swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(true);
            }
        } else {
            loadingView.setVisibility(View.VISIBLE);
            contentView.setVisibility(View.GONE);
            errorView.setVisibility(View.GONE);
        }
    }
    @Override
    public void showContent(List<Movie> movies, boolean isRefresh) {
        if (moviesAdapter == null) {
            moviesAdapter = new TopRatedAdapter(movies, this, this);
            contentView.setAdapter(moviesAdapter);
        } else {
            if (isRefresh) {
                moviesAdapter.clear();
            }
            moviesAdapter.addAll(movies);
            moviesAdapter.notifyDataSetChanged();
        }

        // Delay SwipeRefreshLayout animation by 1.5 seconds
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);

        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        swipeRefreshLayout.setRefreshing(false);
        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }



    @Override
    public void onItemClick(Movie movie) {
    }

    @OnClick(R.id.errorText)
    void onClickErrorView() {
        presenter.start();
    }
}
