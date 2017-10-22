package carteen.edu.seu.com.carteen.FindSection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import carteen.edu.seu.com.carteen.Fragment.BaseFragment;
import carteen.edu.seu.com.carteen.Model.Find;
import carteen.edu.seu.com.carteen.R;
import carteen.edu.seu.com.carteen.Utils.Cache.ACache;

/**
 * Created by Mind on 2017/10/8.
 */
public class Findfragment extends BaseFragment{
    private static final String TAG = "Findfragment";
    private ArrayList<Find> findList =new ArrayList<>();
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView=inflater.inflate(R.layout.fragment_find,container,false);
        initData();
        initView();

        return mRootView;
    }

    private  void initView(){
        mRecyclerView=findViewById(R.id.mrecyclerview);
        List<Find> data=new ArrayList<>(9);
        for(int i=0;i<9;++i){
            Find find=new Find();
            if(i%3==2)
            find.setSpecial(true);
            data.add(find);
        }
        FindAdapter findAdapter=new FindAdapter(this.mActivity,findList);
        findAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(findAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                //showImageViewPager(position, DataManager.getPictureUrlList(), DataManager.getUploadImgUrlList(), "local", "upload");
                Log.d(TAG, "onSimpleItemClick: "+position);
                Intent intent=new Intent(mActivity,FindActivity.class);
                intent.putExtra("positon",position);
                mActivity.startActivity(intent);
            }
        });
    }

    private  void initData(){
        Find find=new Find();
        find.setAuthor("网易健康");
        find.setTitle("熬夜吃什么好 适合熬夜吃的7种零食");
        find.setImge(R.drawable.find_one);
        find.setContent(getResources().getString(R.string.find_one));
        findList.add(find);
        find=new Find();
        find.setAuthor("百度健康");
        find.setTitle("熬夜后，你要吃什么？");
        find.setImge(R.drawable.find_two);
        find.setContent(getResources().getString(R.string.find_two));
        findList.add(find);
        Find find2=new Find();
        find2.setAuthor("搜狐美食");
        find2.setTitle("担担面背后的一段励志故事");
        find2.setImge(R.drawable.find_three);
        find2.setContent(getResources().getString(R.string.find_tree));
        find2.setSpecial(true);
        findList.add(find2);
        Find find3=new Find();
        find3.setAuthor("一周食谱");
        find3.setTitle("不吃早餐？这里教你每天早餐顿顿不一样");
        find3.setImge(R.drawable.find_four);
        find3.setContent(getResources().getString(R.string.find_four));
        findList.add(find3);
        Find find4=new Find();
        find4.setAuthor("潮汕美食会");
        find4.setTitle("火遍全国的汕头牛肉火锅，你真懂吃么？ ");
        find4.setImge(R.drawable.find_five);
        find4.setContent(getResources().getString(R.string.find_five));
        findList.add(find4);
        Log.d(TAG, "initData: "+findList.size());
        ACache cache=ACache.get(this.mActivity);
        cache.put("findList",findList);
    }
}
