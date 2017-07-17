package com.seu.srtp.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.seu.srtp.data.MenuMainPagerAdapter;

/**
 * Created by Mind on 2017/3/23.
 */
public class activity_menu extends AppCompatActivity {
    private  int storeNumber;
    ImageView imageView;
    private ViewPager viewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent=getIntent();
        //取不到值默认返回1,收到是商店几，然后生成对应的菜单
        storeNumber=intent.getIntExtra("storeNumber",1);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tool_menu, menu);
        return true;
    }


    private void init() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Return");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//箭头的点击事件
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        viewPager= (ViewPager) findViewById(R.id.menu_viewpager);
        viewPager.setAdapter(new MenuMainPagerAdapter(getSupportFragmentManager(),storeNumber));

        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
        navigationTabStrip.setTitles("特色菜", "总菜单");
        navigationTabStrip.setViewPager(viewPager);
    }


}
