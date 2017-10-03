package carteen.edu.seu.com.carteen.EatingSection.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import carteen.edu.seu.com.carteen.Model.Food;
import carteen.edu.seu.com.carteen.R;
import carteen.edu.seu.com.carteen.Utils.Cache.ACache;

/**
 * Created by Mind on 2017/10/3.
 */
public class MenuDetailActivity extends AppCompatActivity {

    private TextView foodname;
    private TextView foodgrade;
    private TextView foodprice;
    private TextView fooddescription;
    private int foodid;
    private Food food;
    private static final String TAG = "MenuDetailActivitykk";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descrition);
        Intent intent=getIntent();
        foodid=intent.getIntExtra("foodid",0);
        ACache cache=ACache.get(this);
        ArrayList<Food> foodArrayList= (ArrayList<Food>) cache.getAsObject("MenuData"+foodid);
        Log.d(TAG, "onCreate: foodid"+foodid);
        food=foodArrayList.get(foodid);
        Log.d(TAG, "onCreate: foodprice"+food.getFoodPrice());
        initView();
        initData();
    }



    private void initView(){
        Toolbar toolbar= (Toolbar) findViewById(R.id.menu_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//箭头的点击事件
            @Override
            public void onClick(View v) {
                finish();
            }
        });
         foodname= (TextView) findViewById(R.id.menu_name);
         foodgrade= (TextView) findViewById(R.id.menu_grade);
         foodprice= (TextView) findViewById(R.id.menu_price);
         fooddescription= (TextView) findViewById(R.id.menu_des);
    }

    private void initData(){
        foodname.setText(food.getFoodName());
        foodprice.setText(food.getFoodPrice()+"元");
        fooddescription.setText(food.getFooddescription());
        switch (food.getFoodGrade()){
            case 0:
                foodgrade.setText("☆☆☆☆★");
                break;
            case 1:
                foodgrade.setText("☆☆☆★★");
                break;
            case 2:
                foodgrade.setText("☆☆★★★");
                break;
            case 3:
                foodgrade.setText("☆★★★★");
                break;
            case 4:
                foodgrade.setText("★★★★★");
                break;
            default:
                foodgrade.setText("★★★★★");
                break;
        }

    }
}
