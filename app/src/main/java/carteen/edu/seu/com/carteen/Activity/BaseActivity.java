package carteen.edu.seu.com.carteen.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import carteen.edu.seu.com.carteen.Network.okHttpUtil;
import carteen.edu.seu.com.carteen.Utils.SystemBarTintManager;


/**
 * Created by Mind on 2017/9/2.
 */
public class BaseActivity extends AppCompatActivity {
    private SystemBarTintManager tintManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemBar();
       // getSupportActionBar().setLogo(R.drawable.de_bar_logo);//actionbar 添加logo

    }

    //4.4以上沉浸式状态栏设置
    private void initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // Holo light action bar color is #DDDDDD
            int actionBarColor = Color.parseColor("#0195ff");
            tintManager.setStatusBarTintColor(actionBarColor);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出的时候清空网络请求
        okHttpUtil.cancel(this);
    }

}
