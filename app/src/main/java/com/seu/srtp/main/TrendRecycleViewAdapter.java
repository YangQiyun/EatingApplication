package com.seu.srtp.main;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.seu.srtp.data.NewTrend;

import java.util.List;

/**
 * Created by swallowman on 2017/3/23.
 */

public class TrendRecycleViewAdapter extends RecyclerView.Adapter<TrendRecycleViewAdapter.TrendsViewHolder> {

    private List<NewTrend> trends;
    private Context context;
    private boolean trend_like_flag=false;

    public TrendRecycleViewAdapter(List<NewTrend> trends, Context context) {
        this.trends = trends;
        this.context=context;
    }

    //自定义ViewHolder类
    static class TrendsViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView trend_commenter;
        TextView trend_content;
        TextView trend_date;
        ImageButton trendComment;
        ImageButton trendLike;


        public TrendsViewHolder(final View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            trend_commenter= (TextView) itemView.findViewById(R.id.trend_commenter);
            trend_date= (TextView) itemView.findViewById(R.id.trend_date);
            trend_content= (TextView) itemView.findViewById(R.id.trend_content);
            trendComment=(ImageButton)itemView.findViewById(R.id.trendComment);
            trendLike=(ImageButton)itemView.findViewById(R.id.trendLike);
        }
    }
    @Override
    public TrendRecycleViewAdapter.TrendsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_trend,viewGroup,false);
        TrendsViewHolder trendsViewHoldervh=new TrendsViewHolder(v);
        return trendsViewHoldervh;
    }

    @Override
    public void onBindViewHolder(final TrendRecycleViewAdapter.TrendsViewHolder trendsViewHolder, int i) {
        final int j=i;
        trendsViewHolder.trend_date.setText(trends.get(i).getDate());
        trendsViewHolder.trend_commenter.setText(trends.get(i).getCommenter());
        trendsViewHolder.trend_content.setText(trends.get(i).getContent());
        //点赞按钮
        trendsViewHolder.trendLike.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (trend_like_flag==false){
                    trend_like_flag=true;
                    trendsViewHolder.trendLike.setBackgroundResource(R.drawable.liked);
                }else {
                    trend_like_flag=false;
                    trendsViewHolder.trendLike.setBackgroundResource(R.drawable.like);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return trends.size();
    }
}
