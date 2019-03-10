package com.example.asus.catalogmov.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.catalogmov.DetailMov;
import com.example.asus.catalogmov.R;
import com.example.asus.catalogmov.model.Film;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.Holder> {

    private Context context;
    private ArrayList<Film> arrayList = new ArrayList<>();


    public FilmAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Film> items) {
        arrayList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rv_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.txt_film.setText(arrayList.get(position).getJudul());
        holder.txt_rating.setText(arrayList.get(position).getPopuler());
        holder.txt_release.setText(arrayList.get(position).getRilis());


        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w185/" + arrayList.get(position).getPoster())
                .into(holder.img_poster);

        holder.layout_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMov.class);
                intent.putExtra("id", arrayList.get(position).getId());
                intent.putExtra("poster", arrayList.get(position).getPoster());
                intent.putExtra("judul", arrayList.get(position).getJudul());
                intent.putExtra("populer", arrayList.get(position).getPopuler());
                intent.putExtra("rilis", arrayList.get(position).getRilis());
                intent.putExtra("sinopsis", arrayList.get(position).getSinopsis());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
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
    }
}

