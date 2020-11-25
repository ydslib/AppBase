/**
 * Created by : yds
 * Time: 2020-11-22 11:20 PM
 */
package com.crystallake.appbase.ui.activity;

import com.crystallake.appbase.R;
import com.crystallake.appbase.mvp.DemoContract;
import com.crystallake.appbase.mvp.presenter.DemoPresenter;
import com.crystallake.basic.base.activity.BaseMvpActivity;

public class DemoActivity extends BaseMvpActivity<DemoPresenter> implements DemoContract.View {

    @Override
    protected DemoPresenter createPresenter() {
        return new DemoPresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showMsg(String msg) {

    }
}
