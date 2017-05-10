package com.ycj.adming.test;

import android.view.View;
import android.widget.TextView;

import com.ycj.adming.R;
import com.ycj.adming.base.WithTitleBaseActivity;

import butterknife.BindView;

public class Main2Activity extends WithTitleBaseActivity {

    @BindView(R.id.tv1s)
    TextView tv1s;

    @Override
    protected View loadViewLayout() {
        return getLayoutInflater().inflate(R.layout.activity_main2, null);
    }

    @Override
    protected void initView() {
        tv1s.setText("gg e444444444444444444444444444444444444444444444444444444444444444444444444");
    }

    @Override
    protected void initData() {

    }
}
