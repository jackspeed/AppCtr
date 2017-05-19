package com.ycj.adming.test;

import com.ycj.adming.base.BaseActivity;
import com.ycj.adming.test.bean.CityEntity;
import com.ycj.adming.test.bean.HomeInfoEntity;
import com.ycj.adming.test.bean.LoginBean;
import com.ycj.ycjlibrary.http.ActivityLifeCycleEvent;
import com.ycj.ycjlibrary.http.BaseResponse;
import com.ycj.ycjlibrary.http.HttpRequestUtil;
import com.ycj.ycjlibrary.http.OnResponseListener;
import com.ycj.ycjlibrary.http.ProgressSubscriber;

import java.util.List;

import rx.Observable;

/**
 * Created by adming on 2017/5/4.
 */

public class TestMode {
    public static void getData(BaseActivity activity, final OnResponseListener listener, boolean showLoading) {
        Observable<BaseResponse<List<HomeInfoEntity>>> ob = HttpRequestUtil.getInstance().getApi(TestApi.class).getHomeInfoEntity();
        HttpRequestUtil.getInstance().toSubscribe(ob, new ProgressSubscriber<List<HomeInfoEntity>>(showLoading) {

            @Override
            protected void _onNext(List<HomeInfoEntity> response) {

                listener.onSuccess(response);
            }

            @Override
            protected void _onError(String message) {

                listener.onFailure(message);
            }
        }, "", ActivityLifeCycleEvent.DESTROY, activity.lifecycleSubject, false, false);
    }

    public static void getDataUnCache(final OnResponseListener listener, boolean isShow) {
        Observable<BaseResponse<List<CityEntity>>> ob = HttpRequestUtil.getInstance().getApi(TestApi.class).queryCity();
        HttpRequestUtil.getInstance().toSubscribe(ob, new ProgressSubscriber<List<CityEntity>>(isShow) {

            @Override
            protected void _onNext(List<CityEntity> response) {
                listener.onSuccess(response);
            }

            @Override
            protected void _onError(String message) {
                listener.onFailure(message);
            }
        });
    }

    public static void login(BaseActivity activity) {
        Observable<BaseResponse<LoginBean>> ob0 = HttpRequestUtil.getInstance().getApi(TestApi.class).login("17710580646", "111111");
        HttpRequestUtil.getInstance().toSubscribe(ob0, new ProgressSubscriber<LoginBean>() {
            @Override
            protected void _onNext(LoginBean o) {

            }

            @Override
            protected void _onError(String message) {

            }
        }, ActivityLifeCycleEvent.DESTROY, activity.lifecycleSubject);
    }
}
