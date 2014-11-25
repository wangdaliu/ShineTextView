package com.shine.android;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ShineViewHelper {
    private static final String TAG = ShineViewHelper.class.getSimpleName();

    private AnimationSetupCallback callback;

    public void setAnimationSetupCallback(AnimationSetupCallback callback) {
        this.callback = callback;
    }

    private boolean isSetUp;

    private float shinePercent;

    // all texts need to show;
    private List<String> shineList = new ArrayList<String>();

    private double[] mAlphas;

    private boolean mIsVisible;
    // current index of string list
    private int currentIndex = 0;


    public interface AnimationSetupCallback {
        void onSetupAnimation(View target);
    }

    private View view;
    private boolean isShining;

    private String text;

    public ShineViewHelper(View view) {
        this.view = view;
    }

    public boolean isSetUp() {
        return isSetUp;
    }

    public void setShine(boolean isShining) {
        this.isShining = isShining;
        mIsVisible = !isShining;
    }

    protected void onSizeChanged() {
        if (!isSetUp) {
            isSetUp = true;
            if (callback != null) {
                callback.onSetupAnimation(view);
            }
        }
    }

    public void setShinePercent(float shinePercent) {
        this.shinePercent = shinePercent;
    }

    public void setShineList(List<String> shineList) {
        this.shineList = shineList;
        resetAlphas();
    }

    private void resetAlphas() {
        text = shineList.get(currentIndex);
        mAlphas = new double[text.length()];
        for (int i = 0; i < mAlphas.length; i++) {
            mAlphas[i] = Math.random() - 1;
        }
    }

    private int clamp(double f) {
        return (int) (255 * Math.min(Math.max(f, 0), 1));
    }

    public SpannableString onDraw(int currentColor) {
        if (isShining) {
            SpannableString spannableString = new SpannableString(text);
            for (int i = 0; i < spannableString.length(); i++) {
                spannableString.setSpan(
                        new ForegroundColorSpan(Color.argb(clamp(mAlphas[i] + (mIsVisible ? shinePercent : 2.0f - shinePercent)), Color.red(currentColor), Color.green(currentColor), Color.blue(currentColor))), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            return spannableString;
        }
        return null;
    }

    public void toggle() {
        if (currentIndex >= shineList.size() - 1) {
            currentIndex = 0;
        } else {
            currentIndex++;
        }
        resetAlphas();

        mIsVisible = !mIsVisible;
    }
}
