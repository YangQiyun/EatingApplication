package carteen.edu.seu.com.carteen.DyanamicSection;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import carteen.edu.seu.com.carteen.Activity.BaseActivity;
import carteen.edu.seu.com.carteen.Model.NoticeInfor;
import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/23.
 */
public class NoticeActivity extends BaseActivity{


    private List<NoticeInfor> noticeInfors=new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        initData();
        init();
    }

    private void init() {
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
        recyclerView.setAdapter(new NoticeAdapter(this,noticeInfors));
    }

    private  void initData(){
        NoticeInfor noticeInfor=new NoticeInfor();
        noticeInfor.setTime("2017.09.28");
        NoticeInfor.notice notice=new NoticeInfor.notice();
        notice.setTitle("关于国庆节假期食堂运营的通知");
        notice.setInformant("总务处");
        noticeInfor.getInformation().add(notice);
        noticeInfors.add(noticeInfor);
        noticeInfor=new NoticeInfor();
        noticeInfor.setTime("2017.08");
        notice=new NoticeInfor.notice();
        notice.setTitle("食堂近期的维修说明");
        notice.setInformant("桃园");
        noticeInfor.getInformation().add(notice);
        notice=new NoticeInfor.notice();
        notice.setTitle("假期后食堂正式营业的通知");
        notice.setInformant("梅园");
        noticeInfor.getInformation().add(notice);
        noticeInfors.add(noticeInfor);
    }
}
