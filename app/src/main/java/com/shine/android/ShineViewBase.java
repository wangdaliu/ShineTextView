package com.shine.android;

import java.util.List;

public interface ShineViewBase {
    public void setAnimationSetupCallback(ShineViewHelper.AnimationSetupCallback callback);

    public boolean isSetUp();

    public void setShine(boolean isShining);

    public void setShinePercent(float percent);

    public void setShineList(List<String> shineList);

    public void toggle();
}
