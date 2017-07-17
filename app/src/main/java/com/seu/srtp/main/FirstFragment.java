package com.seu.srtp.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seu.srtp.data.Store;
import com.seu.srtp.data.StoreRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨棋允 on 2017/3/15.
 */
public class FirstFragment extends Fragment {
    public static final String ARGS_PAGE = "scrollToPosition";
    private RecyclerView recyclerView;
    private List<Store> storeList;
    private StoreRecycleViewAdapter adapter;
    private  View mView;


    public static FirstFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        FirstFragment fragment = new FirstFragment();
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
        mView = inflater.inflate(R.layout.fragment_one, container, false);

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
        adapter=new StoreRecycleViewAdapter(storeList,getContext(),true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


    }

    private void initData() {
        storeList =new ArrayList<>();
        storeList.add(new Store("农家小碗菜","点餐快，菜品多。禾畈人家","北京",R.mipmap.xiaowancai));
        storeList.add(new Store("麦多馅饼","经过300度高温、短时间的加工工艺，表层香酥爽口、里层细嫩有筋、口感独特，完整的保留了食品原有的营养元素，它快捷、方便、鲜香宜人、老少皆宜、健康时尚。","北京",R.mipmap.maiduomianbin));
        storeList.add(new Store("担担面","著名的四川小吃，源起挑夫们在码头挑着担担卖面，所以名为担担面。用面粉擀制成面条，煮熟，舀上炒制的猪肉末而成。成菜面条细薄，卤汁酥香，咸鲜微辣，香气扑鼻，十分入味。","北京",R.mipmap.dandanmian));
        storeList.add(new Store("原生豆浆","秉承原汁原味的制作理念，其营养物质、保健功效成分的含量高，对人体有很好的营养保健作用。","地点北京",R.mipmap.yuanmodoujiang));
        storeList.add(new Store("石锅拌饭","发源地为韩国全州，后来演变为韩国的代表性食物。韩国全州的石锅拌饭名闻遐迩，拌饭里蕴涵着\"五行五脏五色\"的原理。","北京",R.mipmap.shiguobanfan));
    }

}