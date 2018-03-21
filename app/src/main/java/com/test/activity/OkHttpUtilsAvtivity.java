package com.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.test.R;
import com.test.http.HttpUtils;
import com.test.http.ResultCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by roger on 2018/3/21.
 */

public class OkHttpUtilsAvtivity extends BaseActivity implements ResultCallBack {
    String url = "http:测试的链接，已删除";
    private HashMap<String, String> parmasMap;

    @BindView(R.id.activity_okutils_textview)
    TextView contentTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parmasMap = new HashMap<>();
        parmasMap.put("source", "android");
        parmasMap.put("lang", "en_US");
        parmasMap.put("country_code", "SG");
        parmasMap.put("appVersionInfo", "4.4.0");
    }


    @OnClick(R.id.activity_okutils_button)
    void submit() {
        HttpUtils.sendGetRequest(this, url, parmasMap, this);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_okutils_layout;
    }

    @Override
    public void onNetWorkNo() {

    }

    @Override
    public void onSuccess(String json) {

    }

    @Override
    public void onFailure(Request request, Exception e) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
