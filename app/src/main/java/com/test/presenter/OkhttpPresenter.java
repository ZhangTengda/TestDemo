package com.test.presenter;

import android.content.Context;

import com.test.model.OkHttpModel;
import com.test.view.IOkUtilsActivity;

import java.util.Map;

/**
 * Created by roger on 2018/7/28.
 */

public class OkhttpPresenter {

    private IOkUtilsActivity view;

    private OkHttpModel okHttpModel;

    public OkhttpPresenter(IOkUtilsActivity view) {
        this.view = view;
        okHttpModel = new OkHttpModel();
    }

    /**
     *
     *
     * @param context
     * @param url
     * @param params
     */
    public void sendRestaurantRequest(Context context, String url, Map<String, String> params) {
        okHttpModel.sendRestaurantRequest(context, url, params, view);
    }
}
