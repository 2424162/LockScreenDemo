package com.android.lockscreendemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;


public class ScreenListener {


    private Context context;
    private ScreenListenerReceiver mScreenReceiver;

    public ScreenListener(Context context) {
        this.context = context;
        mScreenReceiver = new ScreenListenerReceiver();

    }


    public class ScreenListenerReceiver extends BroadcastReceiver {
        private String action = null;

        @Override
        public void onReceive(Context context, Intent intent) {
            action = intent.getAction();
            Log.d("lock1", action.toString());
            if (Intent.ACTION_SCREEN_ON.equals(action)) {        //开屏操作
                onScreenOn();
            }

        }

    }

    private void onScreenOn() {
        Intent intent = new Intent(context, LockActivity.class);
        Log.d("lock1",intent.toString());
        context.startActivity(intent);

    }


    public void startScreenBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        context.registerReceiver(mScreenReceiver, filter);


    }

}