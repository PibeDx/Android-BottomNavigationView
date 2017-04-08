package com.josecuentas.android_bottomnavigationview.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.josecuentas.android_bottomnavigationview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacebookFragment extends Fragment {


  private TextView mTextView;

  public FacebookFragment() {
    // Required empty public constructor
  }


  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_facebook, container, false);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    mTextView = (TextView) view.findViewById(R.id.textview);

  }
}
