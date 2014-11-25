package com.shine.android;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Shine {
    private static final String TAG = Shine.class.getSimpleName();
    private ValueAnimator animator;
    private long duration;
    private long startDelay;
    private List<String> shineList = new ArrayList<String>();

    public Shine(long duration, long startDelay, List<String> shineList) {
        this.duration = duration;
        this.startDelay = startDelay;
        this.shineList = shineList;
    }

    public boolean isAnimating() {
        return animator != null && animator.isRunning();
    }

    public <V extends View & ShineViewBase> void start(final V shineView) {

        if (isAnimating()) {
            return;
        }

        final Runnable animate = new Runnable() {
            @Override
            public void run() {
                shineView.setShine(true);
                shineView.setShineList(shineList);
                animator = ValueAnimator.ofFloat(0.0f, 2.0f);
                animator.setDuration(duration);
                animator.setStartDelay(startDelay);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        Float percent = (Float) animator.getAnimatedValue();
                        shineView.setShinePercent(percent);
                        shineView.invalidate();
                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        shineView.setShine(false);
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            shineView.postInvalidate();
                        } else {
                            shineView.postInvalidateOnAnimation();
                        }

                        animator = null;
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        shineView.toggle();
                    }
                });
                animator.start();
            }
        };

        if (!shineView.isSetUp()) {
            shineView.setAnimationSetupCallback(new ShineViewHelper.AnimationSetupCallback() {
                @Override
                public void onSetupAnimation(final View target) {
                    animate.run();
                }
            });
        } else {
            animate.run();
        }
    }

    public void cancel() {
        if (animator != null) {
            animator.cancel();
        }
    }
}
