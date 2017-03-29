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
        map.put("title", "\n炸酱面");
        map.put("info", "20元");
        map.put("img", R.mipmap.store_one);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "\n" +
                "炸酱面");
        map.put("info", "20元");
        map.put("img", R.mipmap.store_one);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "\n" +
                "炸酱面");
        map.put("info", "20元");
        map.put("img",R.mipmap.store_one);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "\n" +
                "炸酱面");
        map.put("info", "20元");
        map.put("img",R.mipmap.store_one);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "\n" +
                "炸酱面");
        map.put("info", "20元");
        map.put("img",R.mipmap.store_one);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "\n" +
                "炸酱面");
        map.put("info", "20元");
        map.put("img",R.mipmap.store_one);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "\n" +
                "炸酱面");
        map.put("info", "20元");
        map.put("img",R.mipmap.qiezi);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "\n" +
                "炸酱面");
        map.put("info", "20元");
        map.put("img",R.mipmap.qiezi);
        list.add(map);

        return list;
    }

}
