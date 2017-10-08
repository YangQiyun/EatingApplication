package carteen.edu.seu.com.carteen.FindSection;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import carteen.edu.seu.com.carteen.Model.Find;
import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/8.
 */
public class FindAdapter extends BaseMultiItemQuickAdapter<Find,BaseViewHolder>{
    private  final  static int Special=1;
    private Context context;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public FindAdapter(Context context,List<Find> data) {
        super(data);
        this.context=context;
        //取决于find的getitemtype函数的返回值
        addItemType(0, R.layout.item_find_one);
        addItemType(Special,R.layout.item_find_special);
    }

    @Override
    protected void convert(BaseViewHolder helper, Find item) {
        if(0==helper.getPosition()){
            TextView textView=helper.getView(R.id.fenge);
            textView.setVisibility(View.INVISIBLE);
            Log.d(TAG, "convert: "+helper.getPosition());
        }
        else
        switch (helper.getPosition()%3){
            case 2:
                break;
            default:
                break;
        }

    }
}
