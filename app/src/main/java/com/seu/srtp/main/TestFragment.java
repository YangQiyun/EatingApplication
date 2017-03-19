package com.seu.srtp.main;


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

/**
 * Created by 杨棋允 on 2017/3/15.
 */
public class TestFragment extends Fragment {
    public static final String ARGS_PAGE = "scrollToPosition";
    private RecyclerView recyclerView;
    private List<News> newsList;
    private RecyclerViewAdapter adapter;
    private  View mView;


    public static TestFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        TestFragment fragment = new TestFragment();
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
        mView = inflater.inflate(R.layout.content_main, container, false);

        initView();
        return mView;
    }
    //初始化控件
    private void initView() {
        RecyclerView recyclerView= (RecyclerView) mView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.scrollToPosition(this.getArguments().getInt(ARGS_PAGE));
        recyclerView.setLayoutManager(layoutManager);

        //设置间隙内容，但是目前是无效的
        // int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        // recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        //初始化view的数据集
        initPersonData();
        adapter=new RecyclerViewAdapter(newsList,getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


    }

    private void initPersonData() {
        newsList =new ArrayList<>();
        //添加新闻
        newsList.add(new News(getString(R.string.news_one_title),getString(R.string.news_one_desc),R.mipmap.news_one));
        newsList.add(new News(getString(R.string.news_two_title),getString(R.string.news_two_desc),R.mipmap.news_two));
        newsList.add(new News(getString(R.string.news_three_title),getString(R.string.news_three_desc),R.mipmap.news_three));
        newsList.add(new News(getString(R.string.news_four_title),getString(R.string.news_four_desc),R.mipmap.news_four));

    }

}