package com.seu.srtp.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.seu.srtp.data.User;
import com.seu.srtp.sqlite.DBManager;

/**
 * Created by swallowman on 2017/3/14.
 */

public class Login extends Activity{
    private static final int FAILURE = 0; // 失败
    private static final int SUCCESS = 1; // 成功
    private Toast mToast;
    private Button regist;
    private Button login;
    private login_edit edit_account;
    private login_edit edit_psd;
    private ImageView center_icon;

    private DBManager mgr;
    protected void onCreate(Bundle savedInstanceState) {
        mgr = new DBManager(this);
        super.onCreate(savedInstanceState);
        //无用户登录过
        setContentView(R.layout.login);
        login=(Button) findViewById(R.id.login);
        regist=(Button) findViewById(R.id.regist);
        edit_account=(login_edit) findViewById(R.id.edit_account);
        edit_psd=(login_edit)findViewById(R.id.edit_password);
        center_icon=(ImageView) findViewById(R.id.center_icon);
        login.setAlpha(0.9f);
        regist.setAlpha(0.9f);
        center_icon.setAlpha(0.8f);
        edit_psd.setAlpha(0.6f);
        edit_account.setAlpha(0.6f);

    //登录按钮实现
        login.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                int state=-1;
                state=login();
                if(state==0){
                    Intent intent = new Intent();
                    intent.setClass(Login.this,MainActivity.class );
                    startActivity(intent);
                    finish();
                }
                else if(state==1){
                    edit_account.setShakeAnimation();
                    showToast("账号为空，请输入账号");
                }
                else if(state==2){
                    edit_psd.setShakeAnimation();
                    showToast("密码为空，请输入密码");
                }
                else if(state==3){
                    showToast("账号或者密码错误，重新输入");
                }
                else {
                    showToast("尚未注册！");
                }
            }
        });

    //注册新用户
        regist.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                int act=Integer.parseInt(edit_account.getText().toString());
                String psd=edit_psd.getText().toString();
                User newUser=new User(act,psd);
                if(mgr.add(newUser)){
                    showToast("注册成功！");
                }
                else {
                    showToast("注册失败！");
                }
            }
        });
    }

    //判断登陆条件，给出相应提示
    private int login(){
        String act =edit_account.getText().toString();
        String psd=edit_psd.getText().toString();
        String db_psd=null;
        try{
            db_psd=mgr.queryPsd(Integer.parseInt(act)).password;
            if(db_psd.equals(psd)){
                return 0;//输入正确
            }
            else if(act.isEmpty()){
                return 1;//用户名为空
            }
            else if(psd.isEmpty()){
                return 2;//密码为空
            }
            else{
                return 3;//输入错误
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public void showToast(String msg) {
        if (mToast == null){
            mToast = Toast.makeText(this,msg,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }
    protected void onDestroy() {
        super.onDestroy();
        //应用的最后一个Activity关闭时应释放DB
        mgr.closeDB();
    }
}
