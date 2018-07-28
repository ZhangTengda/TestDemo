package com.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.test.R;
import com.test.presenter.OkhttpPresenter;
import com.test.view.IOkUtilsActivity;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by roger on 2018/3/21.
 */

public class OkHttpUtilsAvtivity extends BaseActivity implements IOkUtilsActivity {
    String url = "http://test.igancao.com/index.php";
    private Map<String, String> parmasMap;

    private OkhttpPresenter myPresenter;

    @BindView(R.id.activity_okutils_textview)
    TextView contentTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPresenter = new OkhttpPresenter(this);

//        PresenterFactory.getOkUtilsPresenter(this);

    }

    @OnClick(R.id.activity_okutils_button)
    void submit() {

        myPresenter.sendRestaurantRequest(this, url, parmasMap);
//        showLoadingDialog();
//        HttpUtils.sendVolleyGetRequest(this, url, parmasMap, new RequestListener() {
//
//            @Override
//            public void onSuccess(String ApiName, String json) {
//                dismissLoadingDialog();
//                contentTextView.setText(json);
//            }
//
//            @Override
//            public void onFailure(String ApiName, VolleyError error) {
//                contentTextView.setText(error.getMessage());
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
