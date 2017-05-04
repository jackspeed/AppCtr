package com.ycj.adming.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ycj.adming.R;
import com.ycj.adming.base.WithTitleBaseActivity;
import com.ycj.ycjlibrary.base.adapter.BaseQuickAdapter;
import com.ycj.ycjlibrary.base.adapter.BaseViewHolder;
import com.ycj.ycjlibrary.refresh.PullToRefreshView;
import com.ycj.ycjlibrary.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends WithTitleBaseActivity implements PullToRefreshView.OnLoadMoreListener, PullToRefreshView.OnRefreshListener {
    private final Runnable RUNNABLE = new Runnable() {
        @Override
        public void run() {
            mAdapter.setNewData(data);
        }
    };
    private List<String> data = new ArrayList<>();
    @BindView(R.id.recycle)
    RecyclerView reList;
    @BindView(R.id.content_layout)
    PullToRefreshView contentLayout;

    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleText("积分哈");
        setTopLeftCorner(false);
        ButterKnife.bind(this);
        reList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        reList.setLayoutManager(new LinearLayoutManager(this));
        reList.setItemAnimator(new DefaultItemAnimator());
        reList.setHasFixedSize(true);  //如果Item高度固定  增加该属性能够提高效率
        mAdapter = new MyAdapter(R.layout.list_item_test, null);
        reList.setAdapter(mAdapter);
        loadData();
        contentLayout.setOnLoadMoreListener(this);
        contentLayout.setOnRefreshListener(this);
        new Handler().postDelayed(RUNNABLE, 1000);
    }

    @Override
    protected View initUIContentView() {
        View view = getLayoutInflater().inflate(R.layout.activity_test, null);
        ButterKnife.bind(this);
        return view;
    }

    private void loadData() {
        data.clear();
        for (int i = 0; i < 20; i++) {
            data.add("测试数据" + i);
        }
    }

    private void loadMoreData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 50; i < 60; i++) {
                    data.add("测试数据" + i);
                }
                mAdapter.addData(data);
                contentLayout.onLoadMoreComplete();
            }
        }, 2000);

    }

    @Override//加载更多回调
    public void onLoadMore() {
        loadMoreData();
    }

    @Override//下拉刷新回调
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.setNewData(data);
                contentLayout.onRefreshComplete();
            }
        }, 2000);
        loadData();
    }

    @Override
    protected void leftBarBtnEvent() {

    }

    private class MyAdapter extends BaseQuickAdapter<String> {
        MyAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_item_test, item);
        }
    }
}
