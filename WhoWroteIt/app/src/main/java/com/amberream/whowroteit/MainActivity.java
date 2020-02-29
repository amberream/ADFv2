package com.amberream.whowroteit;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        // hide the keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null)
        {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (connectivityManager != null && !connectivityManager.getActiveNetworkInfo().isConnected())
        {
            Toast.makeText(this, R.string.no_network, Toast.LENGTH_LONG).show();
            return;
        }

        String query = editTextQuery.getText().toString();
        if (query.isEmpty())
        {
            Toast.makeText(this, R.string.enter_search_term ,Toast.LENGTH_LONG).show();
            return;
        }

        // Do the search off the main thread
        new FetchBookTask(textViewAuthor, textViewTitle).execute(query);
        textViewAuthor.setText("");
        textViewTitle.setText(R.string.loading);
    }
}
