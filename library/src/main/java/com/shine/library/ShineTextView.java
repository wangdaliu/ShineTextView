package com.shine.library;

import android.content.Context;
import android.graphics.Canvas;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.List;

public class ShineTextView extends TextView implements ShineViewBase {

    private ShineViewHelper mShineViewHelper;

    public ShineTextView(Context context) {
        super(context);
        mShineViewHelper = new ShineViewHelper(this);
    }

    public ShineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mShineViewHelper = new ShineViewHelper(this);
    }

    public ShineTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mShineViewHelper = new ShineViewHelper(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mShineViewHelper != null) {
            SpannableString spannableString = mShineViewHelper.onDraw(getCurrentTextColor());
            setText(spannableString);
        }
        super.onDraw(canvas);
    }

    @Override
    public void setAnimationSetupCallback(ShineViewHelper.AnimationSetupCallback callback) {
        mShineViewHelper.setAnimationSetupCallback(callback);
    }

    @Override
    public boolean isSetUp() {
        return mShineViewHelper.isSetUp();
    }

    @Override
    public void setShine(boolean isShining) {
        mShineViewHelper.setShine(isShining);
    }

    @Override
    public void setShinePercent(double percent) {
        mShineViewHelper.setShinePercent(percent);
    }

    @Override
    public void setShineList(List<String> shineList) {
        mShineViewHelper.setShineList(shineList);
    }

    @Override
    public void toggle() {
        mShineViewHelper.toggle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mShineViewHelper != null) {
            mShineViewHelper.onSizeChanged();
        }
    }
}
