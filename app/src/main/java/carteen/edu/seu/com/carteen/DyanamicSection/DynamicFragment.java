package carteen.edu.seu.com.carteen.DyanamicSection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import carteen.edu.seu.com.carteen.Fragment.BaseFragment;
import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/8.
 */
public class DynamicFragment extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView=inflater.inflate(R.layout.fragment_dynamic,container,false);
        initView();
        initData();
        return mRootView;
    }

    private  void initView(){

    }

    private  void initData(){

    }
}
