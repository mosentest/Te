package org.moziqi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import moziqi.te.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VWriteFragment extends GeneralFragment {

    private static VWriteFragment fragment = null;

    public static GeneralFragment getInstance() {
        return fragment == null ? fragment = new VWriteFragment() : fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(getResources().getString(R.string.write));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vwrite, container, false);
    }


}
