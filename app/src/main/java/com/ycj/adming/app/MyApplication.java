package com.ycj.adming.app;

import android.app.Application;

import com.ycj.ycjlibrary.http.HttpRequestUtil;
import com.ycj.ycjlibrary.utils.AppInfo;

/**
 * Created by adming on 2017/5/3.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppInfo.init(getApplicationContext());
        HttpRequestUtil.getInstance().init(this);
    }


}
