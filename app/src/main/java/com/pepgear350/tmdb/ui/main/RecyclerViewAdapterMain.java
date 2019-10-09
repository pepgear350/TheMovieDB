package com.pepgear350.tmdb.ui.main;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pepgear350.tmdb.R;
import com.pepgear350.tmdb.db.entity.MoviesEntity;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapterMain extends RecyclerView.Adapter<RecyclerViewAdapterMain.ViewHolder> {

    private List<MoviesEntity> list = new ArrayList<>();
    private final MainActivity.OnListenerRecyclerView mListener;
    private Context context;

    public RecyclerViewAdapterMain(MainActivity.OnListenerRecyclerView listener, Context context) {
        mListener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_item, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MoviesEntity item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.releaseDate.setText(item.getRelease_date());
        holder.vote.setText(String.valueOf(item.getVote_average()));
        holder.overview.setText(item.getOverview());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(new ColorDrawable(ContextCompat.getColor(context, R.color.colorAccent)));
        requestOptions.error(R.drawable.ic_photo_black_24dp);
        requestOptions.centerCrop();

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(item.getBackdrop_path())
                .into(holder.backDrop);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MoviesEntity> listNew) {
        list = listNew;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View view;
        public final TextView title;
        public final TextView vote;
        public final TextView releaseDate;
        public final TextView overview;
        public final ImageView backDrop;
        private MainActivity.OnListenerRecyclerView listener;


        public ViewHolder(View view, MainActivity.OnListenerRecyclerView listener) {
            super(view);
            this.listener = listener;
            this.view = view;
            view.setOnClickListener(this);
            title = view.findViewById(R.id.item_movies_title);
            vote = view.findViewById(R.id.item_movies_vote);
            releaseDate = view.findViewById(R.id.item_movies_release_date);
            overview = view.findViewById(R.id.item_movies_overview);
            backDrop = view.findViewById(R.id.item_movies_backdrop);

        }


        @Override
        public void onClick(View view) {
            if (view == this.view) {
                MoviesEntity item = list.get(this.getLayoutPosition());
                listener.onClickMovie(item.getId());
            }
        }
    }
}
