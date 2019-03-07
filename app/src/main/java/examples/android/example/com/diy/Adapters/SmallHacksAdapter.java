package examples.android.example.com.diy.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import examples.android.example.com.diy.R;
import examples.android.example.com.diy.videos;

public class SmallHacksAdapter extends RecyclerView.Adapter<SmallHacksAdapter.SmallHacksViewHolder> {

private Context context;
private List<videos> result;
private ListItemClickListener mListItemClickListener;


public interface ListItemClickListener {

    void onListItemClick(int clickedItemIndex);

}


    public SmallHacksAdapter(List<videos> details, ListItemClickListener listener){

        if(details!=null){

            result=details;
        }
        mListItemClickListener=listener;

    }


    @Override
    public SmallHacksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        int layoutIdForListItem = R.layout.recycler_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new SmallHacksViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SmallHacksViewHolder holder, int position) {


        holder.populateUI(result,holder.getAdapterPosition());

    }


    @Override
    public int getItemCount() {

        if(result!=null){
            return result.size();
        }
        else{return 0;}


    }




    class SmallHacksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.recipeImage)
        ImageView imageView;

        @Override
        public void onClick(View view) {

            if(mListItemClickListener!=null){
                int position=getAdapterPosition();
                mListItemClickListener.onListItemClick(position);}
        }

        private SmallHacksViewHolder (View view){
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);


        }


        private void populateUI(List<videos> result, int index) {


            Picasso.with(context)
                    .load(result.get(index).getImage())
                    .error(R.drawable.no_image)
                    .placeholder(R.drawable.processing)
                    .into(imageView);

            name.setText(result.get(index).getName());

        }

    }

}
