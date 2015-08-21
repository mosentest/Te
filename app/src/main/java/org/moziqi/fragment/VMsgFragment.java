package org.moziqi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import moziqi.te.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VMsgFragment extends GeneralFragment {

    private static VMsgFragment fragment = null;

    public static GeneralFragment getInstance() {
        return fragment == null ? fragment = new VMsgFragment() : fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(getResources().getString(R.string.msg));
        Log.e(VMsgFragment.class.getName(), "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vmsg, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(VMsgFragment.class.getName(), "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(VMsgFragment.class.getName(), "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(VMsgFragment.class.getName(), "onDestroy");
    }

}
