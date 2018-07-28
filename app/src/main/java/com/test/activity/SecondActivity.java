package com.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.test.R;
import com.test.presenter.SecondActivityPresenter;
import com.test.presenter.SecondActivityView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by roger on 2018/7/5.
 */

public class SecondActivity extends BaseActivity implements SecondActivityView {

    @BindView(R.id.tv_second)
    public TextView secondTextView;

    private SecondActivityPresenter presenter = new SecondActivityPresenter(this);
    private String postString;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void getDefaultBus(String str) {
        this.postString = str;
    }

    @OnClick(R.id.tv_second)
    public void secondOnClick() {
        presenter.updateView(postString);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_second;
    }

    @Override
    public void showText(String str) {
        toastShort(str);
        secondTextView.setText(str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
