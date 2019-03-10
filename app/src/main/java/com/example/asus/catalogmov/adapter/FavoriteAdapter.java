package com.example.asus.catalogmov.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.asus.catalogmov.CustomOnItemClickListener;
import com.example.asus.catalogmov.DetailMov;
import com.example.asus.catalogmov.R;
import com.example.asus.catalogmov.model.FavModel;

import static com.example.asus.catalogmov.DetailMov.POSTER_IMAGE;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.Holder> {

    private Context context;
    private Cursor listFavorit;

    public FavoriteAdapter(Context context){
        this.context = context;
    }

    public void setListNotes(Cursor listFavorit){
        this.listFavorit = listFavorit;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rv_item, parent, false );
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        final FavModel model= getItem(position);
        Log.e("modelFav", model.toString());
        holder.txt_film.setText(model.getJudul());
        holder.txt_release.setText(model.getReleasDate());
        holder.txt_rating.setText(model.getPopularity());
//        holder.tvSinopsis.setText(model.getSinopsis());
        Glide.with(context).load(POSTER_IMAGE+model.getPoster()).thumbnail(0.5f)

                .into(holder.img_poster);

        holder.layout_container.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, DetailMov.class);
                intent.putExtra("id", model.getId());
                intent.putExtra("poster", model.getPoster());
                intent.putExtra("judul", model.getJudul());
                intent.putExtra("populer", model.getPopularity());
                intent.putExtra("rilis", model.getReleasDate());
                intent.putExtra("sinopsis", model.getSinopsis());
                context.startActivity(intent);
            }
        }));



    }

    @Override
    public int getItemCount() {
        if (listFavorit == null) return 0;
        return listFavorit.getCount();
    }

    private FavModel getItem(int position){
        if (!listFavorit.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        Log.e("posss", position+"");
        return new FavModel(listFavorit);
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
