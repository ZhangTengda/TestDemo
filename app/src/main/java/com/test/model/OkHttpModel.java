package com.test.model;

import android.content.Context;

import com.squareup.okhttp.Request;
import com.test.http.HttpUtils;
import com.test.http.ResultCallBack;
import com.test.view.IOkUtilsActivity;

import java.util.Map;

/**
 * Created by roger on 2018/7/28.
 */

public class OkHttpModel {

    public void sendRestaurantRequest(Context context, String url, Map<String, String> paramsMap, final IOkUtilsActivity view) {
        HttpUtils.sendGetRequest(context, url, paramsMap, new ResultCallBack() {
            @Override
            public void onNetWorkNo() {
                view.onNetWorkNo();
            }

            @Override
            public void onSuccess(String ApiName, String response) {
                view.requestSuccess(ApiName, response);
            }

            @Override
            public void onFailure(Request request, Exception e) {
                view.requestFailure(request, e);
            }
        });
    }
}