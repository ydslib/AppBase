package com.crystallake.basic.http.rx;


import com.crystallake.basic.app.BaseApp;
import com.crystallake.basic.base.mvp.view.IView;
import com.crystallake.basic.bean.BaseBean;
import com.crystallake.basic.http.HttpStatus;
import com.crystallake.basic.http.exception.ExceptionHandle;
import com.crystallake.basic.utils.NetworkUtil;

import io.reactivex.observers.ResourceObserver;

/**
 * @author chenxz
 * @date 2018/9/1
 * @desc BaseObserver
 */
public abstract class BaseObserver<T extends BaseBean> extends ResourceObserver<T> {

    private IView mView;
    private String mErrorMsg = "";
    private boolean bShowLoading = true;

    public BaseObserver(IView view) {
        this.mView = view;
    }

    public BaseObserver(IView view, boolean bShowLoading) {
        this.mView = view;
        this.bShowLoading = bShowLoading;
    }

    /**
     * 成功的回调
     */
    protected abstract void onSuccess(T t);

    /**
     * 错误的回调
     */
    protected void onError(T t) {
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (bShowLoading) mView.showLoading();
        if (!NetworkUtil.isConnected(BaseApp.getInstance())) {
            mView.showDefaultMsg("当前网络不可用，请检查网络设置");
            onComplete();
        }
    }

    @Override
    public void onNext(T t) {
        mView.hideLoading();
        if (t.getErrorCode() == HttpStatus.SUCCESS) {
            onSuccess(t);
        } else if (t.getErrorCode() == HttpStatus.TOKEN_INVALID) {
            // TODO 处理 token 过期
        } else {
            onError(t);
            if (!t.getErrorMsg().isEmpty()) {
                mView.showDefaultMsg(t.getErrorMsg());
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        mView.hideLoading();
        if (mView == null) {
            throw new RuntimeException("mView can not be null");
        }
        if (mErrorMsg.isEmpty()) {
            mErrorMsg = ExceptionHandle.handleException(e);
        }
        mView.showDefaultMsg(mErrorMsg);
    }

    @Override
    public void onComplete() {
        mView.hideLoading();
    }
}
