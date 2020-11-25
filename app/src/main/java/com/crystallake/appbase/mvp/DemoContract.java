/**
 * Created by : yds
 * Time: 2020-11-22 10:51 PM
 */
package com.crystallake.appbase.mvp;

import com.crystallake.basic.base.mvp.model.IModel;
import com.crystallake.basic.base.mvp.presenter.IPresenter;
import com.crystallake.basic.base.mvp.view.IView;

public interface DemoContract {
    interface View extends IView{

    }

    interface Presenter extends IPresenter<View>{

    }

    interface  Model extends IModel{

    }
}
