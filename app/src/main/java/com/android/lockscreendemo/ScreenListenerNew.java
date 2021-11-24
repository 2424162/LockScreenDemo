package com.android.lockscreendemo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import static android.view.WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER;

public class ScreenListenerNew {

    public Context mContext;
    public String tag ="lock1";
    public ScreenListenerBroadNew mScreenReceiver = new ScreenListenerBroadNew();
    public  ScreenListenerNew(Context context){
        mContext = context;
    }
    public void startScreenBroadcastReceiver2() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        mContext.registerReceiver(mScreenReceiver, filter);
    }

    public class ScreenListenerBroadNew extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            onScreenOn();
        }

        @SuppressLint("WrongConstant")
        public void onScreenOn(){
            Intent intent = new Intent();
            Log.d(tag,intent.toString());
            intent.setComponent(new ComponentName(mContext,"com.android.lockscreendemo.LockActivity"));
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            Intent action = intent.setAction("inner_action");
            mContext.startActivity(action);
        }

    }
}
