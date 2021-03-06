package com.shine.library;

import java.util.List;

public interface ShineViewBase {
    public void setAnimationSetupCallback(ShineViewHelper.AnimationSetupCallback callback);

    public boolean isSetUp();

    public void setShine(boolean isShining);

    public void setShinePercent(double percent);

    public void setShineList(List<String> shineList);

    public void toggle();
}
