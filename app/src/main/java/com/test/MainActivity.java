package com.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.test.activity.BaseActivity;
import com.test.activity.CalendarActivity;
import com.test.activity.PopverAvtivity;
import com.test.adapter.MainActivityAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roger on 2018/1/21.
 */

public class MainActivity extends BaseActivity implements MainActivityAdapter.RecyclerViewItemClickListener {
    @BindView(R.id.activity_main_recyclerview)
    RecyclerView mainRecyclerView;
    //
    private String[] indexArray = {"Calendar", "Demo"};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainActivityAdapter mainAdapter = new MainActivityAdapter(this, indexArray);
        mainRecyclerView.setAdapter(mainAdapter);
        mainAdapter.addRecyclerViewClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent();
        switch (position) {
            case 0: {
                intent.setClass(this, CalendarActivity.class);
                startActivity(intent);
                break;
            }
            case 1: {
                intent.setClass(this, PopverAvtivity.class);
                startActivity(intent);
                break;
            }

        }
    }
}
