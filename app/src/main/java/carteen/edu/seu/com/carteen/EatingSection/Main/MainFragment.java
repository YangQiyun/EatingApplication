package carteen.edu.seu.com.carteen.EatingSection.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import carteen.edu.seu.com.carteen.Fragment.BaseFragment;
import carteen.edu.seu.com.carteen.Model.Food;
import carteen.edu.seu.com.carteen.Model.Windows;
import carteen.edu.seu.com.carteen.R;
import carteen.edu.seu.com.carteen.Utils.Cache.ACache;

/**
 * Created by Mind on 2017/10/2.
 */
public class MainFragment extends BaseFragment{
    private ArrayList<Windows> alldata=new ArrayList<>();
    private ArrayList<Food> fooddata=new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView=inflater.inflate(R.layout.include_eating_content,container,false);
        initView();
        initData();
        return mRootView;
    }

    private void initView(){
        tabLayout=findViewById(R.id.m_tablelayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void initData(){
        mainFragmentAdapter mainFragmentPagerAdapter=new mainFragmentAdapter(mActivity.getSupportFragmentManager(),mActivity);
        viewPager.setAdapter(mainFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        alldata.add(new Windows(0,"农家小碗菜","点餐快，菜品多。禾畈人家","北京",R.drawable.test1) );
        alldata.add(new Windows(1,"麦多馅饼","经过300度高温、短时间的加工工艺，表层香酥爽口、里层细嫩有筋、口感独特，完整的保留了食品原有的营养元素，它快捷、方便、鲜香宜人、老少皆宜、健康时尚。","北京",R.drawable.test1));
        alldata.add(new Windows(2,"担担面","著名的四川小吃，源起挑夫们在码头挑着担担卖面，所以名为担担面。用面粉擀制成面条，煮熟，舀上炒制的猪肉末而成。成菜面条细薄，卤汁酥香，咸鲜微辣，香气扑鼻，十分入味。","北京",R.drawable.test1));
        alldata.add(new Windows(3,"原生豆浆","秉承原汁原味的制作理念，其营养物质、保健功效成分的含量高，对人体有很好的营养保健作用。","地点北京",R.drawable.test1));
        alldata.add(new Windows(4,"石锅拌饭","发源地为韩国全州，后来演变为韩国的代表性食物。韩国全州的石锅拌饭名闻遐迩，拌饭里蕴涵着\"五行五脏五色\"的原理。","北京",R.drawable.test1));

        ACache cache=ACache.get(mActivity);
        cache.put("StoreData",alldata);

        //Food(String foodName, int foodWinId, int foodPrice, int foodCmtNum, int foodGrade, int foodIsSpecial, int foodImg)
        for(int i=0;i<5;++i){
            fooddata.add(new Food("清炒大白菜",i,15,2,4,1,R.drawable.store_one,0));
            fooddata.add(new Food("肉末豆腐",i,12,1,5,1,R.drawable.store_one,1));
            fooddata.add(new Food("黄豆芽爆炒",i,0,2,3,0,R.drawable.store_one,2));
            fooddata.add(new Food("肉末蒸蛋",i,8,3,4,1,R.drawable.store_one,3));
            fooddata.add(new Food("手撕包菜",i,9,4,5,1,R.drawable.store_one,4));
            fooddata.add(new Food("大白菜",i,10,5,4,0,R.drawable.store_one,5));
            cache.put("MenuData"+i,fooddata);
            fooddata.clear();
        }

    }
}
