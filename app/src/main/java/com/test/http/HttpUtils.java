package com.test.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.Request;
import com.test.utils.AppUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by roger on 2018/3/21.
 */

public class HttpUtils {
    /**
     * api String
     *
     * http://api.m.mtime.cn/PageSubArea/TrailerList.api
     */


    private static RequestQueue queue;

    /**
     * get request
     *
     * @param context   context
     * @param url       url
     * @param parmasMap parmasMap
     * @param callBack  callBack
     */
    public static void sendGetRequest(Context context, final String url, Map<String, String> parmasMap, final ResultCallBack callBack) {
        if (!AppUtil.isNetworkAvailable(context)) {
            if (callBack != null) {
                callBack.onNetWorkNo();
            }
            return;
        }

        OkHttpUtils.get()
                .url(url)
                .addHeader("Authorization", basic)
                .params(parmasMap)
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
                            callBack.onSuccess(url, response);
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
    public static void sendPostRequest(Context context, final String url, Map<String, String> params,
                                       final ResultCallBack callBack) {
        if (!AppUtil.isNetworkAvailable(context)) {
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
                            callBack.onSuccess(url, response);
                        }
                    }
                });


    }


    private static String basic = "Basic " + Base64.encodeToString(("test:5QeJsWHMx3").getBytes(), Base64.NO_WRAP);


    public static void sendVolleyGetRequest(Context context, final String url, Map<String, String> parmasMap,
                                            final RequestListener callBack) {

        if (queue == null)
            queue = Volley.newRequestQueue(context);

        String apiUrl = appendParamsWithURL(url, parmasMap);

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, apiUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (callBack != null)
                            callBack.onSuccess(url, response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (callBack != null)
                            callBack.onFailure(url, volleyError);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return configAPIHTTPRequestHeaders();
            }
        };
        stringRequest.setTag("volleyget");//tag can use ApiName.
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(25000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }


    private static Map<String, String> configAPIHTTPRequestHeaders() {
        Map<String, String> headersMap = new HashMap<String, String>();
        String basic = "Basic " + Base64.encodeToString(("test:5QeJsWHMx3").getBytes(), Base64.NO_WRAP);
        headersMap.put("Authorization", basic);

        return headersMap;
    }

    /**
     * append params with URL
     *
     * @param url    url
     * @param params params Map
     * @return full url
     */
    public static String appendParamsWithURL(String url, Map<String, String> params) {
        //
        String requestURL = "";
        if (!TextUtils.isEmpty(url)) {
            if (params == null || params.size() == 0) {
                requestURL = url;
            } else {
                Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(url);
                if (url.contains("?")) {
                    stringBuilder.append("&");
                } else {
                    stringBuilder.append("?");
                }

                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                        stringBuilder.append(key);
                        stringBuilder.append("=");
                        stringBuilder.append(value);
                        stringBuilder.append("&");
                    }
                }

                if (stringBuilder.length() > 0) {
                    requestURL = stringBuilder.substring(0, stringBuilder.length() - 1);
                }
            }
        }

        return requestURL;
    }

}
