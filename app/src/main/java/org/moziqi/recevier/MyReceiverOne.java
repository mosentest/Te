package org.moziqi.recevier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class MyReceiverOne extends BroadcastReceiver {

    public final static String EXTRA_LIFEFORM_NAME = "EXTRA_LIFEFORM_NAME";
    public final static String EXTRA_LATITUDE = "EXTRA_LATITUDE";
    public final static String EXTRA_LONGITUDE = "EXTRA_LONGITUDE";
    public static final String ACTION_BURN = "com.mzq.action.BURN_IT_WITH_FIRE";
    public static final String NEW_LIFEFORM = "com.mzq.action.NEW_LIFEFORM";

    public MyReceiverOne() {
        Log.e("MyReceiverOne","MyReceiverOne");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //更新内容，启动service，更新activity UI或者使用Notification Manager
//        android.R.style.Theme
        Toast.makeText(context, "receiver", Toast.LENGTH_SHORT).show();
        Log.e("MyReceiverOne","onReceive");
        Uri data = intent.getData();
        String type = intent.getStringExtra(EXTRA_LIFEFORM_NAME);
        double lat = intent.getDoubleExtra(EXTRA_LATITUDE, 0);
        double lng = intent.getDoubleExtra(EXTRA_LONGITUDE, 0);
        Location loc = new Location("gps");
        loc.setLatitude(lat);
        loc.setLongitude(lng);
        if (type.equals("facehugger")) {
            Intent startIntent = new Intent(ACTION_BURN, data);
            startIntent.putExtra(EXTRA_LATITUDE, lat);
            startIntent.putExtra(EXTRA_LONGITUDE, lng);
            context.startService(startIntent);
        }
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
