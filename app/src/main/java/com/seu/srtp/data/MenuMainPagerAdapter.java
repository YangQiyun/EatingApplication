package com.seu.srtp.data;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by GIGAMOLE on 8/18/16.
 */
public class MenuMainPagerAdapter extends FragmentPagerAdapter {

    private final static int COUNT = 2;
    private final static int NEWSTYLE = 0;
    private final static int TRADITION = 1;
    private int storeNumber;

    public MenuMainPagerAdapter(final FragmentManager fm,int StoreNumber) {
        super(fm);
        this.storeNumber=StoreNumber;
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case TRADITION:
                return new fragment_MenuVerticalPager();
            case NEWSTYLE:
            default:
                return fragment_MenuHorizontalPager.newInstance(storeNumber);
        }

    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
