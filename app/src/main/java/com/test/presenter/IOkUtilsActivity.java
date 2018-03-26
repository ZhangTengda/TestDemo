package com.test.presenter;

import android.content.Context;

import com.squareup.okhttp.Request;
import com.test.presenter.view.BaseView;

import java.util.Map;

/**
 * Created by roger on 2018/3/26.
 */

public interface IOkUtilsActivity {

    interface View extends BaseView<IOkUtilsActivity.Presenter> {
        void requestSuccess(String ApiName, String response);

        void requestFailure(Request request, Exception e);

        void onNetWorkNo();
    }

    interface Presenter {
        void sendRestaurantRequest(Context context, String url, Map<String,String> params);
    }
}
