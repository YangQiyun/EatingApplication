package carteen.edu.seu.com.carteen.FindSection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import carteen.edu.seu.com.carteen.Fragment.BaseFragment;
import carteen.edu.seu.com.carteen.Model.Find;
import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/8.
 */
public class Findfragment extends BaseFragment{
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView=inflater.inflate(R.layout.fragment_find,container,false);
        initView();
        initData();
        return mRootView;
    }

    private  void initView(){
        mRecyclerView=findViewById(R.id.mrecyclerview);
        List<Find> data=new ArrayList<>(9);
        for(int i=0;i<9;++i){
            Find find=new Find();
            if(i%3==2)
            find.setSpecial(true);
            data.add(find);
        }
        FindAdapter findAdapter=new FindAdapter(this.mActivity,data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(findAdapter);
    }

    private  void initData(){

    }
}
