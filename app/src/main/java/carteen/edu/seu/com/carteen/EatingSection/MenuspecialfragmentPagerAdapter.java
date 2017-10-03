package carteen.edu.seu.com.carteen.EatingSection;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import carteen.edu.seu.com.carteen.EatingSection.Menu.MenuDetailActivity;
import carteen.edu.seu.com.carteen.Model.Food;
import carteen.edu.seu.com.carteen.R;
import carteen.edu.seu.com.carteen.Utils.Cache.ACache;


/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class MenuspecialfragmentPagerAdapter extends PagerAdapter {
    private Context mContext;
    private FragmentManager fm;
    private ArrayList<Food> foodlist;
    private LayoutInflater mLayoutInflater;
    private int storeNumber;
    private static final String TAG = "MenuspecialPagerAdapter";

    public MenuspecialfragmentPagerAdapter(final Context context, final FragmentManager Fm, int storeNumber) {
        mContext = context;
        this.storeNumber=storeNumber;
        fm=Fm;
         ACache cache=ACache.get(mContext);
        foodlist = (ArrayList<Food>) cache.getAsObject("MenuData"+storeNumber);
        mLayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return foodlist.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        view = mLayoutInflater.inflate(R.layout.item_menuhorizon, container, false);
        Food food=foodlist.get(position);
        ImageView imageView= (ImageView) view.findViewById(R.id.image);
        TextView foodname= (TextView) view.findViewById(R.id.txt_item);
        TextView foodprice= (TextView) view.findViewById(R.id.item_price);
        TextView foodgrade= (TextView) view.findViewById(R.id.item_rank);
        foodprice.setText("价格"+food.getFoodPrice()+"元");
        foodname.setText(food.getFoodName());
        imageView.setImageResource(food.getFoodImg());
        switch (food.getFoodGrade()){
            case 0:
                foodgrade.setText("☆☆☆☆★");
                break;
            case 1:
                foodgrade.setText("☆☆☆★★");
                break;
            case 2:
                foodgrade.setText("☆☆★★★");
                break;
            case 3:
                foodgrade.setText("☆★★★★");
                break;
            case 4:
                foodgrade.setText("★★★★★");
                break;
            default:
                foodgrade.setText("★★★★★");
                break;
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MenuDetailActivity.class);
                intent.putExtra("foodid",foodlist.get(position).getFoodId());
                mContext.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }

}
