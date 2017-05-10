package com.ycj.ycjlibrary.http;

import com.ycj.ycjlibrary.utils.GlobalData;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by adming on 2017/5/4.
 */

public class HttpRequestUtil extends RetrofitUtils {
    private volatile static HttpRequestUtil instance;

    /***
     * 获取实例
     */
    public static HttpRequestUtil getInstance() {
        if (null == instance) {
            synchronized (HttpRequestUtil.class) {
                if (null == instance) {
                    instance = new HttpRequestUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 添加线程管理并订阅
     *
     * @param ob
     * @param subscriber
     * @param cacheKey         缓存kay
     * @param event            Activity 生命周期
     * @param lifecycleSubject
     * @param isSave           是否缓存
     * @param forceRefresh     是否强制刷新
     */
    public void toSubscribe(Observable ob, final ProgressSubscriber subscriber, String cacheKey, final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject, boolean isSave, boolean forceRefresh) {
//        数据预处理
        Observable.Transformer<BaseResponse<Object>, Object> result = RxHelper.handleResult(event, lifecycleSubject);
        Observable observable = ob.compose(result)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //显示Dialog和一些其他操作
                        subscriber.showProgressDialog();
                    }
                });
        RetrofitCache.load(cacheKey, observable, isSave, forceRefresh).subscribe(subscriber);
    }



    /**
     * 添加线程管理并订阅
     *
     * @param ob
     * @param subscriber
     * @param event            Activity 生命周期
     * @param lifecycleSubject
     */
    public void toSubscribe(Observable ob, final ProgressSubscriber subscriber, final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {
        //数据预处理
        Observable.Transformer<BaseResponse<Object>, Object> result = RxHelper.handleResult(event, lifecycleSubject);
        ob.compose(result)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //显示Dialog和一些其他操作
                        subscriber.showProgressDialog();
                    }
                })//保证doOnSubscribe是在主线程执行
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 添加线程管理并订阅
     *
     * @param ob
     * @param subscriber
     */
    public void toSubscribe(Observable ob, final ProgressSubscriber subscriber) {
        //数据预处理
        Observable.Transformer<BaseResponse<Object>, Object> result = RxHelper.handleResult(null, null);
        ob.compose(result)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //显示Dialog和一些其他操作
                        subscriber.showProgressDialog();
                    }
                })//保证doOnSubscribe是在主线程执行
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseResponse<T>, T> handleResult(final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {

        final Observable<ActivityLifeCycleEvent> compareLifecycleObservable;
        if (lifecycleSubject != null) {
            compareLifecycleObservable = lifecycleSubject.takeFirst(new Func1<ActivityLifeCycleEvent, Boolean>() {
                @Override
                public Boolean call(ActivityLifeCycleEvent activityLifeCycleEvent) {
                    return activityLifeCycleEvent.equals(event);
                }
            });
        } else {
            compareLifecycleObservable = null;
        }
        return new Observable.Transformer<BaseResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseResponse<T>> tObservable) {

                return tObservable.flatMap(new Func1<BaseResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseResponse<T> result) {
                        if (result.result == GlobalData.CODE_SUCCESS) {
                            return createData(result.data);
                        } else {
                            return Observable.error(new ApiException(result.result, result.msg));
                        }
                    }
                }).takeUntil(compareLifecycleObservable)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }

}
