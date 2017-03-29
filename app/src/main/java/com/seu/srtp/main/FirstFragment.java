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
        storeList.add(new Store("原生豆浆","豆浆（Soybean Milk)，中国汉族传统饮品，最早的豆浆为西汉淮南王刘安制作。将大豆用水泡涨后磨碎、过滤、煮沸而成","地点北京",R.mipmap.store_one));
        storeList.add(new Store("神奇的牛肉面","豆浆（Soybean Milk)，中国汉族传统饮品，最早的豆浆为西汉淮南王刘安制作。将大豆用水泡涨后磨碎、过滤、煮沸而成","北京",R.mipmap.store_two));
        storeList.add(new Store("好吃的萝卜大","豆浆（Soybean Milk)，中国汉族传统饮品，最早的豆浆为西汉淮南王刘安制作。将大豆用水泡涨后磨碎、过滤、煮沸而成","北京",R.mipmap.store_three));
        storeList.add(new Store("填充用的面材","豆浆（Soybean Milk)，中国汉族传统饮品，最早的豆浆为西汉淮南王刘安制作。将大豆用水泡涨后磨碎、过滤、煮沸而成","北京",R.mipmap.store_four));
    }

}