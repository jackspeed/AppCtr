package com.ycj.adming.test;

import com.ycj.adming.base.BaseActivity;
import com.ycj.adming.base.BasePresenter;
import com.ycj.adming.base.BaseView;
import com.ycj.adming.test.bean.CityEntity;
import com.ycj.ycjlibrary.http.OnResponseListener;

import java.util.List;

/**
 * Created by adming on 2017/5/10.
 */

public class TestPresenter extends BasePresenter<BaseView> implements OnResponseListener<List<CityEntity>> {
    private TestModelView view;

    public TestPresenter(TestModelView view) {
        this.view = view;
        attach(view);
    }

    @Override
    public void attach(BaseView mView) {
        super.attach(mView);
    }

    public void getData(BaseActivity activity, boolean isShow) {
        TestMode.getDataUnCache(this, isShow);
    }

    @Override
    public void detach() {
        super.detach();
    }

    @Override
    public void onSuccess(List<CityEntity> data) {
        if (view == null) return;
        if (data == null || data.size() == 0) {
            view.showEmptyResult();
        } else {
            view.onSuccess(data);
        }
    }

    @Override
    public void onFailure(String msg) {
        if (view != null) view.onFailure(msg);
    }
}
