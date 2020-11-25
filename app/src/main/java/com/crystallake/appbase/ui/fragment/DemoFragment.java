/**
 * Created by : yds
 * Time: 2020-11-22 9:13 PM
 */
package com.crystallake.appbase.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.crystallake.appbase.R;
import com.crystallake.appbase.event.MessageEvent;
import com.crystallake.basic.base.fragment.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class DemoFragment extends BaseFragment {

    @BindView(R.id.tv_result)
    TextView tv_result;

    @Override
    protected int getLayoutRes() {
        return R.layout.damo_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.btn_send)
    public void doClick(View view) {
        sendMessage();
    }

    private void sendMessage() {
        EventBus.getDefault().post(new MessageEvent("message_fragment"));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showMessage(MessageEvent event) {
        tv_result.setText(event.getMessage());
    }
}
