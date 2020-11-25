/**
 * Created by : yds
 * Time: 2020-11-22 10:50 PM
 */
package com.crystallake.appbase.mvp.presenter;

import com.crystallake.appbase.mvp.DemoContract;
import com.crystallake.appbase.mvp.model.DemoModel;
import com.crystallake.basic.base.mvp.presenter.BasePresenter;

public class DemoPresenter extends BasePresenter<DemoContract.Model, DemoContract.View> implements DemoContract.Presenter {

    @Override
    protected DemoContract.Model createModel() {
        return new DemoModel();
    }

}
