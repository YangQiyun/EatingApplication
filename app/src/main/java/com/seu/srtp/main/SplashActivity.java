package com.seu.srtp.main;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.seu.srtp.sqlite.DBManager;

/**
 * Created by swallowman on 2017/3/13.
 */

public class SplashActivity extends Activity {
    private DBManager mgr;
    private static final int FAILURE = 0; // 失败
    private static final int SUCCESS = 1; // 成功
    private static final int OFFLINE = 2; // 如果支持离线阅读，进入离线模式
    private static final int SHOW_TIME_MIN = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        mgr = new DBManager(this);
        new AsyncTask<Void, Void, Integer>() {
            protected Integer doInBackground(Void... params) {
                int result;
                long startTime = System.currentTimeMillis();
                long loadingTime = System.currentTimeMillis() - startTime;
                if (loadingTime < SHOW_TIME_MIN) {
                    try {
                        Thread.sleep(SHOW_TIME_MIN - loadingTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return 1;
            }
            @Override
            protected void onPostExecute(Integer result) {
                Intent intent = new Intent();
                if(mgr.query()){
                    intent.setClass(SplashActivity.this,Login.class );
                    startActivity(intent);
                    finish();
                }
                else{
                    intent.setClass(SplashActivity.this,MainActivity.class );
                    startActivity(intent);
                    finish();
                }
                //两个参数分别表示进入的动画,退出的动画
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }.execute(new Void[]{});
    }



}
