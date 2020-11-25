/**
 * Created by : yds
 * Time: 2020-11-22 10:02 PM
 */
package com.crystallake.basic.base.mvp.presenter;

import com.crystallake.basic.base.mvp.view.IView;

public interface IPresenter<V extends IView> {
    /**
     * 绑定 View
     *
     * @param mView
     */
    void attachView(V mView);

    /**
     * 解绑 View
     */
    void detachView();
}
