package carteen.edu.seu.com.carteen.EatingSection.Main;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import carteen.edu.seu.com.carteen.Fragment.BaseFragment;
import carteen.edu.seu.com.carteen.Model.Food;
import carteen.edu.seu.com.carteen.Model.Windows;
import carteen.edu.seu.com.carteen.MyApplication;
import carteen.edu.seu.com.carteen.R;
import carteen.edu.seu.com.carteen.Utils.Cache.ACache;

/**
 * Created by Mind on 2017/10/2.
 */
public class MainFragment extends BaseFragment{
    private ArrayList<Windows> alldata=new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String TAG = "MainFragment";
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

        try {
            Cursor cursor =MyApplication.getInstance().sqLiteDatabase.query("windows",new String[]{"winId","winName","winDes","winCantType","winImgUrl"},null,null,null,null,null);
            if (cursor!=null&&cursor.moveToFirst()) {
                boolean havenext=true;
                while (havenext) {
                    alldata.add(new Windows(cursor.getInt(cursor.getColumnIndex("winId")),cursor.getString(cursor
                            .getColumnIndex("winName")),cursor.getString(cursor
                            .getColumnIndex("winDes")),String.valueOf(cursor.getInt(cursor.getColumnIndex("winCantType"))),R.drawable.window_one,cursor.getString(cursor
                            .getColumnIndex("winImgUrl"))) );
                    havenext=cursor.moveToNext();
                }
                cursor.close();
            }
        } catch (Exception e) {
            Log.d(TAG, "initData db: "+e.getMessage());
        }

//        alldata.add(new Windows(0,"千层饼","层层相分，外黄里暄，酥软油润，热食不腻，凉吃不散，味道香美","梅园",R.drawable.window_one,"") );
//        alldata.add(new Windows(1,"担担面","面条细薄，卤汁酥香，咸鲜微辣，香气扑鼻，十分入味","梅园",R.drawable.window_two,""));
//        alldata.add(new Windows(2,"花式烩饭","花样百变，种类繁多，满足你的各种需求","梅园",R.drawable.window_three,""));
//        alldata.add(new Windows(3,"锡纸饭","任选素菜，锡纸炒饭，粒粒饱满，美味可口","梅园",R.drawable.window_four,""));
//        alldata.add(new Windows(4,"香扒饭","经典菜式，两种素菜，荤素搭配，营养美味","梅园",R.drawable.window_xiang,""));


        ACache cache=ACache.get(mActivity);
        cache.put("StoreData",alldata);

        //Food(String foodName, int foodWinId, int foodPrice, int foodCmtNum, int foodGrade, int foodIsSpecial, int foodImg)

        try {
            Cursor cursor =MyApplication.getInstance().sqLiteDatabase.query("food",new String[]{"foodId","foodName","foodWinId","foodPrice","foodCmtNum","foodGrade","foodIsSpecial","foodImgUrl"},null,null,null,null,null);
            ArrayList<Food> fooddata=new ArrayList<>();
            int winNum=1;
            if (cursor!=null&&cursor.moveToFirst()) {
                boolean havenext=true;
                while (havenext) {
                    if(cursor.getInt(cursor.getColumnIndex("foodWinId"))!=winNum){
                        cache.put("MenuData"+winNum,fooddata);
                        winNum++;
                        fooddata=new ArrayList<>();
                    }
                    fooddata.add(new Food(cursor.getString(cursor.getColumnIndex("foodName")),
                            cursor.getInt(cursor.getColumnIndex("foodWinId")),
                            cursor.getString(cursor.getColumnIndex("foodPrice")),
                            cursor.getInt(cursor.getColumnIndex("foodCmtNum")),
                            cursor.getInt(cursor.getColumnIndex("foodGrade")),
                            cursor.getInt(cursor.getColumnIndex("foodIsSpecial")),
                            cursor.getString(cursor.getColumnIndex("foodImgUrl")),
                            cursor.getInt(cursor.getColumnIndex("foodId"))));
                    havenext=cursor.moveToNext();
                }
                cursor.close();
            }
        } catch (Exception e) {
            Log.d(TAG, "initData db: "+e.getMessage());
        }

//        fooddata.add(new Food("公婆饼",0,3,2,4,1,R.drawable.food_gongpobin,0));
//        fooddata.add(new Food("素卷饼",0,3,1,5,1,R.drawable.food_juanbin,1));
//        fooddata.add(new Food("原味手抓饼",0,3.5,2,3,0,R.drawable.food_purebin,2));
//        fooddata.add(new Food("手抓饼",0,4.5,3,4,1,R.drawable.food_shouzhuabin,3));
//        fooddata.add(new Food("酱香饼",0,2,4,5,1,R.drawable.food_jiangxiang,4));
//        fooddata.add(new Food("鸡蛋饼",0,2,5,4,0,R.drawable.food_eggbin,5));
//        cache.put("MenuData"+0,fooddata);
//        fooddata=new ArrayList<>();
//        fooddata.add(new Food("三鲜干拌面",1,6,2,4,1,R.drawable.food_sanxianganban,6));
//        fooddata.add(new Food("三鲜油泼面",1,8,1,5,1,R.drawable.food_sanxianyoupomian,7));
//        fooddata.add(new Food("三鲜面",1,8,2,3,0,R.drawable.food_sanxianmian,8));
//        fooddata.add(new Food("四川燃面",1,9,3,4,1,R.drawable.food_sichuan,9));
//        fooddata.add(new Food("干切牛肉油泼面",1,10,4,5,1,R.drawable.food_nirouyoupo,10));
//        fooddata.add(new Food("红烧牛肉面",1,10,5,4,0,R.drawable.food_hongshaoniuroumian,11));
//        fooddata.add(new Food("臊子面",1,8,3,4,1,R.drawable.food_zaozimian,12));
//        fooddata.add(new Food("西红柿鸡蛋面",1,5,4,5,1,R.drawable.food_xihongshi,13));
//        cache.put("MenuData"+1,fooddata);
//        fooddata=new ArrayList<>();
//        fooddata.add(new Food("大脸鸡排",2,11,2,4,1,R.drawable.food_dalianjipa,14));
//        fooddata.add(new Food("糖醋里脊",2,9,1,5,1,R.drawable.food_tanculiji,15));
//        fooddata.add(new Food("酸汤金针菇肥牛",2,11,2,3,0,R.drawable.food_jinzhen,16));
//        cache.put("MenuData"+2,fooddata);
//        fooddata=new ArrayList<>();
//        fooddata.add(new Food("香肠锡纸饭",3,9,2,4,1,R.drawable.food_xiangchang,17));
//        fooddata.add(new Food("鸡块锡纸饭",3,9,1,5,1,R.drawable.food_jikuai,18));
//        fooddata.add(new Food("火腿锡纸饭",3,10,2,3,0,R.drawable.food_huotui,19));
//        cache.put("MenuData"+3,fooddata);
//        fooddata=new ArrayList<>();
//        fooddata.add(new Food("杭椒牛柳",4,11,2,4,1,R.drawable.food_kanjiangniuliu,20));
//        fooddata.add(new Food("糖醋小排",4,12,1,5,1,R.drawable.food_tangcuxiapai,21));
//        fooddata.add(new Food("红烧肉",4,10,2,3,0,R.drawable.food_hongshaorou,22));
//        fooddata.add(new Food("酱香鸭腿",4,10,2,3,0,R.drawable.food_jiangxiangjitui,23));
//        cache.put("MenuData"+4,fooddata);
    }
}
