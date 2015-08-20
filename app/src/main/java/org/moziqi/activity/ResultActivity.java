package org.moziqi.activity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.ServiceCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.moziqi.util.Constant;

import moziqi.te.R;

public class ResultActivity extends ActionBarActivity {

    private static final String ACTION_STARTED = "com.moziqi.android.supportv4.STARTED";
    private static final String ACTION_UPDATE = "com.moziqi.android.supportv4.UPDATE";
    private static final String ACTION_STOPPED = "com.moziqi.android.supportv4.STOPPED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {
        private Button mButton;
        private TextView mTextView;
        private View rootView;
        private LocalBroadcastManager mLocalBroadcastManager;
        private BroadcastReceiver mBroadcastReceiver;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_result, container, false);
            initView();
            return rootView;
        }

        public View findViewById(int id) {
            return rootView.findViewById(id);
        }

        public void initView() {
            mButton = (Button) this.findViewById(R.id.btn_f_result);
            mTextView = (TextView) this.findViewById(R.id.tv_f_result);
            initData();
        }

        public void initData() {
            mButton.setOnClickListener(this);
            mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ACTION_STARTED);
            intentFilter.addAction(ACTION_UPDATE);
            intentFilter.addAction(ACTION_STOPPED);
            mBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals(ACTION_STARTED)) {
                        mTextView.setText("started");
                    } else if (intent.getAction().equals(ACTION_UPDATE)) {
                        mTextView.setText("udpate" + intent.getIntExtra("value", 0));
                    } else if (intent.getAction().equals(ACTION_STOPPED)) {
                        mTextView.setText("stoped");
                    }
                }
            };
            mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
        }

        @Override
        public void onClick(View v) {
            Intent mIntent = null;
            switch (v.getId()) {
                case R.id.btn_f_result:
                    mIntent = new Intent();
                    mIntent.putExtra("result", "2");
                    getActivity().setResult(Constant.Home2ResultActivity.resultCode, mIntent);
                    getActivity().finish();
                    break;
                default:
                    break;
            }
        }
    }

    public static class LocalService extends Service {
        private LocalBroadcastManager mLocalBroadcastManager;
        private int mCurUpdate;
        private static final int MSG_UPDATE = 1;
        private Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_UPDATE: {
                        mCurUpdate++;
                        Intent intent = new Intent(ACTION_UPDATE);
                        intent.putExtra("value", mCurUpdate);
                        mLocalBroadcastManager.sendBroadcast(intent);
                        Message nmsg = mHandler.obtainMessage(MSG_UPDATE);
                        mHandler.sendMessageDelayed(nmsg, 1000);
                    }
                    break;
                    default:
                        super.handleMessage(msg);
                }
            }
        };

        @Override
        public void onCreate() {
            super.onCreate();
            mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            mLocalBroadcastManager.sendBroadcast(new Intent(ACTION_STARTED));
            mHandler.removeMessages(MSG_UPDATE);
            Message msg = mHandler.obtainMessage(MSG_UPDATE);
            mHandler.sendMessageDelayed(msg, 1000);
            return ServiceCompat.START_STICKY;
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }


        @Override
        public void onDestroy() {
            super.onDestroy();
            mLocalBroadcastManager.sendBroadcast(new Intent(ACTION_STOPPED));
            mHandler.removeMessages(MSG_UPDATE);
        }

    }
}
