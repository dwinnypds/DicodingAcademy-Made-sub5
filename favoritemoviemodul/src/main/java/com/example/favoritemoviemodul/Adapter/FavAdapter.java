package com.example.favoritemoviemodul.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.favoritemoviemodul.DetailFav;
import com.example.favoritemoviemodul.R;
import com.example.favoritemoviemodul.model.FavModel;

import static com.example.favoritemoviemodul.API.ADB.POSTER_IMAGE;
import static com.example.favoritemoviemodul.DB.DBContract.CONTENT_URI;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.Holder>{


    private Cursor list;

    public FavAdapter(Cursor items) {
        replaceAll(items);
    }

    public void replaceAll(Cursor items) {
        list = items;
        notifyDataSetChanged();
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        else return list.getCount();
    }

    private FavModel getItem(int position) {
        if (!list.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }
        return new FavModel(list);
    }

    public class Holder extends RecyclerView.ViewHolder{
        private ImageView img_poster;
        private TextView txt_film, txt_release, txt_rating;
        private LinearLayout layout_container;

        public Holder(View itemView) {
            super(itemView);

            img_poster = itemView.findViewById(R.id.img_poster);
            txt_film = itemView.findViewById(R.id.txt_judul);
            txt_release = itemView.findViewById(R.id.txt_releas_date);
            txt_rating = itemView.findViewById(R.id.txt_popularity);
            layout_container = itemView.findViewById(R.id.layout_container);
        }

        public void bind(final FavModel item) {
            txt_film.setText(item.getJudul());
            txt_release.setText(item.getReleasDate());
            txt_rating.setText(item.getPopularity());
            Glide.with(itemView.getContext())
                    .load(POSTER_IMAGE+ item.getPoster())
                    .into(img_poster);
            layout_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailFav.class);
                    intent.setData(Uri.parse(CONTENT_URI + "/" + item.getId()));
                    itemView.getContext().startActivity(intent);
                }
            });

        }


    }
}
