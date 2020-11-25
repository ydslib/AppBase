/**
 * Created by : yds
 * Time: 2020-11-22 10:00 PM
 */
package com.crystallake.basic.base.activity;

import android.os.Bundle;

import com.crystallake.basic.base.mvp.presenter.BasePresenter;
import com.crystallake.basic.base.mvp.view.IView;
import com.trello.rxlifecycle4.LifecycleTransformer;
import com.trello.rxlifecycle4.android.ActivityEvent;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements IView {

    protected P mPresenter;

    protected abstract P createPresenter();

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showDefaultMsg(String msg) {

    }

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mPresenter = null;
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }
}
