package com.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.test.R;
import com.test.factory.PresenterFactory;
import com.test.http.HttpUtils;
import com.test.http.ResultCallBack;
import com.test.presenter.IOkUtilsActivity;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by roger on 2018/3/21.
 */

public class OkHttpUtilsAvtivity extends BaseActivity implements IOkUtilsActivity.View {
    //    String url = "https://api.chope.co/restaurants/list?login_token=c0d603fe953cca3473d4a878ae9294c11b4bb8b44c17cfe600c892bda5226836&source=android&lang=en_US&country_code=SG&appVersionInfo=4.4.0";
    String url = "http://api.app.chope.cc/restaurants/list";
    private Map<String, String> parmasMap;

    private IOkUtilsActivity.Presenter myPresenter;

    @BindView(R.id.activity_okutils_textview)
    TextView contentTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PresenterFactory.getOkUtilsPresenter(this);

        parmasMap = new HashMap<>();
        parmasMap.put("source", "android");
        parmasMap.put("lang", "en_US");
        parmasMap.put("country_code", "SG");
        parmasMap.put("appVersionInfo", "4.4.0");
    }

    @OnClick(R.id.activity_okutils_button)
    void submit() {

        myPresenter.sendRestaurantRequest(this, url, parmasMap);
//        showLoadingDialog();
//        HttpUtils.sendGetRequest(this, url, parmasMap, new ResultCallBack() {
//            @Override
//            public void onNetWorkNo() {
//
//            }
//
//            @Override
//            public void onSuccess(String ApiName, String json) {
//                dismissLoadingDialog();
//                contentTextView.setText(json);
//            }
//
//            @Override
//            public void onFailure(Request request, Exception e) {
//                contentTextView.setText(e.getMessage());
//                dismissLoadingDialog();
//            }
//        });
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_okutils_layout;
    }

//    @Override
//    public void onNetWorkNo() {
//
//    }
//
//    @Override
//    public void onSuccess(String ApiName, String json) {
//        dismissBaseDialog();
//        contentTextView.setText(json);
//    }
//
//    @Override
//    public void onFailure(Request request, Exception e) {
//        dismissBaseDialog();
//    }

    @Override
    protected void onStop() {
        super.onStop();
        OkHttpUtils.getInstance().cancelTag(this);
    }

    @Override
    public void setPresenter(IOkUtilsActivity.Presenter presenter) {
        myPresenter = presenter;
    }

    @Override
    public void requestSuccess(String ApiName, String response) {
        contentTextView.setText(response);
    }

    @Override
    public void requestFailure(Request request, Exception e) {
        contentTextView.setText("shi bai le a");
    }

    @Override
    public void onNetWorkNo() {

    }
}
