package com.josecuentas.android_bottomnavigationview.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.josecuentas.android_bottomnavigationview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacebookItemFragment extends Fragment {


    public FacebookItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_facebook_item, container, false);
    }

}
