package carteen.edu.seu.com.carteen.EatingSection.Main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

/**
 * Created by Mind on 2017/3/15.
 */
public class mainFragmentAdapter extends android.support.v4.app.FragmentPagerAdapter {
    public final int COUNT = 3;
    private String[] titles = new String[]{"桃园", "梅园", "橘园"};
    private Context context;

    public mainFragmentAdapter(FragmentManager fm, Context context) {
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
        if(position==0){
            return contentFragment.newInstance(0);}
        if(position==1){
            return contentFragment.newInstance(1);}
        return contentFragment.newInstance(2);
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