package dev.kvan93.esportsnews;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import static dev.kvan93.esportsnews.MainActivity.database;


public class esportsNewsArticleAdapter extends RecyclerView.Adapter<esportsNewsArticleAdapter.esportsNewsArticleViewHolder> {

    private RequestQueue requestQueue;
    private String url;

    public SharedPreferences sP;
    private List<esportarticle> esportarticles;

    public class esportsNewsArticleViewHolder extends RecyclerView.ViewHolder {


        public TextView titleTextView;
        public LinearLayout containerView;
        public TextView contentTextView;
        public TextView feedLableTextView;
        public  TextView secretUrlView;

        public boolean isFavorite;
        public CheckBox checkBox;
        //TODO Make a Favorites section?

        esportsNewsArticleViewHolder(View view) {
            super(view);
            containerView = view.findViewById(R.id.esports_article_row);

            titleTextView = view.findViewById(R.id.esports_news_title);
            contentTextView = view.findViewById(R.id.esports_news_content);
            feedLableTextView = view.findViewById(R.id.esport_news_feedlabel);
            secretUrlView = view.findViewById(R.id.secret_url);
            checkBox = view.findViewById(R.id.favoritebutton);

            containerView.setOnClickListener(new View.OnClickListener() {
                private Intent openURL;

                @Override
                public void onClick(View v) {
                    openURL = new Intent(Intent.ACTION_VIEW, Uri.parse((String) secretUrlView.getText()));
                    v.getContext().startActivity(openURL);
                }
            });

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked = checkBox.isChecked();

                    esportarticle current = (esportarticle) containerView.getTag();
                    if (checked){
                        // sP.edit().putBoolean(current.getGuidId(),true).commit();
                        database.favoriteDao().create(
                                current.getGuidId(),
                                current.getTitle(),
                                current.getContent(),
                                current.getFeedlabel(),
                                current.getArticleUrl());
                    }
                    else {
                      //  sP.edit().putBoolean(current.getGuidId(),false).commit();
                        database.favoriteDao().delete(current.getGuidId());
                    }
                }
            });

        }
    }


    esportsNewsArticleAdapter(Context context,Integer appId){
        //sP = PreferenceManager.getDefaultSharedPreferences(context);

        requestQueue = Volley.newRequestQueue(context);
        esportarticles = new ArrayList<>();
        loadNewsItems(appId);

    }

    // TODO Other news sources then steam api? for fortnite and others?
    public void loadNewsItems(Integer appId) {
        url = "https://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=" + appId + "&count=20&maxlength=300&format=json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject appnews = response.getJSONObject("appnews");
                    JSONArray results = appnews.getJSONArray("newsitems");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject result = results.getJSONObject(i);
                        esportarticles.add(new esportarticle(
                                result.getString("gid"),
                                result.getString("title"),
                                // set HTML from the JSON result answer from --> https://stackoverflow.com/questions/2116162/how-to-display-html-in-textview
                                result.getString("contents"),
                                result.getString("feedlabel"),
                                result.getString("url")
                        ));

                    }

                    notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.e("kvan93", "Json error", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("kvan93", "esports article list error");
            }
        });

        requestQueue.add(request);
    }

    @NonNull
    @Override
    public esportsNewsArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.esports_news, parent,false);
        return new esportsNewsArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull esportsNewsArticleViewHolder holder, int position) {
        esportarticle current = esportarticles.get(position);
        holder.titleTextView.setText(current.getTitle());
        holder.contentTextView.setText(Html.fromHtml(current.getContent()));
        holder.feedLableTextView.setText(current.getFeedlabel());
        holder.secretUrlView.setText(current.getArticleUrl());
        holder.containerView.setTag(current);
        if(database.favoriteDao().isFavorite(current.getGuidId()) == 1) {
            holder.checkBox.setChecked(true);
        } else
        {
            holder.checkBox.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return esportarticles == null ? 0 : esportarticles.size();
    }
}
