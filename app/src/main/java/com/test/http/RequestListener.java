package com.test.http;

import com.android.volley.VolleyError;

/**
 * Created by roger on 2018/3/27.
 */

public interface RequestListener {
    /**
     * 请求成功
     */
    void onSuccess(String APIName, String response);

    /**
     * 请求失败
     */
    void onFailure(String APIName, VolleyError error);
}
