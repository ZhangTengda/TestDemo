package com.test.presenter.view;

/**
 * Created by roger on 2018/3/26.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showLoadingDialog();

    void dismissLoadingDialog();
}
