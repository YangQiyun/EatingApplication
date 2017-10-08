package carteen.edu.seu.com.carteen.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/8.
 */
public class PersonActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initView();
    }

    void initView(){
        Toolbar toolbar= (Toolbar) findViewById(R.id.menu_person_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//箭头的点击事件
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
