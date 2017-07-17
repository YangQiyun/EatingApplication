package com.seu.srtp.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.seu.srtp.main.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mind on 2017/3/27.
 */
public class fragment_MenuVerticalPager extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_vertival,container,false);
        ListView LV= (ListView) view.findViewById(R.id.fra_menuListv);
        SimpleAdapter adapter = new SimpleAdapter(getContext(),getData(),R.layout.item_menuvertical,
                new String[]{"title","info","img"},
                new int[]{R.id.title,R.id.info,R.id.img});
        LV.setAdapter(adapter);
        return view;
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "酱炒黄豆芽   3元");
        map.put("info", "黄豆芽味甘、性凉，入脾、大肠经； 具有清热利湿");
        map.put("img", R.mipmap.xiaoone);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title",
                "清炒大白菜   3元");
        map.put("info", "白菜含有丰富的粗纤维，不但能起到润肠、促进排毒的作用又刺激肠");
        map.put("img", R.mipmap.xiaotwo);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title",
                "肉末豆腐   3元");
        map.put("info", "具有清热利湿、消肿除痹、祛黑痣、治疣赘、润肌肤的功效");
        map.put("img",R.mipmap.xiaothree);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title",
                "南瓜   3元");
        map.put("info", "补气养血、防止牙龈出血、心血管硬化及降低胆固醇等功效。春天是");
        map.put("img",R.mipmap.xiaofour);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title",
                "菜秧   3元");
        map.put("info", "春天多吃些菜秧可以有效防止维生素B2缺乏症。豆芽中所含的维生素");
        map.put("img",R.mipmap.xiaofive);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title",
                "手撕包菜   3元");
        map.put("info", "具有清热利湿、消肿除痹、祛黑痣、治疣赘、润肌肤的功效");
        map.put("img",R.mipmap.xiaosix);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title",
                "肉沫蒸蛋   3元");
        map.put("info", "具有清热利湿、消肿除痹、祛黑痣、治疣赘、润肌肤的功效");
        map.put("img",R.mipmap.xiaoseven);
        list.add(map);


        return list;
    }

}
