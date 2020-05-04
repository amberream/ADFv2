package com.amberream.scrollingtext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerForContextMenu(findViewById(R.id.textView_article));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }
}
