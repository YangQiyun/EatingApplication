package carteen.edu.seu.com.carteen.EatingSection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import carteen.edu.seu.com.carteen.EatingSection.Menu.MenuDetailActivity;
import carteen.edu.seu.com.carteen.Model.Food;
import carteen.edu.seu.com.carteen.R;
import carteen.edu.seu.com.carteen.Utils.Cache.ACache;

/**
 * Created by Mind on 2017/3/27.
 */
public class Menuallfragment extends Fragment {
    private ArrayList<Food> foodArrayList;
    private static final String TAG = "Menuallfragment";
    private static String ARGS="storeNumber";
    private int storeNum;

    //构造函数，传入一个storeNumber
    public static Menuallfragment newInstance(int storeNumber){
        Bundle bundle=new Bundle();
        Menuallfragment fragmentMenuHorizontalPager =new Menuallfragment();
        bundle.putInt(ARGS,storeNumber);
        fragmentMenuHorizontalPager.setArguments(bundle);
        return fragmentMenuHorizontalPager;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_vertival,container,false);
        ListView LV= (ListView) view.findViewById(R.id.fra_menuListv);
        storeNum=getArguments().getInt(ARGS);
        SimpleAdapter adapter = new SimpleAdapter(getContext(),getData(),R.layout.item_menuvertical,
                new String[]{"foodname","foodgrade","foodprice"},
                new int[]{R.id.foodname,R.id.foodgrade,R.id.foodprice});
        LV.setAdapter(adapter);
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Menuallfragment.this.getActivity(), MenuDetailActivity.class);
                intent.putExtra("foodid",foodArrayList.get(position).getFoodId());
                intent.putExtra("winid",foodArrayList.get(position).getFoodWinId());
                Menuallfragment.this.startActivity(intent);
            }
        });
        return view;
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        ACache cache=ACache.get(Menuallfragment.this.getActivity());
        foodArrayList= (ArrayList<Food>) cache.getAsObject("MenuData"+storeNum);
        for(Food food:foodArrayList){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("foodname", food.getFoodName());
            switch (food.getFoodGrade()){
                case 0:
                    map.put("foodgrade", "☆☆☆☆★");
                    break;
                case 1:
                    map.put("foodgrade", "☆☆☆★★");
                    break;
                case 2:
                    map.put("foodgrade", "☆☆★★★");
                    break;
                case 3:
                    map.put("foodgrade", "☆★★★★");
                    break;
                case 4:
                    map.put("foodgrade", "★★★★★");
                    break;
                default:
                    map.put("foodgrade", "★★★★★");
                    break;
            }
            map.put("foodprice", food.getFoodPrice());
            list.add(map);
        }
        return list;
    }

}
