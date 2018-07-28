package com.test.view;

import android.content.Context;

import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * Created by roger on 2018/3/26.
 */

public interface IOkUtilsActivity {

    void requestSuccess(String ApiName, String response);

    void requestFailure(Request request, Exception e);

    void onNetWorkNo();

}
