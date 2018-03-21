package com.test.http;

import android.content.Context;
import android.util.Base64;

import com.squareup.okhttp.Request;
import com.test.utils.AppUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roger on 2018/3/21.
 */

public class HttpUtils {

    /**
     * get request
     *
     * @param context   context
     * @param url       url
     * @param parmasMap parmasMap
     * @param callBack  callBack
     */
    public static void sendGetRequest(Context context, String url, HashMap<String, String> parmasMap, final ResultCallBack callBack) {
        if (!AppUtils.isNetworkAvailable(context)) {
            if (callBack != null) {
                callBack.onNetWorkNo();
            }
            return;
        }

        OkHttpUtils.get()
                .url(url)
                .addHeader("Authorization", basic)
                .params(parmasMap)
                .tag(context)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        if (callBack != null) {
                            callBack.onFailure(request, e);
                        }
                    }

                    @Override
                    public void onResponse(String response) {
                        if (callBack != null) {
                            callBack.onSuccess(response);
                        }
                    }
                });
    }

    /**
     * post request
     *
     * @param context  context
     * @param url      url
     * @param params   params
     * @param callBack callBack
     */
    public static void sendPostRequest(Context context, String url, Map<String, String> params, final ResultCallBack callBack) {
        if (!AppUtils.isNetworkAvailable(context)) {
            if (callBack != null) {
                callBack.onNetWorkNo();
            }
            return;
        }
        OkHttpUtils.post()
                .url(url)
                .params(params)
                .tag(context)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        if (callBack != null) {
                            callBack.onFailure(request, e);
                        }
                    }

                    @Override
                    public void onResponse(String response) {
                        if (callBack != null) {
                            callBack.onSuccess(response);
                        }
                    }
                });


    }


    private static String basic = "Basic " + Base64.encodeToString(("m").getBytes(), Base64.NO_WRAP);
}
