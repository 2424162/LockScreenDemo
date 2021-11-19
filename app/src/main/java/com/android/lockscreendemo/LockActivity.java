package com.android.lockscreendemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LockActivity extends AppCompatActivity {
    private Handler mhandler = new Handler();
    private boolean run = true;
    private TextView mView;
    private TextView dataV;
    private SilderView silderView;
    public final static String tag = "lock1";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        Log.e("lock1", "onCreate: ScreenOnTestActivity");
        final Window win = getWindow();
        //四个标志位分别是锁屏状态下显示，解锁，保持屏幕长亮，打开屏幕
        //这样当Activity启动的时候，它会解锁并亮屏显示。
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        mView = (TextView) findViewById(R.id.time);
        dataV = (TextView) findViewById(R.id.date);
        silderView = (SilderView) findViewById(R.id.dog);



        Log.d(tag, mView.toString());
        updateTime();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag,"关闭activity");
        mhandler.removeCallbacks(task);
    }

    public void updateTime() {
        Log.d("lock1", "11");
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
                    ((TextView)mView).setText(strings[1]);
                    dataV.setText(strings[0]);

                }
                mhandler.postDelayed(this, 1000);
            }
        }
    };

}
