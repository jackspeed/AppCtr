package com.ycj.adming;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class BaseActivity extends AppCompatActivity {

    private int mOrientation;
    private AlertDialog loadingDialog;

    /**
     * startActivityForResult()方法的返回
     *
     * @param isRefresh true-刷新数据，false-不刷新数据
     */
    public void setBackResult(boolean isRefresh) {
        Intent intent = new Intent();
        intent.putExtra("isRefresh", isRefresh);//是否刷新
        setResult(AppConst.CODE_ACTIVITY_BACK, intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideKeyBoard();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 隐蔽软键盘 Method: hideKeyBoard
     */
    public void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View currentFocus = getCurrentFocus();
        if (null != currentFocus && imm.isActive()) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 屏幕方向改变时会调用，参考
     * android:configChanges="locale|keyboard|keyboardHidden|orientation"
     * 如果不定义configchanges，则屏幕方向变化时，会再次调用oncreate Method: onConfigurationChanged
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mOrientation != newConfig.orientation) {
            mOrientation = newConfig.orientation;
        }
    }

    /**
     * 按下按键时触发
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 按了后退键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 获取屏幕宽度(px)
     */
    public int getMobileWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度(px)
     */
    public int getMobileHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取状态栏高度
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /*
    返回版本api
     */
    public boolean getSDKApi() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        return currentapiVersion >= android.os.Build.VERSION_CODES.KITKAT;
    }

    public boolean checkNetworkState() {
        boolean flag = false;
        //得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        return flag;
    }

    public String getVersionName() {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.0";
    }

    public int getVersionCode() {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void showLoading(boolean touchCanceled) {
        try {
            hideLoading();
            loadingDialog = new AlertDialog.Builder(this).create();
            loadingDialog.setCanceledOnTouchOutside(touchCanceled);
            loadingDialog.setCancelable(touchCanceled);
            loadingDialog.show();
            Window window = loadingDialog.getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.alpha = 1.0f;//alpha在0.0f到1.0f之间。1.0完全不透明，0.0f完全透明
            lp.dimAmount = 0.4f;//dimAmount在0.0f和1.0f之间，0.0f完全不暗，1.0f全暗
            window.setAttributes(lp);
            window.setBackgroundDrawable(new BitmapDrawable());
            View alertView = LayoutInflater.from(this).inflate(R.layout.layout_loading, null);
            window.setContentView(alertView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }
}
