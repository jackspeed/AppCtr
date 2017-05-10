package com.ycj.ycjlibrary.http;


import com.ycj.ycjlibrary.SimpleLoadDialog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by helin on 2016/10/10 15:49.
 */

public abstract class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {
    private SimpleLoadDialog dialogHandler;

    /**
     * dialogHandler==null ，dialog不显示
     */
    public ProgressSubscriber() {
        dialogHandler = null;
    }

    /**
     * cancelAble
     * #true点击dialog消失；
     * #false点击dialog不消失返回按钮dialog也不消失
     *
     * @param cancelAble boolean
     */
    public ProgressSubscriber(boolean cancelAble) {
        dialogHandler = new SimpleLoadDialog(this, cancelAble);
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }


    /**
     * 显示Dialog
     */
    public void showProgressDialog() {
        if (dialogHandler != null) {
            dialogHandler.show();
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    /**
     * 隐藏Dialog
     */
    private void dismissProgressDialog() {
        if (dialogHandler != null) {
            dialogHandler.dismiss();
            dialogHandler = null;
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof SocketTimeoutException) {
            _onError("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            _onError("网络中断，请检查您的网络状态");
        } else if (e instanceof ApiException) {
            _onError(e.getMessage());
        } else {
            _onError("请求失败，请稍后再试...");
        }
        dismissProgressDialog();
    }


    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);
}
