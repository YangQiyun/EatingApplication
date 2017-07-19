package com.seu.srtp.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ddz.floatingactionbutton.FloatingActionButton;
import com.ddz.floatingactionbutton.FloatingActionMenu;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";
    private ViewPager viewPager;
    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton floatingActionButton_up,floatingActionButton_down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.eating);
        toolbar.setTitle("  御膳房");
        setSupportActionBar(toolbar);


        //Fragment+ViewPager+FragmentViewPager组合的使用
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                this);
        viewPager.setAdapter(adapter);

        //TabLayout
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

      //  floatingActionMenu= (FloatingActionMenu) findViewById(R.id.floatingbutton);
      //  floatingActionButton_up= (FloatingActionButton) findViewById(R.id.action_up);
       // floatingActionButton_down= (FloatingActionButton) findViewById(R.id.action_down);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tool_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_person) {
            Intent intent=new Intent(MainActivity.this,ActivityPerson.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
