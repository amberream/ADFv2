package com.amberream.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.amberream.roomwordssample.REPLY";

    EditText mEditWordView;
    Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditWordView = findViewById(R.id.edit_word);
        mSaveButton = findViewById(R.id.button_saved);
    }

    public void saveWord(View view) {
        Intent intent = new Intent();
        if (TextUtils.isEmpty(mEditWordView.getText()))
        {
            setResult(RESULT_CANCELED, intent);
        }
        else
        {
            intent.putExtra(EXTRA_REPLY, mEditWordView.getText().toString());
            setResult(RESULT_OK, intent);
        }
        // Call this when your activity is done and should be closed
        // The ActivityResult is propagated back to whoever launched you via onActivityResult()
        finish();
    }
}
