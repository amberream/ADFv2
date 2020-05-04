package com.amberream.checkboxesshowtoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Android fundamentals 04.2: Input controls Homework
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        StringBuilder builder = new StringBuilder();

        List<CheckBox> checkBoxes = new ArrayList<CheckBox>();
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox_chocolateSyrup));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox_sprinkles));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox_crushedNuts));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox_cherries));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox_oreos));

        for (CheckBox cb : checkBoxes) {
            if (cb.isChecked()) {
                builder.append(cb.getText() + " ");
            }
        }

        Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
    }
}
