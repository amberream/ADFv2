package com.amberream.drawabletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int mImageLevel = 1;

    private static int MIN_IMAGE_LEVEL = 1;
    private static int MAX_IMAGE_LEVEL = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increaseBattery(View view) {
        ((ImageView) findViewById(R.id.imageView)).setImageLevel(getImageLevel(1));
    }

    public void decreaseBattery(View view) {
        ((ImageView) findViewById(R.id.imageView)).setImageLevel(getImageLevel(-1));
    }

    private int getImageLevel(int i) {
        int temp = mImageLevel + i;
        if (temp >= MIN_IMAGE_LEVEL && temp <= MAX_IMAGE_LEVEL)
        {
            mImageLevel = temp;
        }
        return mImageLevel;
    }
}
