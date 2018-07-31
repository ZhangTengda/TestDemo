package com.test.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.test.R;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Created by roger on 2018/7/24.
 */

public class ZXingActivity extends BaseActivity{

    @Override
    protected int getContentId() {
        return R.layout.activity_zxing;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CountDownLatch countDownLatch = new CountDownLatch(3);
        countDownLatch.countDown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        SharedPreferences sharedPreferences = new SharedPreferences() {
            @Override
            public Map<String, ?> getAll() {
                return null;
            }

            @Nullable
            @Override
            public String getString(String key, @Nullable String defValue) {
                return null;
            }

            @Nullable
            @Override
            public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
                return null;
            }

            @Override
            public int getInt(String key, int defValue) {
                return 0;
            }

            @Override
            public long getLong(String key, long defValue) {
                return 0;
            }

            @Override
            public float getFloat(String key, float defValue) {
                return 0;
            }

            @Override
            public boolean getBoolean(String key, boolean defValue) {
                return false;
            }

            @Override
            public boolean contains(String key) {
                return false;
            }

            @Override
            public Editor edit() {
                return null;
            }

            @Override
            public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }

            @Override
            public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }
        }

    }
}
