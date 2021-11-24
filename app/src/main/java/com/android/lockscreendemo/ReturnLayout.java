package com.android.lockscreendemo;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

public class ReturnLayout extends RelativeLayout {

    public View backView;
    public ViewDragHelper viewDragHelper;
    //private Point mAutoBackOriginPos = new Point();

    public ReturnLayout(Context context) {
        super(context);
    }

    public ReturnLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViewHelper();
    }

    public ReturnLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void initViewHelper() {  //好多View位置判断都是绝对坐标，待改进
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return child == backView;
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                viewDragHelper.settleCapturedViewAt(550, 2100); //View 回弹位置
                invalidate();
            }

            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return 550;
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
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("test", event.getY() + "");
        viewDragHelper.processTouchEvent(event);
        if (event.getY() < 1277) {
            LockActivity.instance.finish();
        }
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        backView = getChildAt(3);


    }
}
