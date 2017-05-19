package com.ycj.adming.app;

import com.ycj.ycjlibrary.SimpleApplication;
import com.ycj.ycjlibrary.http.HttpRequestUtil;
import com.ycj.ycjlibrary.utils.AppInfo;

/**
 * Created by adming on 2017/5/3.
 */

public class MyApplication extends SimpleApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //缓存Context
        AppInfo.init(getApplicationContext());
        //初始化网络请求模块
        HttpRequestUtil.getInstance().init(this);
    }


}
