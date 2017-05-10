package com.ycj.adming.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ycj.adming.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by adming on 2017/4/13.
 */

public abstract class WithTitleBaseActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_back_bar)
    ImageView ivBackBar;
    @BindView(R.id.layout_back_bar)
    RelativeLayout layoutBackBar;
    @BindView(R.id.tv_title_base)
    TextView tvTitleBase;
    @BindView(R.id.tv_right_bar)
    TextView tvRightBar;
    @BindView(R.id.iv_right_bar)
    ImageView ivRightBar;
    @BindView(R.id.layout_right_bar)
    LinearLayout layoutRightBar;
    private LinearLayout layoutEmpty;
    private LinearLayout layoutError;
    private LinearLayout rootView;
    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_root_view, null);
        contentView = loadViewLayout();
        rootView.addView(contentView, 1);
        setContentView(rootView);
        ButterKnife.bind(this);
        initTitleView();
        initView();
        initData();
    }

    protected abstract View loadViewLayout();

    protected abstract void initView();

    protected abstract void initData();

    private void initTitleView() {
        layoutEmpty = (LinearLayout) findViewById(R.id.layout_empty_base);
        layoutError = (LinearLayout) findViewById(R.id.layout_error_base);

        ivRightBar = (ImageView) findViewById(R.id.iv_right_bar);
        ivBackBar = (ImageView) findViewById(R.id.iv_back_bar);
        tvTitleBase = (TextView) findViewById(R.id.tv_title_base);
        tvRightBar = (TextView) findViewById(R.id.tv_right_bar);

        findViewById(R.id.tv_title_empty_click_refresh).setOnClickListener(this);
        findViewById(R.id.tv_title_error_click_refresh).setOnClickListener(this);

        layoutRightBar.setOnClickListener(this);
        layoutBackBar.setOnClickListener(this);
    }


    //设置标题文本
    protected void setTitleText(String titleText) {
        tvTitleBase.setText(null == titleText ? "" : titleText);
    }

    //设置标题文本
    protected void setTitleText(int titleTextId) {
        if (titleTextId > 0) {
            tvTitleBase.setText(titleTextId);
        } else {
            tvTitleBase.setText("");
        }
    }

    /**
     * 左边按钮事件设置
     */

    protected void leftBarBtnEvent() {
        finish();
    }

    /**
     * 右边按钮事件设置
     */
    protected void rightBarBtnEvent() {

    }

    /**
     * 按后退键时
     */
    @Override
    public void onBackPressed() {
        leftBarBtnEvent();
    }


    /**
     * 个人主页右上角角标
     *
     * @param show 是否显示
     */
    protected void setTopRightCornerTv(boolean show) {
        if (show) {
            tvRightBar.setVisibility(View.VISIBLE);
        } else {
            tvRightBar.setVisibility(View.GONE);
        }
    }

    /**
     * 个人主页右上角角标文字
     */
    protected void setTopRightCornerTvText(String text) {
        tvRightBar.setText(text);
    }

    /**
     * 个人主页右上角角标文字
     */
    protected void setTopRightCornerTvText(int id) {
        tvRightBar.setText(id);
    }

    /**
     * 个人主页右上角角标
     *
     * @param show 是否显示
     */
    protected void setTopLeftCorner(boolean show) {
        if (show) {
            ivBackBar.setVisibility(View.VISIBLE);
        } else {
            ivBackBar.setVisibility(View.GONE);
        }
    }

    /**
     * 个人主页右上角角标
     *
     * @param show 是否显示
     */
    protected void setTopRightCornerIv(boolean show) {
        if (show) {
            ivRightBar.setVisibility(View.VISIBLE);
        } else {
            ivRightBar.setVisibility(View.GONE);
        }
    }

    /**
     * 个人主页右上角角标图片
     */
    protected void setTopRightCornerIvResource(int resId) {
        ivRightBar.setImageResource(resId);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back_bar:
                leftBarBtnEvent();
                break;
            case R.id.layout_right_bar:
                rightBarBtnEvent();
                break;
            case R.id.tv_title_error_click_refresh:
            case R.id.tv_title_empty_click_refresh:
                onBaseRefresh();
                break;
        }
    }

    public void showErrorView() {
        rootView.removeView(contentView);
        layoutEmpty.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
    }

    public void showEmptyView() {
        rootView.removeView(contentView);
        layoutError.setVisibility(View.GONE);
        layoutEmpty.setVisibility(View.VISIBLE);
    }

    public void showContentView() {
        rootView.addView(contentView, 1);
        layoutError.setVisibility(View.GONE);
        layoutEmpty.setVisibility(View.GONE);
    }


    protected void onBaseRefresh() {

    }
}
