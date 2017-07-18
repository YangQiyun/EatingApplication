package com.seu.srtp.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

/**
 * Created by Mind on 2017/7/18.
 */
public class ActivityPerson extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        init();
    }


    private void init() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.menu_person_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        //不去除会有默认的app标题出来
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//箭头的点击事件
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
