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

public class LockActivity extends AppCompatActivity {
    public static LockActivity instance;
    private Handler mhandler = new Handler();
    private boolean run = true;
    private TextView mView;
    private TextView dataV;
    private SilderView silderView;
    public final static String tag = "lock1";


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.d(tag,""+ev.getDeviceId());
//        return true;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(tag,event.toString());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                actionMove(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;

    }






    public void actionMove(MotionEvent event){
        if(silderView!=null) {
            int Y = (int)event.getY();
            int rawY = (int)event.getRawY();
            int[] location = new int[2];
            silderView.getLocationInWindow(location);
            int windowY = location[1];
            silderView.getLocationOnScreen(location);
            int screenY = location[1];
            Log.d(tag,Y+"-"+rawY+"-"+windowY+"-"+screenY);
            silderView.layout((int)(716-150), Y-200,716+150,Y+200);//+300+(int)(0.18*Y)
            if (event.getY()<1277){
                //this.onDestroy();
            }
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_layout);
        instance = this;
        //Log.e("lock1", "onCreate: ScreenOnTestActivity");
        final Window win = getWindow();
        //四个标志位分别是锁屏状态下显示，解锁，保持屏幕长亮，打开屏幕
        //这样当Activity启动的时候，它会解锁并亮屏显示。
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        mView = (TextView) findViewById(R.id.time);
        dataV = (TextView) findViewById(R.id.date);
        silderView = (SilderView) findViewById(R.id.idog2);
        ImageButton imageButton = (ImageButton)findViewById(R.id.look);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
            }
        });
        Log.d(tag, mView.toString());
        updateTime();

    }



    public void updateTime() {
        Log.d("lock1", "11");
        mhandler.postDelayed(task, 0);

    }


    public void demo(){
         Handler handler1 = new Handler(Looper.myLooper()){
             public void handleMessage(Message msg){
                 Log.d(tag,""+msg.what);
             }
        };
    }

    public void onclick(){



        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                mhandler.sendMessage(message);
            }
        });

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
                //Log.d(tag,"线程："+android.os.Process.myTid());
            }
        }
    };



}
