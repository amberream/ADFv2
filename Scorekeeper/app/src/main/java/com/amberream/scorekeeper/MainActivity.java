package com.amberream.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TEAM1_SCORE = "TEAM1_SCORE";
    private static final String TEAM2_SCORE = "TEAM2_SCORE";

    private TextView mTextViewTeam1;
    private TextView mTextViewTeam2;

    private int mScore1;
    private int mScore2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewTeam1 = findViewById(R.id.textView_scoreTeam1);
        mTextViewTeam2 = findViewById(R.id.textView_scoreTeam2);

        // init the scores
        if (savedInstanceState != null)
        {
            mScore1 = savedInstanceState.getInt(TEAM1_SCORE, 0);
            mScore2 = savedInstanceState.getInt(TEAM2_SCORE, 0);
        }
        else
        {
            mScore1 = 0;
            mScore2 = 0;
        }

        //set the scores on the views
        mTextViewTeam1.setText("" + mScore1);
        mTextViewTeam2.setText("" + mScore2);
    }

    public void decreaseScore(View view) {
        if (view.getId() == R.id.button_decreaseTeam1) {
            mScore1--;
            mTextViewTeam1.setText("" + mScore1);
        } else if (view.getId() == R.id.button_decreaseTeam2) {
            mScore2--;
            mTextViewTeam2.setText("" + mScore2);
        }
    }

    public void increaseScore(View view) {
        if (view.getId() == R.id.button_increaseTeam1) {
            mScore1++;
            mTextViewTeam1.setText("" + mScore1);
        } else if (view.getId() == R.id.button_increaseTeam2) {
            mScore2++;
            mTextViewTeam2.setText("" + mScore2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            menu.findItem(R.id.nightmode).setTitle(R.string.daymode);
        }
        else
        {
            menu.findItem(R.id.nightmode).setTitle(R.string.nightmode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nightmode) {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(TEAM1_SCORE, mScore1);
        outState.putInt(TEAM2_SCORE, mScore2);
        super.onSaveInstanceState(outState);
    }
}
