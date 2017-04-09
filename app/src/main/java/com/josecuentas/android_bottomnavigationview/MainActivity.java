package com.josecuentas.android_bottomnavigationview;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.josecuentas.android_bottomnavigationview.ui.fragment.FacebookDrawerFragment;
import com.josecuentas.android_bottomnavigationview.ui.fragment.FacebookFragment;
import com.josecuentas.android_bottomnavigationview.ui.fragment.TwitterDrawerFragment;
import com.josecuentas.android_bottomnavigationview.ui.fragment.TwitterFragment;

import java.util.List;

/*
* base: http://stackoverflow.com/a/31999206
* fix: http://stackoverflow.com/a/35593494
* material: https://material.io/guidelines/components/bottom-navigation.html#bottom-navigation-behavior
* */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    private BottomNavigationView mBottomNavigationView;
    private Fragment mFragmentFacebook, mFragmentTwitter;
    private FacebookDrawerFragment mDrawerFacebook;
    private TwitterDrawerFragment mDrawerTwitter;
    private int[] bottomBarColors;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentFacebook = FacebookFragment.newInstance("Facebook");
        mFragmentTwitter = TwitterFragment.newInstance("Twitter");
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerFacebook = new FacebookDrawerFragment();
        mDrawerFacebook.setDrawerLayout(mDrawerLayout);
        mDrawerTwitter = new TwitterDrawerFragment();
        mDrawerTwitter.setDrawerLayout(mDrawerLayout);

        bottomBarColors = new int[] {
            R.color.color_facebook,
            R.color.color_twitter
        };

        mDrawerLayout.openDrawer(GravityCompat.START);



        changeFragment(0);
        setupDrawer(0);
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(
                    @NonNull
                            MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action1:
                        //item.setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_facebook));
                        changeFragment(0);
                        setupDrawer(0);
                        break;
                    case R.id.action2:
                        //item.setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_twitter));
                        changeFragment(1);
                        setupDrawer(1);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

    }

    private void changeFragment(int position) {
        //clearBackStack();
        changeBottomBarColor(position);
        if (position == 0) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().remove(mFragmentTwitter).commit();
            if(getSupportFragmentManager().findFragmentByTag("faceboook") == null) {
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.frameContainer, mFragmentFacebook, "faceboook")
                        .commit();
            }



        } else if (position % 2 != 0) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().remove(mFragmentFacebook).commit();
            if(getSupportFragmentManager().findFragmentByTag("twitter") == null) {
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .replace(R.id.frameContainer, mFragmentTwitter, "twitter")
                        .commit();
            }


        }
    }

    private void changeBottomBarColor(int index) {
        if (bottomBarColors != null) {
            mBottomNavigationView.setItemBackgroundResource(bottomBarColors[index]);
            mBottomNavigationView.setBackgroundResource(bottomBarColors[index]);

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setNavigationBarColor(ContextCompat.getColor(this, bottomBarColors[index]));
            }
        }
    }

    private void setupDrawer(int position) {
        switch (position) {
            case 0:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.navigation_drawer, mDrawerFacebook)
                        .commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.navigation_drawer,mDrawerTwitter)
                        .commit();
                break;
        }

    }

    /*
    * http://stackoverflow.com/a/24820403
    * */
    @Override public void onBackPressed() {
        // if there is a fragment and the back stack of this fragment is not empty,
        // then emulate 'onBackPressed' behaviour, because in default, it is not working
        FragmentManager fm = getSupportFragmentManager();
        fm.executePendingTransactions();
        List<Fragment> fragments = fm.getFragments();
        if (fragments == null) return;
        for (Fragment frag : fragments) {
            if (frag == null) continue;
            if (frag.isVisible()) {
                FragmentManager childFm = frag.getChildFragmentManager();
                if (childFm.getBackStackEntryCount() > 0) {
                    childFm.popBackStack();
                    return;
                }
            }
        }
        super.onBackPressed();
    }

    private void clearBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        fm.executePendingTransactions();
        List<Fragment> fragments = fm.getFragments();
        if (fragments == null) return;
        for (Fragment frag : fragments) {
            if (frag == null) continue;
            //if (frag.isVisible()) {
                FragmentManager fms = frag.getFragmentManager();
                fms.popBackStack();
            //}
        }
    }
}