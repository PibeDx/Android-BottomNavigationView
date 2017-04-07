package com.josecuentas.android_bottomnavigationview.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.josecuentas.android_bottomnavigationview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwitterFragment extends Fragment {


    private static final String ARGS_TITLE = ".title";
    private TextView mTextView;

    public TwitterFragment() {
        // Required empty public constructor
    }

    public static TwitterFragment newInstance(String title) {
        TwitterFragment twitterFragment = new TwitterFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_TITLE, title);
        twitterFragment.setArguments(bundle);
        return twitterFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_twitter, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTextView = (TextView) view.findViewById(R.id.textview);
        if (getArguments() == null) return;
        final String title = getArguments().getString(ARGS_TITLE);
        mTextView.setText(title);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                switch (title) {
                    case "Facebook":
                        changeFragment(1);
                        break;
                    case "Twitter":
                        changeFragment(0);
                        break;
                }

            }
        });
    }

    private void changeFragment(int position) {

        Fragment newFragment = null;

        if (position == 0) {
            newFragment = new TwitterDetailFragment();
        } else if (position % 2 != 0) {
            newFragment = new TwitterDetailFragment();
        } else {
            newFragment = new TwitterDetailFragment();
        }
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        //fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.beginTransaction()
                .replace(R.id.frameContainer, newFragment)
                .addToBackStack(null)
                .commit();


        /*getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameContainer, newFragment)
                .addToBackStack(null)
                .commit();*/
    }
}
