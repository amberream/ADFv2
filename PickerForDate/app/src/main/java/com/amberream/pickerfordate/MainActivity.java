package com.amberream.pickerfordate;

import android.support.v4.app.DialogFragment;
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

    public void showDatePicker(View view) {
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), getString(R.string.date_fragment));
    }

    public void showDatePickerResult(int year, int month, int day)
    {
        String message = getString(R.string.date) + ":" + (month + 1) + "/" + day + "/" + year;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
