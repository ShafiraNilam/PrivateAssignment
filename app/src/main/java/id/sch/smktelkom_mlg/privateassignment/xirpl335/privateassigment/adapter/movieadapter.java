package id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.DetilActivity;
import id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.PlayingFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.model.Movie;

/**
 * Created by USER on 13/05/2017.
 */

public class movieadapter extends RecyclerView.Adapter<movieadapter.ViewHolder> {
    public String url = "https://image.tmdb.org/t/p/w500";
    public String image;
    ArrayList<Movie> mlist;
    PlayingFragment playingfragment;
    Context context;
    //private int lastposition = -1;

    public movieadapter(PlayingFragment playingFragment, ArrayList<Movie> mlist, Context context) {
        this.mlist = mlist;
        this.playingfragment = playingFragment;
        this.context = context;
    }

    @Override
    public movieadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.playing_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(movieadapter.ViewHolder holder, int position) {
        final Movie results = mlist.get(position);
        holder.tvJudul.setText(results.title);
        holder.tvDeskripsi.setText(results.overview);
        image = url + results.backdrop_path;
        Glide.with(context).load(image)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivFoto);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = results.id;
                Intent intent = new Intent(context, DetilActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("movie_title", results.title);
                intent.putExtra("poster_path", results.backdrop_path);
                intent.putExtra("description", results.overview);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (mlist != null)
            return mlist.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvJudul;
        TextView tvDeskripsi;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFoto = (ImageView) itemView.findViewById(R.id.imageView);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDeskripsi);
            cardView = (CardView) itemView.findViewById(R.id.CardView);
        }
    }
    //public movieadapter(ArrayList<Movie> mlist)
    //{
    //this.mlist = mlist;
    //}
}
