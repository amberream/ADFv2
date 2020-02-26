package com.amberream.whowroteit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextQuery;
    private TextView textViewAuthor;
    private TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextQuery = findViewById(R.id.editText_query);
        textViewAuthor = findViewById(R.id.textView_author);
        textViewTitle = findViewById(R.id.textView_title);
    }


    public void search(View view) {
        String query = editTextQuery.getText().toString();
        // Do the search off the main thread
        new FetchBookTask(textViewAuthor, textViewTitle).execute(query);
    }
}
