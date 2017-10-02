package carteen.edu.seu.com.carteen.EatingSection.Menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import carteen.edu.seu.com.carteen.EatingSection.Menuallfragment;
import carteen.edu.seu.com.carteen.EatingSection.Menuspecialfragment;

/**
 * Created by GIGAMOLE on 8/18/16.
 */
public class MenuMainPagerAdapter extends FragmentPagerAdapter {

    private final static int COUNT = 2;
    private final static int NEWSTYLE = 0;
    private final static int TRADITION = 1;
    private int storeNumber;

    public MenuMainPagerAdapter(final FragmentManager fm, int StoreNumber) {
        super(fm);
        this.storeNumber=StoreNumber;
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case TRADITION:
                return  Menuallfragment.newInstance(storeNumber);
            case NEWSTYLE:
            default:
                return Menuspecialfragment.newInstance(storeNumber);
        }

    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
