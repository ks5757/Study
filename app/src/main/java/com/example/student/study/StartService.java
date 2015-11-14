package com.example.student.study;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class StartService extends Service {


    private Timer timer = null;
    private int countTime;
    private int stopTime;
    public static final String EXTRA_STOP_TIME = "EXTRA_STOP_TIME";


    private Handler handler = new Handler(){

        public void handleMessage(Message msg) {
            Toast.makeText(StartService.this,
                    (String) msg.obj,
                    Toast.LENGTH_SHORT).show();
        }
     };

    private TimerTask task = new TimerTask(){
        @Override
        public void run(){
            //
            countTime += 10;

            if(countTime/60 == stopTime){
                stopSelf();
            }else{
                //
                handler.sendMessage(
                        Message.obtain(handler,
                                0,
                                countTime /60+
                        "分" +
                        countTime % 60 +"秒経過しました!"));
            }
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){
        super.onCreate();

        //トースト表示
        Toast.makeText(this, "サービスを起動します", Toast.LENGTH_SHORT).show();

        //タイマーと経過時間初期化
        timer = new Timer();
        countTime = 0;
    }

    @Override
    public void onStart(Intent intent, int startId){
        super.onStart(intent, startId);

        //トースト表示
        Toast.makeText(this,"サービスを開始します", Toast.LENGTH_SHORT).show();
        //タイマー設定(10秒ごとにrunメソッドを呼び出し)
        timer.schedule(task, 10000, 10000);
        //終了時間取得
        Bundle bundle = intent.getExtras();


        stopTime = Integer.parseInt(bundle.getString(EXTRA_STOP_TIME));
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        //トースト表示
        Toast.makeText(this, "サービスを終了します", Toast.LENGTH_SHORT).show();
        //タイマー設定解除
        timer.cancel();
        timer.purge();
    }



}
