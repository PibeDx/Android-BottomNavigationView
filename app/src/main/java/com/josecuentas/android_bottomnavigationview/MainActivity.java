package com.josecuentas.android_bottomnavigationview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/*
* base: http://stackoverflow.com/a/31999206
* */

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action1: item.setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_facebook)); break;
                    case R.id.action2: item.setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_twitter)); break;
                    default: return false;
                }
                return true;
            }
        });

    }
}