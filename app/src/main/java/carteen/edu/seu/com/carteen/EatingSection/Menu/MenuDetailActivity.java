package carteen.edu.seu.com.carteen.EatingSection.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import carteen.edu.seu.com.carteen.Activity.ShowBigimageActivity;
import carteen.edu.seu.com.carteen.Model.Food;
import carteen.edu.seu.com.carteen.R;
import carteen.edu.seu.com.carteen.Utils.Cache.ACache;

/**
 * Created by Mind on 2017/10/3.
 */
public class MenuDetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView foodname;
    private TextView foodgrade;
    private TextView foodprice;
    private TextView fooddescription;
    private int foodid;
    private int winid;
    private Food food;
    private static final String TAG = "MenuDetailActivitykk";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descrition);
        Intent intent=getIntent();
        winid=intent.getIntExtra("winid",0);
        foodid=intent.getIntExtra("foodid",0);
        ACache cache=ACache.get(this);
        ArrayList<Food> foodArrayList= (ArrayList<Food>) cache.getAsObject("MenuData"+winid);
        for(Food mfood:foodArrayList) {
            Log.d(TAG, "onCreate: "+mfood.getFoodId());
            if (mfood.getFoodId() == foodid) {
                food = mfood;
                Log.d(TAG, "onCreate: foodprice find");
                break;
            }
        }
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
        imageView= (ImageView) findViewById(R.id.menu_img);
    }

    private void initData(){
        foodname.setText(food.getFoodName());
        foodprice.setText(food.getFoodPrice());
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
        Glide.with(MenuDetailActivity.this).load(food.getFoodImg()).placeholder(R.drawable.loading_error).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuDetailActivity.this,ShowBigimageActivity.class);
                Bundle temp=new Bundle();
                ArrayList<String> img=new ArrayList<String>();
                img.add(food.getFoodImg());
                temp.putStringArrayList(ShowBigimageActivity.LIST,img);
                intent.putExtras(temp);
                startActivity(intent);
            }
        });
    }
}
