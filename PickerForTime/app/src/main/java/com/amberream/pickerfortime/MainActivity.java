package com.amberream.pickerfortime;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startTimePicker(View view) {
        Fragment timePicker = new TimePickerFragment();
        ((TimePickerFragment) timePicker).show(getSupportFragmentManager(), getString(R.string.time_picker));
    }

    public void showTime(int hour, int minute) {
        Toast.makeText(this, hour + ":" + minute, Toast.LENGTH_SHORT).show();
    }
}
