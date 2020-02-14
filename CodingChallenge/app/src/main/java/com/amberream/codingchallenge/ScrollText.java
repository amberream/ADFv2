package com.amberream.codingchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScrollText extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_text);

        mTextView = findViewById(R.id.textView);
        String textNumber = getIntent().getStringExtra(MainActivity.TEXT_NUMBER);
        if (MainActivity.TEXT_ONE.equals(textNumber))
        {
            mTextView.setText(R.string.lorem_ipsum);
        }
        else if (MainActivity.TEXT_TWO.equals(textNumber))
        {
            mTextView.setText(R.string.bacon_ipsum);
        }
        else if (MainActivity.TEXT_THREE.equals(textNumber))
        {
            mTextView.setText(R.string.veggie_ipsum);
        }
    }
}
