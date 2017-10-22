package carteen.edu.seu.com.carteen.DyanamicSection;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import carteen.edu.seu.com.carteen.DyanamicSection.util.GridviewAdapter;
import carteen.edu.seu.com.carteen.DyanamicSection.util.MyGridView;
import carteen.edu.seu.com.carteen.Model.LostInfor;
import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/11.
 */
public class DyanmicLostAdapter extends BaseQuickAdapter<LostInfor,BaseViewHolder> {
    private  Context context;
    public DyanmicLostAdapter(Context context,List<LostInfor> data) {
        super(R.layout.item_dynamic_lost,data);
        this.context=context;
    }



    @Override
    protected void convert(BaseViewHolder helper, LostInfor item) {
        MyGridView gridView= helper.getView(R.id.gridview);
        if (item.getImageList().size() !=0) {
            switch (item.getImageList().size()) {
                case 1:
                    gridView.setNumColumns(1);
                    break;
                case 2:
                    gridView.setNumColumns(2);
                    break;
                case 3:
                    gridView.setNumColumns(2);
                    break;
                case 4:
                    gridView.setNumColumns(2);

                    break;
                default:
                    gridView.setNumColumns(3);
                    break;
            }
            gridView.setVisibility(View.VISIBLE);
            gridView.setAdapter(new GridviewAdapter(context,item.getImageList()));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //点击查看大图
                }
            });
        }else{
            gridView.setVisibility(View.GONE);
        }

        Glide.with(context).load(item.getLostuser().getImg()).into((ImageView) helper.getView(R.id.Imav_touxiang));
        helper.setText(R.id.tv_name,item.getLostuser().getUsrNick());
        helper.setText(R.id.tv_time,item.getTime());
        helper.setText(R.id.lost_content,item.getContent());
        RecyclerView rvchild=helper.getView(R.id.rv_child);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setAutoMeasureEnabled(true);
        rvchild.setLayoutManager(manager);
        ;
        //子recycleview的刷新
        if(rvchild.getAdapter()==null){
            Log.d(TAG, "convert: "+"this...............");
            rvchild.setAdapter(new DynamicLostChildAdapter(context));
            ((DynamicLostChildAdapter)rvchild.getAdapter()).setList(item.getCommentList());
        }
    }
}
