package com.seu.srtp.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by Mind on 2017/3/15.
 */
class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    public final int COUNT = 3;
    private String[] titles = new String[]{"菜单", "论坛", "寻物启事"};
    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    //传递给已经生成的fragment参数调用的
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      return  super.instantiateItem(container,position);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return  com.seu.srtp.main.TestFragment.newInstance(position);
        return com.seu.srtp.main.PageFragment.newInstance(position+2);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}