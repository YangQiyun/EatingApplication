package carteen.edu.seu.com.carteen.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import carteen.edu.seu.com.carteen.R;


public class LoginActivity extends AppCompatActivity{

    private TextView username;
    private TextView password;
    private TextView forgotpassword;
    private TextView signup;
    private TextView verifycode;
    private Button btn_login;
    //    private Button btn_verifycode;
    private TextView btn_verifycode;
    private Handler mHandler;
    private ProgressDialog loginProgressDlg;
    private CountDownTimer timeCount;
    private String phone;
    private int pflag=0;//是否在guide里点击了注册按钮的flag
    private int eventFlag=1;//1为登录 2为忘记密码 3为验证注册 4为验证注册通过 5为提交注册通过 6为修改密码验证
    TranslateAnimation animationHide=new TranslateAnimation(Animation.RELATIVE_TO_SELF,
            0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
            -1.0f);
    TranslateAnimation animationShow=new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
            -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        if(loginProgressDlg!=null)
            loginProgressDlg.dismiss();
        else
            finish();
    }

    View view1;
    View view2;
    View view111;
    String trueCODE;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginProgressDlg=new ProgressDialog(LoginActivity.this);
        loginProgressDlg.setTitle("shacus");
        loginProgressDlg.setMessage("处理中");
        loginProgressDlg.setIndeterminate(true);
        loginProgressDlg.setCancelable(false);
        Intent intent=getIntent();

        TextView direct_enter= (TextView) findViewById(R.id.suibiankan);
        username=(TextView)findViewById(R.id.login_username);
        password=(TextView)findViewById(R.id.login_password);
        verifycode=(TextView)findViewById(R.id.register_verifycode);
        btn_login=(Button)findViewById(R.id.btn_login);

        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view111 = findViewById(R.id.view111);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("0000") && password.getText().toString().equals("0000")){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        direct_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_verifycode=(TextView)findViewById(R.id.btn_verify_code);
        btn_verifycode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        ImageView loginbtnimg = (ImageView) findViewById(R.id.loginimgview);
//        loginbtnimg.bringToFront();
        forgotpassword=(TextView)findViewById(R.id.btn_forgot);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eventFlag==2){
                    signup.setVisibility(View.VISIBLE);
                    eventFlag=1;
                    forgotpassword.setText("忘记密码");
                    animationHide.setDuration(500);
                    verifycode.startAnimation(animationHide);
                    verifycode.setVisibility(View.GONE);
                    animationShow.setDuration(500);
                    password.startAnimation(animationShow);
                    username.setText("");
                    username.setVisibility(View.VISIBLE);
                    username.setHint("手机号");
                    view111.setVisibility(View.VISIBLE);
                    btn_verifycode.setVisibility(View.GONE);
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.GONE);
                    password.setVisibility(View.VISIBLE);
                    btn_login.setText("  登   录");
                    return;
                }
                if(eventFlag!=2){
//                signup.setVisibility(View.INVISIBLE);
                    eventFlag=2;
                    animationHide.setDuration(500);
                    password.startAnimation(animationHide);
                    password.setVisibility(View.GONE);
                    animationShow.setDuration(500);
                    verifycode.startAnimation(animationShow);
                    username.setText("");
                    username.setVisibility(View.VISIBLE);
                    username.setHint("手机号");
                    view111.setVisibility(View.VISIBLE);
                    verifycode.setVisibility(View.VISIBLE);
                    btn_verifycode.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.VISIBLE);
                    forgotpassword.setText("去登陆");
                    btn_login.setText("验证账号");
                }
                return;
            }
        });
        signup=(TextView)findViewById(R.id.btn_newuser);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eventFlag!=3){
                    eventFlag=3;
                    animationHide.setDuration(500);
                    password.startAnimation(animationHide);
                    password.setVisibility(View.GONE);
                    animationShow.setDuration(500);
                    verifycode.startAnimation(animationShow);
                    verifycode.setVisibility(View.VISIBLE);
                    username.setText("");
                    username.setVisibility(View.VISIBLE);
                    username.setHint("手机号");
                    view111.setVisibility(View.VISIBLE);
                    signup.setText("老用户登录");
//                forgotpassword.setVisibility(View.INVISIBLE);
                    btn_verifycode.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.VISIBLE);
                    btn_login.setText("  注   册");

                    //下一步
                    return;
                }
                if(eventFlag==3){
                    eventFlag=1;
                    animationHide.setDuration(500);
                    verifycode.startAnimation(animationHide);
                    verifycode.setVisibility(View.GONE);
                    animationShow.setDuration(500);
                    password.startAnimation(animationShow);
                    password.setVisibility(View.VISIBLE);
                    signup.setText("新用户注册");
                    btn_verifycode.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    view1.setVisibility(View.VISIBLE);
                    username.setText("");
                    username.setVisibility(View.VISIBLE);
                    username.setHint("手机号");
                    view111.setVisibility(View.VISIBLE);

//                    forgotpassword.setVisibility(View.VISIBLE);
                    btn_login.setText("  登   录");
                    return;
                }
            }
        });
        //if (eventFlag==1)
        //  signup.performClick();
        timeCount=new CountDownTimer(120000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                //btn_verifycode.setBackgroundColor(getResources().getColor(R.drawable.shape_verifycode));
                btn_verifycode.setClickable(false);
                btn_verifycode.setText("重发 "+millisUntilFinished/1000+"s");
            }

            @Override
            public void onFinish() {
                btn_verifycode.setText("获取");
                //btn_verifycode.setBackgroundColor(getResources().getColor(R.color.gold));
//                btn_verifycode.setBackground(getResources().getDrawable(R.drawable.shape_verifycode));
                btn_verifycode.setBackground(getResources().getDrawable(R.drawable.loginbtn));
                btn_verifycode.setClickable(true);
            }
        };
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg){
            }
        };

        if (pflag==1){
            eventFlag=666;
            signup.performClick();
        }
    }

    private boolean judgeNameAndPwd(String usrnm, String pwd) {
        for (int i = usrnm.length();--i>=0;){
            if (!Character.isDigit(usrnm.charAt(i))){
                return false;
            }
        }
        return !(usrnm.length() != 11 || pwd.length() < 6);
    }


    @Override
    protected void onDestroy() {
        if(loginProgressDlg!=null)
            loginProgressDlg.dismiss();
        super.onDestroy();
    }
}
