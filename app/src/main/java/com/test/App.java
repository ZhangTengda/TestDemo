package com.test;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by roger on 2018/3/21.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        ZXingLibrary.initDisplayOpinion(this);
    }
}
