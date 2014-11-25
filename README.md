ShineTextView


## How to use

Add a `ShineTextView` to your layout:
```xml
<com.shine.library.ShineTextView
    android:id="@+id/shine_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:textSize="40sp"
    android:textColor="@android:color/black"/>
```

To start the animation:
```java
mShine = new Shine(DEFAULT_DURATION, DEFAULT_START_DELAY, shineList);
mShine.start(mShineTextView);
```

You may want to keep track of the shimmer instance after the animation is started if you want to stop it.

To stop it:
```java
if (mShine != null && mShine.isAnimating()) {
    mShine.cancel();
}
```
