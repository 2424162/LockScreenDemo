package com.android.lockscreendemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        ScreenListener mScreenListener = new ScreenListener(this);
        mScreenListener.startScreenBroadcastReceiver();
    }
}