package com.test.factory;

import com.test.presenter.IOkUtilsActivity;
import com.test.presenter.ImpOkUtilsActivity;

/**
 * Created by roger on 2018/3/26.
 */

public class PresenterFactory {
    public static IOkUtilsActivity.Presenter getOkUtilsPresenter(IOkUtilsActivity.View view){
        return new ImpOkUtilsActivity(view);
    }
}
