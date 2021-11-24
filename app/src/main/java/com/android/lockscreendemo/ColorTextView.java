package com.android.lockscreendemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ColorTextView extends androidx.appcompat.widget.AppCompatTextView {
//动态彩色字体
    public int mViewWidth;
    public TextPaint mPaint;
    public int[] color = new int[]{0xFFFFEABA,0xFFBE8B49};

    public ColorTextView(@NonNull Context context) {
        super(context);
    }

    public ColorTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mViewWidth = getMeasuredWidth();
        mPaint = getPaint();
        String mText = getText().toString();
        Rect mTextBound = new Rect();
        mPaint.getTextBounds(mText,0,mText.length(),mTextBound);
        LinearGradient linearGradient = new LinearGradient(0,0,mViewWidth,0,color,null, Shader.TileMode.REPEAT);
        invalidate();//重绘这个View
        color[0] = color[0]-500;
        color[1] = color[1]-500;
        mPaint.setShader(linearGradient);
        canvas.drawText(mText,200,400,mPaint);

    }
}
