package com.test.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.test.R;
import com.test.customview.PopoverView;

/**
 * Created by roger on 2018/2/6.
 */

public class PopverAvtivity extends BaseActivity implements View.OnClickListener, PopoverView.PopoverViewDelegate {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //get root layout
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.rootLayout);

        PopoverView popoverView = new PopoverView(this, R.layout.popover_showed_view);
        popoverView.setContentSizeForViewInPopover(new Point(320, 340));
        popoverView.setDelegate(this);
        popoverView.showPopoverFromRectInViewGroup(rootView, PopoverView.getFrameForView(v), PopoverView.PopoverArrowDirectionAny, true);

    }


    @Override
    public void popoverViewWillShow(PopoverView view) {
        Log.i("POPOVER", "Will show");
    }

    @Override
    public void popoverViewDidShow(PopoverView view) {
        Log.i("POPOVER", "Did show");
    }

    @Override
    public void popoverViewWillDismiss(PopoverView view) {
        Log.i("POPOVER", "Will dismiss");
    }

    @Override
    public void popoverViewDidDismiss(PopoverView view) {
        Log.i("POPOVER", "Did dismiss");
    }
}
