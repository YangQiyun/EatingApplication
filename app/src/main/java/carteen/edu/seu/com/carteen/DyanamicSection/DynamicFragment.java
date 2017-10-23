package carteen.edu.seu.com.carteen.DyanamicSection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import carteen.edu.seu.com.carteen.DyanamicSection.util.RoundImageView;
import carteen.edu.seu.com.carteen.Fragment.BaseFragment;
import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/8.
 */
public class DynamicFragment extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView=inflater.inflate(R.layout.fragment_dynamic,container,false);
        initView();
        initData();
        return mRootView;
    }

    private  void initView(){
        RoundImageView findcard=findViewById(R.id.card_find);
        findcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mActivity,DynamicLostActivity.class);
                mActivity.startActivity(intent);
            }
        });
        RoundImageView firecard=findViewById(R.id.fire);
        firecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mActivity,NewProjectActivity.class);
                mActivity.startActivity(intent);
            }
        });
//        RoundImageView newcard=findViewById(R.id.card_new);
//        newcard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(mActivity,NewProjectActivity.class);
//                mActivity.startActivity(intent);
//            }
//        });
        ImageButton upload=findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mActivity,SendAcitivity.class);
               mActivity.startActivity(intent);
            }
        });
        RoundImageView noticecard=findViewById(R.id.canteen_notice);
        noticecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mActivity,NoticeActivity.class);
                mActivity.startActivity(intent);
            }
        });
    }

    private  void initData(){

    }
}
