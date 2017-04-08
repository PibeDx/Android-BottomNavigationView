package com.josecuentas.android_bottomnavigationview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MenuItem;

import com.josecuentas.android_bottomnavigationview.ui.fragment.FacebookFragment;

/*
* base: http://stackoverflow.com/a/31999206
* */

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private Fragment mFragment;
    private int[] bottomBarColors;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        mBottomNavigationView.setItemIconTintList(null);
        //mBottomNavigationView.setItemIconTintList(ContextCompat.getColorStateList(this, R.color.facebook_bottombar_color));
        bottomBarColors = new int[]{
//            R.drawable.facebook_bottombar_color,
//            R.drawable.twitter_bottombar_color,
            R.color.color_facebook,
            R.color.color_twitter
        };
        changeFragment(0);
        changeBottomBarColor(mBottomNavigationView, 0);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action1:
                        changeFragment(0);
                        changeBottomBarColor(mBottomNavigationView, 0);
                        break;
                    case R.id.action2:
                        changeFragment(1);
                        changeBottomBarColor(mBottomNavigationView, 1);
                        break;
                    default: return true;
                }
                return true;
            }
        });



    }

    public static int getThemeColor(Context c, int resId) {
        TypedValue typedValue = new TypedValue();
        c.getTheme().resolveAttribute(resId, typedValue, true);
        return typedValue.data;
    }

    public static boolean isConnectivityAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
            context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void changeBottomBarColor(BottomNavigationView bottomNavigationView, int index) {
        if (bottomBarColors != null) {
            bottomNavigationView.setItemBackgroundResource(bottomBarColors[index]);
            bottomNavigationView.setBackgroundResource(bottomBarColors[index]);

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setNavigationBarColor(ContextCompat.getColor(this, bottomBarColors[index]));
            }
        }
    }

    private void changeFragment(int position) {
        Fragment newFragment = null;

        if (position == 0) {
            newFragment = new FacebookFragment();
        } else if (position % 2 != 0) {
            newFragment = new FacebookFragment();
        } else {
            newFragment = new FacebookFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(
            R.id.frameContainer, newFragment)
            .commit();
    }
}