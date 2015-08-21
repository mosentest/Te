package org.moziqi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import moziqi.te.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankHomeFragment extends Fragment {


    public BlankHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BlankHomeFragment", "onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("BlankHomeFragment", "onResume");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e("BlankHomeFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("BlankHomeFragment", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("BlankHomeFragment", "onDestroy");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank_home, container, false);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getActivity().getSupportFragmentManager(), FragmentPagerItems.with(getActivity())
                .add(R.string.more, VMoreFragment.class)
                .add(R.string.msg, VMsgFragment.class)
                .create()
        );
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab = (SmartTabLayout) rootView.findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
        return rootView;
    }


}
