package com.test.http;

import com.squareup.okhttp.Request;

/**
 * Created by roger on 2018/3/21.
 */

public interface ResultCallBack {

    void onNetWorkNo();

    void onSuccess(String json);

    void onFailure(Request request, Exception e);
}
