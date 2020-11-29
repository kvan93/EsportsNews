package dev.kvan93.esportsnews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


import static dev.kvan93.esportsnews.MainActivity.database;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.favoriteViewHolder> {

    public static class favoriteViewHolder extends RecyclerView.ViewHolder{
        public TextView favtitleTextView;
        public LinearLayout containerView;
        public TextView favcontentTextView;
        public TextView favfeedLableTextView;
        public  TextView favsecretUrlView;
        public  TextView favsecretguidId;
        public Button favSingleDeleteButton;


        favoriteViewHolder(View view){
            super(view);
            containerView = view.findViewById(R.id.favorites_row);

            favtitleTextView = view.findViewById(R.id.favorites_news_title);
            favcontentTextView = view.findViewById(R.id.favorites_news_content);
            favfeedLableTextView = view.findViewById(R.id.favorites_news_feedlabel);
            favsecretUrlView = view.findViewById(R.id.favorites_secret_url);
            favsecretguidId = view.findViewById(R.id.guidId);
            favSingleDeleteButton = view.findViewById(R.id.single_favorite_delete);

            if (containerView != null){
                containerView.setOnClickListener(new View.OnClickListener() {
                    private Intent openURL;

                    @Override
                    public void onClick(View v) {
                        openURL = new Intent(Intent.ACTION_VIEW, Uri.parse((String) favsecretUrlView.getText()));
                        v.getContext().startActivity(openURL);
                    }
                });
            }

            favSingleDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Favorite current = (Favorite) containerView.getTag();
                    database.favoriteDao().delete(current.guidId);
                    Intent intent = new Intent(v.getContext(),FavoriteActivity.class);
                    v.getContext().startActivity(intent);
                }
            });

        }
    }

    private List<Favorite> favorites = new ArrayList<>();

    FavoriteAdapter(Context context){
        reload();
    }

    @NonNull
    @Override
    public favoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.favorites_row, parent, false);
        return new favoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull favoriteViewHolder holder, int position) {
        Favorite current = favorites.get(position);
        holder.favtitleTextView.setText(current.title);
        holder.favcontentTextView.setText(Html.fromHtml(current.content));
        holder.favfeedLableTextView.setText(current.feedlabel);
        holder.favsecretUrlView.setText(current.articleUrl);
        holder.containerView.setTag(current);
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public void reload(){
        favorites = database.favoriteDao().getAllFavorites();
        notifyDataSetChanged();
    }
}
