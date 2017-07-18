package com.seu.srtp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

/**
 * Created by Mind on 2017/7/18.
 */
public class activity_person extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tool_menu, menu);
        return true;
    }

    private void init() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.menu_person_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Return");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//箭头的点击事件
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
