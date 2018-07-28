package com.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.test.activity.BaseActivity;
import com.test.activity.CalendarActivity;
import com.test.activity.EventBusAvtivity;
import com.test.activity.OkHttpUtilsAvtivity;
import com.test.activity.PopverAvtivity;
import com.test.activity.SecondActivity;
import com.test.adapter.MainActivityAdapter;

import butterknife.BindView;

/**
 * Created by roger on 2018/1/21.
 */

public class MainActivity extends BaseActivity implements MainActivityAdapter.RecyclerViewItemClickListener {

    @BindView(R.id.activity_main_recyclerview)
    RecyclerView mainRecyclerView;
    //
    private String[] indexArray = {"Calendar", "Demo", "OkHttpUtils", "EventBus", "1",
            "11", "111", "1111", "11111", "111111", "1111111", "11111111", "111111111", "1111111111",
            "11111", "111111", "11111", "11111", "11111", "11111", "11111", "11111", "11111",
            "111111", "11111", "11111", "11111", "11111", "11111", "11111", "11111", "111111",
            "11111", "11111", "11111", "11111", "11111", "11111", "11111", "111111", "11111",
            "11111", "11111", "11111", "11111", "11111", "11111", "111111", "11111", "11111",
            "11111", "11111", "11111", "11111", "11111", "111111", "11111", "11111", "11111",
            "11111", "11111", "11111", "11111", "111111", "11111", "11111", "11111", "11111",
            "11111", "11111", "11111", "111111", "11111", "11111", "11111", "11111", "11111",
            "111111", "11111", "11111", "11111", "11111", "11111", "11111", "11111", "11111", "11111"};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainActivityAdapter mainAdapter = new MainActivityAdapter(this, indexArray);
        mainRecyclerView.setAdapter(mainAdapter);
        mainAdapter.addRecyclerViewClickListener(this);
    }


    @Override
    protected int getContentId() {
        return R.layout.activity_contact_list;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0: {
                calendarClick();

                break;
            }
            case 1: {
                popverViewClick();

                break;
            }
            case 2: {
                okHttpClick();

                break;
            }
            case 3: {
                eventsBusClick();

                break;
            }

        }
    }

    public void calendarClick() {
        Intent intent = new Intent();
        intent.setClass(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void popverViewClick() {
        Intent intent = new Intent();
        intent.setClass(this, PopverAvtivity.class);
        startActivity(intent);
    }

    public void okHttpClick() {
        Intent intent = new Intent();
        intent.setClass(this, OkHttpUtilsAvtivity.class);
        startActivity(intent);
    }

    public void eventsBusClick() {
        Intent intent = new Intent();
        intent.setClass(this, EventBusAvtivity.class);
        startActivity(intent);
    }
}
