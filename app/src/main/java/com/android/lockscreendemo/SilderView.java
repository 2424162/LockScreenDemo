package com.android.lockscreendemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.customview.widget.ViewDragHelper;


public class SilderView extends View {
    private int downX;
    private static String tag = "test1";
    private int downY;
    private int vHeight = this.getMeasuredHeight();


    private int vleft = this.getLeft();
    private int vright = this.getRight();
    private int vtop = this.getTop();
    private int vbottom = this.getBottom();

    public SilderView(Context context) {
        this(context, null);
        layout(vleft, vtop, vright, vbottom);
    }

    public SilderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SilderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(tag, event.toString());
//        ViewDragHelper viewDragHelper = new ViewDragHelper(getContext(), this, new ViewDragHelper.Callback() {
//            @Override
//            public boolean tryCaptureView(@NonNull View child, int pointerId) {
//                return false;
//            }
//
//
//        });
//        viewDragHelper.processTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actionDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                actionMove(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return super.onTouchEvent(event);
    }

    private void actionDown(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        downX = x;
        downY = y;
    }

    private void actionMove(MotionEvent event) {
        Log.d(tag, "Y相对位置" + event.getRawY());
        int s = (int) event.getY();
        this.offsetTopAndBottom(s);0
    }

    @Override
    public void getLocationInWindow(int[] outLocation) {
        super.getLocationInWindow(outLocation);
        int x = outLocation[0];
        Log.d(tag, x + "宽度");
    }

    private void changeImg() {
        ViewGroup.LayoutParams lp = this.getLayoutParams();
        lp.width = (200);
        lp.height = (200);


        this.setLayoutParams(lp);

    }

    private void changeImg2() {
        ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
        layoutParams.width = 100;
        layoutParams.height = 100;
        setLayoutParams(layoutParams);
    }
}
