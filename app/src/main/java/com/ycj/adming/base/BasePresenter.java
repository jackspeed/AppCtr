package com.ycj.adming.base;

/**
 * Created by adming on 2017/5/3.
 */

public abstract class BasePresenter<T> {
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void dettach() {
        mView = null;
    }
}