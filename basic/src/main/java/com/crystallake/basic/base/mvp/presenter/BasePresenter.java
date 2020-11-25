/**
 * Created by : yds
 * Time: 2020-11-22 10:01 PM
 */
package com.crystallake.basic.base.mvp.presenter;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.crystallake.basic.base.mvp.view.IView;
import com.crystallake.basic.base.mvp.model.IModel;
import com.crystallake.basic.utils.Preconditions;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter<V>, LifecycleObserver {
    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeDisposable mCompositeDisposable;
    private M mModel;
    private V mView;

    public BasePresenter() {
        this.mModel = createModel();
    }

    /**
     * 获取 Model
     *
     * @return
     */
    protected abstract M createModel();

    public V getView() {
        Preconditions.checkNotNull(mView, "%s cannot be null", IView.class.getName());
        return mView;
    }

    public M getModel() {
        Preconditions.checkNotNull(mView, "%s cannot be null", IView.class.getName());
        return mModel;
    }

    /**
     * 如果要使用 Eventbus 请将此方法返回 true
     */
    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void attachView(V mView) {
        this.mView = mView;
        if (mView != null && mView instanceof LifecycleOwner) {
            ((LifecycleOwner) mView).getLifecycle().addObserver(this);
            if (mModel != null && mModel instanceof LifecycleObserver) {
                ((LifecycleOwner) mView).getLifecycle().addObserver((LifecycleObserver) mModel);
            }
        }
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void detachView() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        unDispose();// 解除订阅
        if (mModel != null)
            mModel.onDetach();
        this.mModel = null;
        this.mView = null;
        this.mCompositeDisposable = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
        Log.d(TAG,"onDestroy");
    }

    public void addDispose(Disposable disposable){
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 停止正在进行的任务
     */
    public void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证Activity结束时取消
        }
    }


}
