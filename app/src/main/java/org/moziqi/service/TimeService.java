package org.moziqi.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import org.moziqi.fragment.VMoreFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * http://5200415.blog.51cto.com/3851969/1003413
 */
public class TimeService extends Service {
    private String TAG = "TimeService";
    private Timer timer = null;
    private SimpleDateFormat simpleDateFormat = null;
    private Intent timeIntent = null;
    private Bundle bundle = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"TimeService->onCreate");
        init();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setTimeChangedBroadcast();
            }
        }, 1000, 1000);
    }

    /**
     * 相关变量初始化
     */
    private void init() {
        timer = new Timer();
        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 " + " hh:mm:ss");
        timeIntent = new Intent();
        bundle = new Bundle();
    }

    /**
     * 发送广播，通知UI层时间已改变
     */
    private void setTimeChangedBroadcast() {
        bundle.putString("time", getTime());
        timeIntent.putExtras(bundle);
        timeIntent.setAction(VMoreFragment.TIME_CHANGED_ACTION);
        sendBroadcast(timeIntent);
    }

    /**
     * 获取最新系统时间
     *
     * @return
     */
    private String getTime() {
        return simpleDateFormat.format(new Date());
    }

    public TimeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"TimeService->onBind");
        return null;
    }

    @Override
    public ComponentName startService(Intent service) {
        Log.e(TAG, "TimeService->startService");
        return super.startService(service);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "TimeService->onDestroy");
    }
}
