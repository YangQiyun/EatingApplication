package carteen.edu.seu.com.carteen.EatingSection.Main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import carteen.edu.seu.com.carteen.Model.Windows;
import carteen.edu.seu.com.carteen.R;
import carteen.edu.seu.com.carteen.Utils.Cache.ACache;


/**
 * Created by 杨棋允 on 2017/3/15.
 */
public class contentFragment extends Fragment {
    public static final String ARGS_PAGE = "scrollToPosition";
    private RecyclerView recyclerView;
    private List<Windows> storeList;
    private contentFragmentStoreAdapter adapter;

    private  View mView;


    public static contentFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        contentFragment fragment = new contentFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main_store, container, false);

        initView();
        return mView;
    }
    //初始化控件
    private void initView() {
        RecyclerView recyclerView= (RecyclerView) mView.findViewById(R.id.fragone_recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

        //设置间隙内容，但是目前是无效的
        // int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        // recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        //初始化view的数据集
        initData();
        adapter=new contentFragmentStoreAdapter(storeList,getContext(),true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


    }

    private void initData() {
        storeList =new ArrayList<>();
        ACache cache=ACache.get(contentFragment.this.getActivity());
        storeList= (ArrayList<Windows>) cache.getAsObject("StoreData");
    }

}