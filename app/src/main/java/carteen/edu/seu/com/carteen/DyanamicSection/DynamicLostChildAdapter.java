package carteen.edu.seu.com.carteen.DyanamicSection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import carteen.edu.seu.com.carteen.Model.Comment;
import carteen.edu.seu.com.carteen.R;

/**
 * Created by Bill on 2017/9/20.
 */

public class DynamicLostChildAdapter extends RecyclerView.Adapter<DynamicLostChildAdapter.ViewHolder> {

    private Context context;
    private List<Comment> list = new ArrayList<>();

    public DynamicLostChildAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Comment> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lost_comment, parent, false);
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

        private TextView name;
        private TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.comment_name);
            this.content= (TextView) itemView.findViewById(R.id.comment_content);
        }

        private void update(int position) {
            Log.d("childrecycle", "update: "+position+list.get(position));
            name.setText(list.get(position).getUser().getUsrNick());
            content.setText(list.get(position).getContent());
        }

    }

}
