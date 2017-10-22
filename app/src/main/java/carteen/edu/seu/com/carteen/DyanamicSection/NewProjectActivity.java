package carteen.edu.seu.com.carteen.DyanamicSection;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import carteen.edu.seu.com.carteen.Activity.BaseActivity;
import carteen.edu.seu.com.carteen.Model.Food;
import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/23.
 */
public class NewProjectActivity extends BaseActivity{

    private List<Food> foodList=new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        initData();
        initView();
    }

    private void initData(){
        foodList.add(new Food("肉末蒸蛋",0,8,3,4,1,R.drawable.store_one,3));
        foodList.add(new Food("手撕包菜",1,9,4,5,1,R.drawable.store_one,4));
        foodList.add(new Food("大白菜",1,10,5,4,0,R.drawable.store_one,5));
    }

    private void  initView(){
        ImageButton imageButton= (ImageButton) findViewById(R.id.m_toolbar_selector);
        imageButton.setVisibility(View.INVISIBLE);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//箭头的点击事件
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView= (RecyclerView) findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NewProjectAdapter(this,foodList));
    }
}
