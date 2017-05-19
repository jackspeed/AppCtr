package com.ycj.adming.test.ui;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ycj.adming.R;
import com.ycj.adming.base.WithTitleBaseActivity;
import com.ycj.adming.test.bean.CityEntity;
import com.ycj.adming.test.presenter.TestPresenter;
import com.ycj.adming.test.view.TestModelView;
import com.ycj.ycjlibrary.base.adapter.BaseQuickAdapter;
import com.ycj.ycjlibrary.base.adapter.BaseViewHolder;
import com.ycj.ycjlibrary.photoview.OnOutsidePhotoTapListener;
import com.ycj.ycjlibrary.photoview.PhotoView;
import com.ycj.ycjlibrary.refresh.PullToRefreshView;
import com.ycj.ycjlibrary.utils.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;

public class TestActivity extends WithTitleBaseActivity implements TestModelView<List<CityEntity>>, PullToRefreshView.OnLoadMoreListener, PullToRefreshView.OnRefreshListener, OnOutsidePhotoTapListener {
    @BindView(R.id.recycle)
    RecyclerView reList;
    @BindView(R.id.content_layout)
    PullToRefreshView contentLayout;
    private MyAdapter mAdapter;
    private TestPresenter presenter;

    @Override
    protected View loadViewLayout() {
        return getLayoutInflater().inflate(R.layout.activity_test, null);
    }

    @Override
    protected void initView() {
        setTitleText("积分哈");
        setTopLeftCorner(false);
        reList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        reList.setLayoutManager(new LinearLayoutManager(this));
        reList.setItemAnimator(new DefaultItemAnimator());
        reList.setHasFixedSize(true);  //如果Item高度固定  增加该属性能够提高效率
        mAdapter = new MyAdapter(R.layout.list_item_test, null);
        reList.setAdapter(mAdapter);
        contentLayout.setOnLoadMoreListener(this);
        contentLayout.setOnRefreshListener(this);
        PhotoView p = new PhotoView(this);
        p.setOnOutsidePhotoTapListener(this);
    }

    @Override
    protected void initData() {
        if (presenter == null) presenter = new TestPresenter(this);
        presenter.getData(this, true);
    }

    @Override
    protected void onBaseRefresh() {
        initData();
    }

    @Override
    public void showEmptyResult() {
        showEmptyView();
        onComplete();
    }

    @Override
    public void showErrorResult() {
        showErrorView();
        onComplete();
    }

    @Override
    public void onSuccess(List<CityEntity> cityEntityList) {
        mAdapter.setNewData(cityEntityList);
        onComplete();
    }

    @Override
    public void onFailure(String msg) {
        showErrorView();
        onComplete();
    }

    private void onComplete() {
        contentLayout.onRefreshComplete();
        contentLayout.onLoadMoreComplete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    protected void leftBarBtnEvent() {

    }

    @Override
    public void onLoadMore() {
        initData();
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onOutsidePhotoTap(ImageView imageView) {
        finish();
    }

    private class MyAdapter extends BaseQuickAdapter<CityEntity> {
        MyAdapter(int layoutResId, List<CityEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CityEntity item) {
            helper.setText(R.id.tv_item_test, item.getName());
        }
    }
}
