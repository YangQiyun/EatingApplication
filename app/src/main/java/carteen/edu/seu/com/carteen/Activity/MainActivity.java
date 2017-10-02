package carteen.edu.seu.com.carteen.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import carteen.edu.seu.com.carteen.EatingSection.Main.MainFragment;
import carteen.edu.seu.com.carteen.Fragment.BaseFragment;
import carteen.edu.seu.com.carteen.Fragment.TestFragment;
import carteen.edu.seu.com.carteen.R;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FragmentManager fragmentManager=this.getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;
    private ImageButton[] BottomButton;
    private BaseFragment[] fragmentGroup=new BaseFragment[3];
    private TextView tool_title;
    private static final String TAG = "MainActivity";
    private static int select=0;//为了进入app后直接进入食堂
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }


    @Override
    protected void onResume() {
        super.onResume();
        if(0==select){
            select=1;
            BottomButton[0].callOnClick();}
        int cuttent=0;
        for(BaseFragment baseFragment:fragmentGroup){
            if(baseFragment.isHidden())
                cuttent++;
        }
        Log.d(TAG, "onResume: "+cuttent);
        if(cuttent<=1){
            hidefragment();
            BottomButton[2].callOnClick();
        }
    }

    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        if(fragmentGroup[0]==null&&fragment instanceof MainFragment)
            fragmentGroup[0]=(BaseFragment) fragment;
        if(fragmentGroup[1]==null&&fragment instanceof TestFragment)
            fragmentGroup[1]=(BaseFragment) fragment;
        if(fragmentGroup[2]==null&&fragment instanceof TestFragment)
            fragmentGroup[2]=(BaseFragment) fragment;
        super.onAttachFragment(fragment);
    }


    private void initView(){
       tool_title= (TextView) findViewById(R.id.m_toolbar_title);
        BottomButton=new ImageButton[3];
       BottomButton[0]= (ImageButton) findViewById(R.id.button_eating);
       BottomButton[1]= (ImageButton) findViewById(R.id.button_find);
       BottomButton[2]= (ImageButton) findViewById(R.id.button_dynamic);
       for(ImageButton i:BottomButton)
           i.setOnClickListener(this);
    }
    private void initData(){
        hidefragment();
    }


    @Override
    public void onClick(View v) {
        hidefragment();
        fragmentTransaction=fragmentManager.beginTransaction();
        for(ImageButton i:BottomButton)
            i.setSelected(false);
        switch (v.getId()){
            case R.id.button_eating:
                fragmentTransaction.show(fragmentManager.findFragmentByTag("fg_eating"));
                fragmentTransaction.commit();
                tool_title.setText("食堂");
                BottomButton[0].setSelected(true);
                break;
            case R.id.button_find:
                fragmentTransaction.show(fragmentManager.findFragmentByTag("fg_find"));
                fragmentTransaction.commit();
                tool_title.setText("发现");
                BottomButton[1].setSelected(true);
                break;
            case R.id.button_dynamic:
                fragmentTransaction.show(fragmentManager.findFragmentByTag("fg_dynamic"));
                fragmentTransaction.commit();
                tool_title.setText("动态");
                BottomButton[2].setSelected(true);
                break;
            default:
                Log.d(TAG, "onClick: other click");
                break;
        }
    }
    /*
    * 初始化创建所有的fragment，还有当隐藏frament使用
    * */
    private void hidefragment(){
        fragmentTransaction=fragmentManager.beginTransaction();
        String a[]={"fg_eating","fg_find","fg_dynamic"};
        for(int i=0;i<3;++i){
            if(fragmentGroup[i]==null){
                if(0==i)
                    fragmentGroup[0]=new MainFragment();
                else
                fragmentGroup[i]= TestFragment.newInstance(i);
                fragmentTransaction.add(R.id.fragmentlayout_content,fragmentGroup[i],a[i]);}
            else
                fragmentTransaction.hide(fragmentManager.findFragmentByTag(a[i]));
        } fragmentTransaction.commit();
    }


}
