package com.shine.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.shine.library.Shine;
import com.shine.library.ShineTextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private Shine mShine;
    private ShineTextView mShineTextView;
    private Button mStart;
    private Button mCancel;
    private List<String> shineList = new ArrayList<String>();
    private static final long DEFAULT_DURATION = 3000;
    private static final long DEFAULT_START_DELAY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShineTextView = (ShineTextView) findViewById(R.id.shine_view);
        mStart = (Button) findViewById(R.id.start);
        mCancel = (Button) findViewById(R.id.cancel);


        shineList.add("To be, or not to be- that is the question");
        shineList.add("That's quite objective and convincing.");

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShine == null) {
                    mShine = new Shine(DEFAULT_DURATION, DEFAULT_START_DELAY, shineList);
                    mShine.start(mShineTextView);
                }

            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShine != null && mShine.isAnimating()) {
                    mShine.cancel();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
