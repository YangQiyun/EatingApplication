package carteen.edu.seu.com.carteen.DyanamicSection;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import carteen.edu.seu.com.carteen.DyanamicSection.util.RoundImageView;
import carteen.edu.seu.com.carteen.Model.Food;
import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/11.
 */
public class NewProjectAdapter extends BaseQuickAdapter<Food,BaseViewHolder> {
    private  Context context;
    public NewProjectAdapter(Context context, List<Food> data) {
        super(R.layout.item_newproject,data);
        this.context=context;
    }



    @Override
    protected void convert(BaseViewHolder helper, Food item) {
        helper.setText(R.id.food_name,item.getFoodName());
        RoundImageView imageView=helper.getView(R.id.card_img);
        Glide.with(context).load(item.getFoodImg()).into(imageView);
        helper.setText(R.id.food_description,item.getFooddescription());
    }
}
