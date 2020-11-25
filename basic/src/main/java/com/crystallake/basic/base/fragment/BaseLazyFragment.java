/**
 * Created by : yds
 * Time: 2020-11-23 9:23 PM
 */
package com.crystallake.basic.base.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.crystallake.basic.base.fragment.BaseFragment;

public abstract class BaseLazyFragment extends BaseFragment {
    /**
     * Fragment 处理懒加载，为了防止 setUserVisibleHint 进入多次导致数据重复加载
     */
    protected boolean mIsUiVisible = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser&&isVisible()&&mRootView!=null&&!mIsUiVisible){
            mIsUiVisible = true;
            lazyLoad();
        }else{
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    protected abstract void lazyLoad();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()&&mRootView!=null&&!mIsUiVisible){
            mIsUiVisible = true;
            lazyLoad();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsUiVisible = false;
    }
}
