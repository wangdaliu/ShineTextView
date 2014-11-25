package com.shine.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.shine.library.Shine;
import com.shine.library.ShineTextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private Shine mGrandient;
    private ShineTextView mGrandientTextView;
    private Button mToggle;
    private List<String> shineList = new ArrayList<String>();
    private static final long DEFAULT_DURATION = 3000;
    private static final long DEFAULT_START_DELAY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGrandientTextView = (ShineTextView) findViewById(R.id.grandient_view);
        mToggle = (Button) findViewById(R.id.toggle);

        shineList.add("dadadjjkcja daidajda dadjadlkjdkad adadaadada dadawodakodkad dawdadadw");
        shineList.add("1122231 31243242 4242 42342 24 234242342 2 3424 ");

        mToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGrandient != null && mGrandient.isAnimating()) {
                    mGrandient.cancel();
                } else {
                    mGrandient = new Shine(DEFAULT_DURATION, DEFAULT_START_DELAY, shineList);
                    mGrandient.start(mGrandientTextView);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
