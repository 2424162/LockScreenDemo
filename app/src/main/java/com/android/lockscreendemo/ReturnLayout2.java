package com.android.lockscreendemo;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.customview.widget.ViewDragHelper;

public class ReturnLayout2 extends RelativeLayout {

    public View BackView;
    public ViewDragHelper viewDragHelper;
    private Point mAutoBackOriginPos = new Point();

    public ReturnLayout2(Context context) {
        super(context);
    }

    public ReturnLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewHelper();
    }

    public ReturnLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void initViewHelper(){
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return child == BackView;
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                viewDragHelper.settleCapturedViewAt(200,300);
                invalidate();
            }

            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return super.clampViewPositionHorizontal(child,left,dx);
            }

            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                return top;
            }
        });
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (viewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        Log.d("test",event.getY()+"");
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        BackView = getChildAt(0);

    }


}
