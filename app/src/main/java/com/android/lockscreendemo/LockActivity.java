package com.android.lockscreendemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

public class LockActivity extends AppCompatActivity { //锁屏activity,Window.addFlag()设置锁屏，和时间更新
    public static LockActivity instance;
    private Handler mhandler = new Handler();
    private boolean run = true;
    private TextView mView;
    private TextView dateView;
    public final static String tag = "lock1";


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_layout);
        instance = this; //传入 Layout 执行finish()
        //Log.e("lock1", "onCreate: ScreenOnTestActivity");
        final Window win = getWindow();
        //四个标志位分别是锁屏状态下显示，解锁，保持屏幕长亮，打开屏幕
        //这样当Activity启动的时候，它会解锁并亮屏显示。
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        mView = (TextView) findViewById(R.id.time);
        dateView = (TextView) findViewById(R.id.date);
        ImageButton imageButton = (ImageButton) findViewById(R.id.look);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
            }
        });
        updateTime();
    }

    public void updateTime() {
        mhandler.postDelayed(task, 0);
    }


    public final Runnable task = new Runnable() {
        @Override
        public void run() {
            if (run) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
                String str = sdf.format(new Date());
                String[] strings = str.split("  ");
                if (mView != null) {
                    ((TextView) mView).setText(strings[1]);
                    dateView.setText(strings[0]);
                }
                mhandler.postDelayed(this, 1000);
                //Log.d(tag,"线程："+android.os.Process.myTid());
            }
        }
    };

}
