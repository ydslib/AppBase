package com.crystallake.basic.http.rx.scheduler;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author chenxz
 * @date 2018/8/21
 * @desc IoMainScheduler
 */
public class IoMainScheduler extends BaseScheduler {
    public IoMainScheduler() {
        super(Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
