package carteen.edu.seu.com.carteen.DyanamicSection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import carteen.edu.seu.com.carteen.Model.NoticeInfor;
import carteen.edu.seu.com.carteen.R;

/**
 * Created by Bill on 2017/9/20.
 */

public class NoticeChildAdapter extends RecyclerView.Adapter<NoticeChildAdapter.ViewHolder> {

    private Context context;
    private List<NoticeInfor.notice> list = new ArrayList<>();

    public NoticeChildAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<NoticeInfor.notice> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notice_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView bottom;
        private Button formant;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.notice_title);
            this.bottom= (TextView) itemView.findViewById(R.id.bottom_line);
            this.formant= (Button) itemView.findViewById(R.id.informant);
        }

        private void update(int position) {
            title.setText(list.get(position).getTitle());
            if(position==list.size()-1)
                bottom.setVisibility(View.GONE);
            else
                bottom.setVisibility(View.VISIBLE);
            formant.setText(list.get(position).getInformant());
        }

    }

}
