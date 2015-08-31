package org.moziqi.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.moziqi.recevier.MyReceiverOne;
import org.moziqi.service.TimeService;

import moziqi.te.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VMoreFragment extends GeneralFragment implements View.OnClickListener {


    public static final String TIME_CHANGED_ACTION = "com.mo.TIME_CHANGED_ACTION";
    private static VMoreFragment fragment = null;

    private MyReceiverOne myReceiverOne;
    private IntentFilter filter;

    private TextView mTextView;
    private Button mButton;

    private Intent timeService;
    private UITimeReceiver uiTimeReceiver;
    private IntentFilter intentFilter;

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
        return inflater.inflate(R.layout.fragment_vmore, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTextView = (TextView) findById(R.id.time);
        mButton = (Button) findById(R.id.btn);
        mButton.setOnClickListener(this);

        registerBroadcastReceiver();
        startTimeService();
    }

    private void startTimeService() {
        timeService = new Intent(getActivity(), TimeService.class);
        getActivity().startService(timeService);
    }

    private void registerBroadcastReceiver() {
        uiTimeReceiver = new UITimeReceiver();
        intentFilter = new IntentFilter(TIME_CHANGED_ACTION);
        getActivity().registerReceiver(uiTimeReceiver, intentFilter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
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
        getActivity().unregisterReceiver(uiTimeReceiver);
        getActivity().stopService(timeService);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Toast.makeText(getActivity(), "---", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    class UITimeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (VMoreFragment.TIME_CHANGED_ACTION.equals(action)) {
                Bundle bundle = intent.getExtras();
                String strTime = bundle.getString("time");
                mTextView.setText(strTime);
            }
        }
    }
}
