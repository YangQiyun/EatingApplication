package carteen.edu.seu.com.carteen.FindSection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import carteen.edu.seu.com.carteen.Activity.BaseActivity;
import carteen.edu.seu.com.carteen.Activity.ShowBigimageActivity;
import carteen.edu.seu.com.carteen.Model.Find;
import carteen.edu.seu.com.carteen.R;
import carteen.edu.seu.com.carteen.Utils.Cache.ACache;

/**
 * Created by Mind on 2017/10/21.
 */
public class FindActivity extends BaseActivity{
    private int positon;
    private ImageView img;
    private TextView title;
    private TextView content;
    private TextView author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        positon=this.getIntent().getIntExtra("positon",0);
        initView();
        initData();
    }

    void initView(){
        img= (ImageView) findViewById(R.id.find_activity_img);
        title=(TextView)findViewById(R.id.find_activity_title);
        content=(TextView)findViewById(R.id.find_activity_content);
        author=(TextView)findViewById(R.id.find_activity_author);
    }

    void  initData(){
        ACache cache=ACache.get(this);
        ArrayList<Find> finds= (ArrayList<Find>) cache.getAsObject("findList");
        Log.d("dsf", "initData: "+positon);
        img.setImageResource(finds.get(positon).getImge());
        title.setText(finds.get(positon).getTitle());
        content.setText(finds.get(positon).getContent());
        author.setText(finds.get(positon).getAuthor());


        final ArrayList<String> imglist=new ArrayList<>();
        switch (positon+1){
            case 1:
                imglist.add("http://ox5cwubcf.bkt.clouddn.com/find_one.jpg");
                break;
            case 2:
                imglist.add("http://ox5cwubcf.bkt.clouddn.com/find_two.jpg");
                break;
            case 5:
                imglist.add("http://ox5cwubcf.bkt.clouddn.com/find_five.jpg");
                break;
            case 4:
                imglist.add("http://ox5cwubcf.bkt.clouddn.com/find_four.jpg");
                break;
            case 3:
                imglist.add("http://ox5cwubcf.bkt.clouddn.com/find_three.jpg");
                break;
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindActivity.this,ShowBigimageActivity.class);
                Bundle temp=new Bundle();
                temp.putStringArrayList(ShowBigimageActivity.LIST,imglist);
                intent.putExtras(temp);
                startActivity(intent);
            }
        });
    }
}
