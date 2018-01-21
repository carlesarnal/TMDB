package com.carlesarnal.themoviedb.app.toprated;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carlesarnal.themoviedb.api.model.Movie;
import com.carlesarnal.themoviedb.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by carles on 17/01/18.
 */

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.ViewHolder> {
    private List<Movie> movies;
    private Activity activity;
    private ItemClickListener itemClickListener;

    public TopRatedAdapter(List<Movie> movies, Activity activity, ItemClickListener itemClickListener) {
        this.movies = movies;
        this.activity = activity;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_rated_item_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Movie movie = movies.get(position);

        String fullImageUrl = getFullImageUrl(movie);
        if (!fullImageUrl.isEmpty()) {
            Glide.with(activity)
                    .load(fullImageUrl)
                    .centerCrop()
                    .crossFade().into(holder.imageView);
        }

        String popularity = getPopularityString(movie.popularity);
        holder.popularityTextView.setText(popularity);
        holder.titleTextView.setText(movie.title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(movie, holder.imageView);
            }
        });
    }

    @NonNull
    private String getFullImageUrl(Movie movie) {
        String imagePath = "";

        if (movie.posterPath != null && !movie.posterPath.isEmpty()) {
            imagePath = activity.getString(R.string.images_base_url) + movie.posterPath;
        } else {
            imagePath = activity.getString(R.string.images_base_url) + movie.backdropPath;
        }

        return imagePath;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void clear() {
        movies.clear();
    }

    public void addAll(List<Movie> movies) {
        this.movies.addAll(movies);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.popularityTextView)
        TextView popularityTextView;
        @BindView(R.id.titleTextView)
        TextView titleTextView;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

    }

    private String getPopularityString(float popularity) {
        java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#.#");
        return decimalFormat.format(popularity);
    }

    public interface ItemClickListener {

        void onItemClick(Movie movie, ImageView imageView);

    }
}
