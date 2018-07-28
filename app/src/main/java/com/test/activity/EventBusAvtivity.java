package com.test.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.test.R;
import com.test.presenter.EventBusView;
import com.test.utils.AppUtil;
import com.test.utils.ImageUtil;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by roger on 2018/7/3.
 */

public class EventBusAvtivity extends BaseActivity implements EventBusView, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.eventbus_jieshou)
    public Button sendMessage;

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;

    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;

    @BindView(R.id.text)
    public TextView textView;

    int allTimeLong = 234;
    Handler mHandler = new Handler();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // EventBus 发送事件的时候，订阅事件的Activity还未执行需要使用 postSticky
        EventBus.getDefault().postSticky("朗朗上口，悠悠上脚");

        showDaoJiShi();
    }

    private void showDaoJiShi() {

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                if (allTimeLong > 0) {
//                    allTimeLong--;
//                    textView.setText("DaoJiShi " + allTimeLong + " sec");
//                    mHandler.postDelayed(this, 1000);
//                } else {
//                    Toast.makeText(EventBusAvtivity.this, "时间到............", Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
//        mHandler.postDelayed(runnable, 1000);



        new CountDownTimer(allTimeLong*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("seconds remaining: " +millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                textView.setText("done!");
            }
        }.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_eventbus;
    }


    @OnClick(R.id.eventbus_jieshou)
    public void sendMessageClick() {
        startActivity(SecondActivity.class);
    }

    @OnClick(R.id.zxing01)
    public void startZXing() {
        initPermissionUtils();
    }

    @OnClick(R.id.zxing_easy)
    public void easyClick() {
        cameraTask(R.id.zxing_easy);
    }

    @OnClick(R.id.zxing_pic)
    public void selectClick() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @OnClick(R.id.zxing_diy)
    public void diyClick() {
        cameraTask(R.id.zxing_diy);
    }

    @OnClick(R.id.zxing_create)
    public void createClick() {
        Intent intent = new Intent(EventBusAvtivity.this, QRCodeActivity.class);
        startActivity(intent);
    }

    private void initPermissionUtils() {
        String[] permissions = AppUtil.checkPermission(this);

        if (permissions.length == 0) {
            startActivity(ZXingActivity.class);
        } else {
            //申请权限
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
    }

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;


    /**
     * EsayPermissions接管权限处理逻辑
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    public void cameraTask(int viewId) {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            onClick(viewId);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "需要请求camera权限",
                    REQUEST_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Toast.makeText(this, "执行onPermissionsGranted()...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this, "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "当前App需要申请camera权限,需要打开设置页面么?")
                    .setTitle("权限申请")
                    .setPositiveButton("确认")
                    .setNegativeButton("取消", null /* click listener */)
                    .setRequestCode(REQUEST_CAMERA_PERM)
                    .build()
                    .show();
        }
    }

    /**
     * 按钮点击事件处理逻辑
     *
     * @param buttonId
     */
    private void onClick(int buttonId) {
        switch (buttonId) {
            case R.id.zxing_easy:
                Intent intent = new Intent(getApplication(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.zxing_diy:
                intent = new Intent(EventBusAvtivity.this, SecondActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(EventBusAvtivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(EventBusAvtivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(EventBusAvtivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQUEST_CAMERA_PERM) {
            Toast.makeText(this, "从设置页面返回...", Toast.LENGTH_SHORT)
                    .show();
        }
    }

}
