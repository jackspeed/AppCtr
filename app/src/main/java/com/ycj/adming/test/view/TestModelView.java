package com.ycj.adming.test.view;

import com.ycj.adming.base.BaseView;

/**
 * Created by adming on 2017/5/10.
 */

public interface TestModelView<T> extends BaseView {

    void showEmptyResult();

    void showErrorResult();

    void onSuccess(T t);

}
