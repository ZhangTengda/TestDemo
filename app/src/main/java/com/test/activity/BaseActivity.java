package com.test.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.R;

import butterknife.ButterKnife;

/**
 * Created by roger on 2018/1/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected AlertDialog progressDialog;

    protected Context baseContext;

    protected BaseActivity baseActivity;
    private View dialogView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentId());
        ButterKnife.bind(this);

        baseActivity = BaseActivity.this;
        baseContext = getApplicationContext();
    }

    protected abstract int getContentId();

    /**
     * base activity show a dialog with pass message string
     *
     * @param message message string
     */
    public void showDialogWithMessage(String message) {

    }

    /**
     * dismiss dialog
     */
    public void dismissBaseDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * base activity show a dialog with pass message string
     */
    public void showLoadingDialog() {
        if (!baseActivity.isFinishing()) {
            if (progressDialog == null) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

                dialogView = LayoutInflater.from(baseContext).inflate(R.layout.custom_progressbar_layout, null);

                dialogBuilder.setView(dialogView);
                dialogBuilder.setCancelable(true);
                progressDialog = dialogBuilder.create();
            }

            TextView textView = dialogView.findViewById(R.id.custom_progressbar_message);
            textView.setText(getResources().getString(R.string.loading));

            progressDialog.show();
        }
    }

    /**
     * dismiss dialog
     */
    public void dismissLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
