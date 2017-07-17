package com.seu.srtp.main;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.seu.srtp.barrage.BarrageView;
import com.seu.srtp.data.NewTrend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by swallowman on 2017/3/22.
 */

public class TrendFragment extends Fragment {
    public static final String ARGS_PAGE = "scrollToPosition";
    private RecyclerView recyclerView;
    //评论队列
    private List<NewTrend>trends;
    private TrendRecycleViewAdapter trendAdapter;
    private View mView;
    //发布弹幕图片按钮
    private ImageButton launchInstantTrend;
    //弹幕编辑框
    private EditText instantTrendEdit;
    //发帖子
    private Button createTrend;
    private Random random = new Random();
    //弹幕队列
    private String[] trend_texts;
    private ArrayList<String>trend_texts_container;
    //判断主线程是否暂停来修复弹幕满屏的bug
    private boolean isOnPause = false;
    public static TrendFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        TrendFragment fragment = new TrendFragment();
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
        mView = inflater.inflate(R.layout.forum, container, false);
        initView();
        trend_texts_container=new ArrayList<>();
        createTrend=(Button)mView.findViewById(R.id.creat_trend);
        //获取布局中的id，用来安排弹幕布局
        final RelativeLayout relativeLayout = (RelativeLayout) mView.findViewById(R.id.trend_all);
        //设置弹幕宽高全屏（其实不是）
        final ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        final Handler handler = new Handler();
        Runnable createBarrageView = new Runnable() {
            @Override
            public void run() {
                if (!isOnPause) {
                    //新建一条弹幕，并设置文字
                    trend_texts = trend_texts_container.toArray(new String[trend_texts_container.size()]);
                    //弹幕库不空就发射弹幕
                    if(trend_texts.length!=0){
                    final BarrageView barrageView = new BarrageView(getContext());
                    //随机从弹幕库中选取发射内容
                    barrageView.setText(trend_texts[random.nextInt(trend_texts.length)]);
                    relativeLayout.addView(barrageView,lp);
                    }
                }
                //发送下一条消息
                handler.postDelayed(this, 800);
            }
        };
        handler.post(createBarrageView);

        //发布新帖
        createTrend.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //TODO:
            }
        });

        //获取输入框文本，存入消息队列中
        instantTrendEdit=(EditText)mView.findViewById(R.id.trendEdit);
        launchInstantTrend=(ImageButton)mView.findViewById(R.id.launchNewTrend);
        launchInstantTrend.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v){
                String instantText=instantTrendEdit.getText().toString();
                if(!instantText.equals(null)&&!instantText.isEmpty()){
                    trend_texts_container.add(instantText);
                    instantTrendEdit.setText(null);
                }
            }
        });
        return mView;
    }

    //初始化控件
    private void initView() {
        RecyclerView recyclerView= (RecyclerView) mView.findViewById(R.id.trendView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.scrollToPosition(this.getArguments().getInt(ARGS_PAGE));
        recyclerView.setLayoutManager(layoutManager);
        //初始化view的数据集
        initTrends();
        trendAdapter=new TrendRecycleViewAdapter(trends,getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(trendAdapter);
    }
    private void initTrends(){
        trends=new ArrayList<>();
        trends.add(new NewTrend(1,"yang","hello yangxingcai"));
        trends.add(new NewTrend(2,"zhang","this is zhang"));
        trends.add(new NewTrend(3,"yang","hello yangqiyun"));
        trends.add(new NewTrend(4,"wen","this is wen"));
        trends.add(new NewTrend(1,"yang","hello yangxingcai"));
        trends.add(new NewTrend(2,"zhang","this is zhang"));
        trends.add(new NewTrend(3,"yang","hello yangqiyun"));
        trends.add(new NewTrend(4,"wen","this is wen"));
    }
    @Override
    public void onPause() {
        super.onPause();
        isOnPause = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        isOnPause = false;
    }

}
