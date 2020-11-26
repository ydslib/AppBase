/**
 * Created by : yds
 * Time: 2020-11-22 8:47 PM
 */
package com.crystallake.basic.base.fragment.support;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.trello.rxlifecycle4.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends RxFragment {
    protected final String TAG = this.getClass().getSimpleName();

    protected View mRootView;
    protected Context mContext;

    /**
     * 绑定 ButterKnife 时返回的 Unbinder ，用于 ButterKnife 解绑
     */
    private Unbinder mUnbinder;


    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected boolean useEventBus() {
        return false;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutRes(), null);
            mUnbinder = ButterKnife.bind(this, mRootView);
            if (useEventBus()) {
                EventBus.getDefault().register(this);
            }
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        this.mUnbinder = null;
        this.mContext = null;
        this.mRootView = null;
    }
}
