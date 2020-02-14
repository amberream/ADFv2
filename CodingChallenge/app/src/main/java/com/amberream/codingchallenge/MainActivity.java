package com.amberream.codingchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TEXT_NUMBER = "com.amberream.codingchallenge.TEXT_NUMBER";

    public static final String TEXT_ONE = "textOne";
    public static final String TEXT_TWO = "textTwo";
    public static final String TEXT_THREE = "textThree";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void startScrollTextActivity(String textNumber)
    {
        Intent intent = new Intent(this, ScrollText.class);
        intent.putExtra(TEXT_NUMBER, textNumber);
        startActivity(intent);
    }

    public void showTextOne(View view) {
        startScrollTextActivity(TEXT_ONE);
    }

    public void showTextTwo(View view) {
        startScrollTextActivity(TEXT_TWO);
    }

    public void showTextThree(View view) {
        startScrollTextActivity(TEXT_THREE);
    }
}
