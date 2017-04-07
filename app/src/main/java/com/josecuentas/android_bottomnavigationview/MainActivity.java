package com.josecuentas.android_bottomnavigationview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.josecuentas.android_bottomnavigationview.ui.fragment.FacebookFragment;
import com.josecuentas.android_bottomnavigationview.ui.fragment.TwitterFragment;

import java.util.List;

/*
* base: http://stackoverflow.com/a/31999206
* */

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private Fragment mFragmentFacebook, mFragmentTwitter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentFacebook = FacebookFragment.newInstance("Facebook");
        mFragmentTwitter = TwitterFragment.newInstance("Twitter");
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        changeFragment(0);
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(
                    @NonNull
                            MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action1:
                        item.setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_facebook));
                        changeFragment(0);
                        break;
                    case R.id.action2:
                        item.setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_twitter));
                        changeFragment(1);
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
        if (position == 0) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            //if (mFragmentTwitter.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(mFragmentTwitter).commit();
            //}
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameContainer, mFragmentFacebook)
                    .commit();

        } else if (position % 2 != 0) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            //if (mFragmentFacebook.isVisible()) {
                getSupportFragmentManager().beginTransaction().remove(mFragmentFacebook).commit();
            //}
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameContainer, mFragmentTwitter)
                    .commit();
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