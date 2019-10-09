package com.pepgear350.tmdb.ui.details;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pepgear350.tmdb.R;
import com.pepgear350.tmdb.db.entity.CreditsEntity;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapterDetails extends RecyclerView.Adapter<RecyclerViewAdapterDetails.ViewHolder> {

    private List<CreditsEntity> list = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapterDetails(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.credits_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CreditsEntity item = list.get(position);

        holder.character.setText(item.getCharacter());
        holder.name.setText(item.getName());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(new ColorDrawable(ContextCompat.getColor(context, R.color.colorAccent)));
        requestOptions.error(R.drawable.ic_photo_black_24dp);
        requestOptions.centerCrop();

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(item.getProfile_path())
                .into(holder.profile);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<CreditsEntity> listNew) {
        list = listNew;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView character;
        public final TextView name;
        public final ImageView profile;


        public ViewHolder(View view) {
            super(view);

            this.view = view;
            character = view.findViewById(R.id.character_cast);
            name = view.findViewById(R.id.name_cast);
            profile = view.findViewById(R.id.profile_cast);
        }
    }
}
