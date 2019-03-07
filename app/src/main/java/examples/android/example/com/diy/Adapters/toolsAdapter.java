package examples.android.example.com.diy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import examples.android.example.com.diy.R;
import examples.android.example.com.diy.tools;

public class toolsAdapter extends RecyclerView.Adapter<toolsAdapter.toolsViewHolder> {


    private List<tools> result;
    private Context context;


    public toolsAdapter(List<tools> tools){

        if(tools!=null){

            result=tools;
        }


    }


    @Override
    public toolsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        int layoutIdForListItem = R.layout.tools_recycler_list_items;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new toolsViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull toolsViewHolder holder, int position) {


        holder.populateUI(result,holder.getAdapterPosition());

    }

    @Override
    public int getItemCount() {

        if(result!=null){
            return result.size();
        }
        else{return 0;}


    }


    class toolsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.toolName)
        TextView toolName;

        @Override
        public void onClick(View view) {

        }


        private toolsViewHolder (View view){
            super(view);
            ButterKnife.bind(this, view);

        }


        private void populateUI(List<tools> result, int index) {


            toolName.setText(result.get(index).getName()+"\n");

        }

    }
}
