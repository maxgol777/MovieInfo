package work.maxgol.movieviewer.jv.ui.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import work.maxgol.movieviewer.R;
import work.maxgol.movieviewer.jv.common.constants.ApiConstants;
import work.maxgol.movieviewer.jv.domain.model.Movie;
import work.maxgol.movieviewer.jv.ui.ui.interfaces.RecyclerItemClickListener;

/**
 * Created by Maxgol on 10.03.2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<Movie> list;

    public MyRecyclerViewAdapter(Context context, List<Movie> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Movie item = list.get(position);

        holder.titleView.setText(item.getTitle());
        holder.mContentView.setText(item.getRelease_date());

        // -- set image --
        Glide.with(context)
                .load(ApiConstants.Glide.imagePath154 + list.get(position).getPoster_path())
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.mPreview);

        holder.itemView.setOnClickListener(v -> ((RecyclerItemClickListener) context).itemClick(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        public final View itemView;
        public final ImageView mPreview;
        public final TextView titleView;
        public final TextView mContentView;

        public MyViewHolder(View view) {
            super(view);
            titleView = (TextView) view.findViewById(R.id.title);
            mContentView = (TextView) view.findViewById(R.id.content);
            mPreview = (ImageView) view.findViewById(R.id.moviePreview);
            itemView = view;
        }
    }
}
