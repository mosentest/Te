package org.moziqi.fragment;


import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.moziqi.recevier.MyReceiverOne;

import moziqi.te.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VMoreFragment extends GeneralFragment {

    private static VMoreFragment fragment = null;

    private MyReceiverOne myReceiverOne;
    private IntentFilter filter;

    public static GeneralFragment getInstance() {
        return fragment == null ? fragment = new VMoreFragment() : fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(getResources().getString(R.string.more));
        myReceiverOne = new MyReceiverOne();
        filter = new IntentFilter(MyReceiverOne.NEW_LIFEFORM);
        Log.e(VMoreFragment.class.getName(), "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vmore, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(VMoreFragment.class.getName(), "onResume");
        getActivity().registerReceiver(myReceiverOne, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(VMoreFragment.class.getName(), "onPause");
        getActivity().unregisterReceiver(myReceiverOne);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(VMoreFragment.class.getName(), "onDestroy");
    }
}
