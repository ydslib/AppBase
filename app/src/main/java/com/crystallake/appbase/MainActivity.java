package com.crystallake.appbase;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.crystallake.appbase.ui.activity.DemoActivity;
import com.crystallake.basic.base.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    public void startDemoActivity(View view) {
        Intent intent = new Intent(this, DemoActivity.class);
        startActivity(intent);
    }
}