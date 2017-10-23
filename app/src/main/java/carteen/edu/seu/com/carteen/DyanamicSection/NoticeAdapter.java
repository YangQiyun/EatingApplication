package carteen.edu.seu.com.carteen.DyanamicSection;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import carteen.edu.seu.com.carteen.Model.NoticeInfor;
import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/11.
 */
public class NoticeAdapter extends BaseQuickAdapter<NoticeInfor,BaseViewHolder> {
    private  Context context;
    public NoticeAdapter(Context context, List<NoticeInfor> data) {
        super(R.layout.item_notice_parent,data);
        this.context=context;
    }



    @Override
    protected void convert(BaseViewHolder helper, NoticeInfor item) {

        helper.setText(R.id.notice_time,item.getTime());

        RecyclerView rvchild=helper.getView(R.id.rv_child);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setAutoMeasureEnabled(true);
        rvchild.setLayoutManager(manager);
        ;
        //子recycleview的刷新
        if(rvchild.getAdapter()==null){
            rvchild.setAdapter(new NoticeChildAdapter(context));
            ((NoticeChildAdapter)rvchild.getAdapter()).setList(item.getInformation());
        }
    }
}
