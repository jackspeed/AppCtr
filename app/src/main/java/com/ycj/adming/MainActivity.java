package com.ycj.adming;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends WithTitleBaseActivity {


    @BindView(R.id.iv_test)
    ImageView ivTest;
    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUIContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @Override
    protected void onBaseRefresh() {
        showContentView();
    }

    @OnClick({R.id.iv_test, R.id.tv_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_test:
                showEmptyView();
                break;
            case R.id.tv_test:
                showErrorView();
                break;
        }
    }
}
