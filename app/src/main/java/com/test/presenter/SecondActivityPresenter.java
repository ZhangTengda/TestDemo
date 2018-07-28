package com.test.presenter;

/**
 * Created by roger on 2018/7/13.
 */

public class SecondActivityPresenter {

    private SecondActivityView view;

    public SecondActivityPresenter(SecondActivityView view) {
        this.view = view;
    }

    public void updateView(String str) {
        view.showText(str);
    }
}
