package com.test.presenter;

import android.content.Context;

import com.squareup.okhttp.Request;
import com.test.R;
import com.test.http.HttpUtils;
import com.test.http.ResultCallBack;

import java.util.Map;

/**
 * Created by roger on 2018/3/26.
 */

public class ImpOkUtilsActivity implements IOkUtilsActivity.Presenter {

    private IOkUtilsActivity.View myView;

    public ImpOkUtilsActivity(IOkUtilsActivity.View view) {
        myView = view;
        view.setPresenter(this);
    }

    @Override
    public void sendRestaurantRequest(Context context, String url, Map<String, String> paramsMap) {
        myView.showLoadingDialog();
        HttpUtils.sendGetRequest(context, url, paramsMap, new ResultCallBack() {
            @Override
            public void onNetWorkNo() {
                myView.onNetWorkNo();
            }

            @Override
            public void onSuccess(String ApiName, String response) {
                myView.requestSuccess(ApiName,response);
                myView.dismissLoadingDialog();
            }

            @Override
            public void onFailure(Request request, Exception e) {
                myView.requestFailure(request, e);
                myView.dismissLoadingDialog();
            }
        });
    }
}
