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
        foodList.add(new Food("羊肉泡馍",0,"8元",3,4,1,"http://ox5cwubcf.bkt.clouddn.com/%E6%A2%85%E5%9B%AD-%E5%A6%82%E6%84%8F%E9%A6%84%E9%A5%A8-%E6%B0%B4%E9%A5%BA.png",3));
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
