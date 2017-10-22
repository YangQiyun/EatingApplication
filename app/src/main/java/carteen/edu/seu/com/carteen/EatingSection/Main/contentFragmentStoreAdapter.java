package carteen.edu.seu.com.carteen.EatingSection.Main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import carteen.edu.seu.com.carteen.EatingSection.Menu.MenuActivity;
import carteen.edu.seu.com.carteen.Model.Windows;
import carteen.edu.seu.com.carteen.R;


/**
 * Created by Mind on 2017/3/23.
 */
public class contentFragmentStoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Windows> StoreList;
    private Context context;
    private boolean ListType;
    /*
    * 参数ItemType ==ture 代表传入的List是Store 类型的，false 代表List是Menu
    * */
    public contentFragmentStoreAdapter(List<?> list, Context context, boolean ItemType){
        ListType=ItemType;
        this.context=context;
        if(true==ItemType){
            StoreList=(List<Windows>) list;
        }else{

        }
    }

    //定义两种item的类型，适配两个布局
    private enum ITEM_TYPE{
        ITEM_STORE,
        ITEM_MENU
    }

    @Override
    public int getItemViewType(int position) {
        if(ListType==true)
        return ITEM_TYPE.ITEM_STORE.ordinal();
        else
            return  ITEM_TYPE.ITEM_MENU.ordinal();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.ITEM_STORE.ordinal()){
            return new StoreHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store,parent,false));
        }else if(viewType == ITEM_TYPE.ITEM_MENU.ordinal()){
          //  return new MenuHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_maybe_delete,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.d("tag",String.valueOf(position));
        if(holder instanceof StoreHolder){
            StoreHolder storeHoler=(StoreHolder)holder;
            storeHoler.store_photo.setImageResource(StoreList.get(position).getWinImgUrl());
            storeHoler.store_name.setText(StoreList.get(position).getWinName());
            storeHoler.store_desc.setText(StoreList.get(position).getWinDes());

            storeHoler.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MenuActivity.class);
                    intent.putExtra("storeNumber",StoreList.get(position).getWinId());
                    intent.putExtra("storeName",StoreList.get(position).getWinName());
                    context.startActivity(intent);
                }
            });
        }
        if(holder instanceof MenuHolder){
            MenuHolder menuHolder= (MenuHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        if(ListType==true)
            return StoreList.size();
        else
            return 0;
    }

    //FirstFragment Store RecycleView ViewHolder
    public class StoreHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView store_photo;
        TextView store_name;
        TextView store_desc;

        public StoreHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.store_cardview);
            store_photo= (ImageView) itemView.findViewById(R.id.store_photo);
            store_name= (TextView) itemView.findViewById(R.id.store_name);
            store_desc= (TextView) itemView.findViewById(R.id.store_desc);
            //描述字体加粗
            store_desc.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }

    //FirstFragment Store 下的对于每一个菜品的ViewHolder
    public  class MenuHolder extends RecyclerView.ViewHolder{
//        ImageView menu_photo;
//        TextView menu_name;
//        TextView menu_desc;
//        TextView menu_price;
        public MenuHolder(View itemView) {
            super(itemView);
//            menu_photo= (ImageView) itemView.findViewById(R.id.menu_photo);
//            menu_name= (TextView) itemView.findViewById(R.id.menu_name);
//            menu_desc= (TextView) itemView.findViewById(R.id.menu_desc);
//            menu_price= (TextView) itemView.findViewById(R.id.menu_price);
        }
    }
}
