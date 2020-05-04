package com.amberream.hellocompat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.hello_textview);

        if (savedInstanceState != null)
        {
            mTextView.setTextColor(savedInstanceState.getInt("color"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("color", mTextView.getCurrentTextColor());
    }

    public void changeColor(View view) {
        Random random = new Random();
        String colorName = mColorArray[random.nextInt(20)];
        // get the resource id
        int colorResourceId = getResources().getIdentifier(colorName, "color", getApplicationContext().getPackageName());

        // requires api 23
//        int colorRes = getResources().getColor(colorResourceId, this.getTheme());
        int color = ContextCompat.getColor(this, colorResourceId);
        mTextView.setTextColor(color);

    }

}
