package com.test.view;


import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatImageView;

/**
 * Created by roger on 2018/7/25.
 */

public class CustomImageView extends AppCompatImageView {
    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }

    @Override
    public void postInvalidate() {
        super.postInvalidate();
    }

}
