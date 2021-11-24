package com.android.lockscreendemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_layout);
        Log.d("lock1","主线程"+android.os.Process.myTid());
        ScreenListener mScreenListener = new ScreenListener(this);
        mScreenListener.startScreenBroadcastReceiver();

    }

}