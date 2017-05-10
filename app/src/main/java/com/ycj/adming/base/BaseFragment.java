package com.ycj.adming.base;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ycj.adming.R;

/**
 * Created by adming on 2017/5/3.
 */


public abstract class BaseFragment extends FrameLayout {
    private Context mContext;
    private AlertDialog loadingDialog;

    public BaseFragment(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public void showLoading(boolean touchCanceled) {
        try {
            hideLoading();
            loadingDialog = new AlertDialog.Builder(mContext).create();
            loadingDialog.setCanceledOnTouchOutside(touchCanceled);
            loadingDialog.setCancelable(touchCanceled);
            loadingDialog.show();
            Window window = loadingDialog.getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.alpha = 1.0f;//alpha在0.0f到1.0f之间。1.0完全不透明，0.0f完全透明
            lp.dimAmount = 0.4f;//dimAmount在0.0f和1.0f之间，0.0f完全不暗，1.0f全暗
            window.setAttributes(lp);
            window.setBackgroundDrawable(new BitmapDrawable());
            View alertView = LayoutInflater.from(mContext).inflate(R.layout.layout_loading, null);
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
