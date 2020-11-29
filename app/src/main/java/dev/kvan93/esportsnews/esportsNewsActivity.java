package dev.kvan93.esportsnews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class esportsNewsActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private esportsNewsArticleAdapter adapter;
    private RecyclerView recyclerView;
    public String articleGuid;
    private int appID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recycler_news);
        appID = getIntent().getIntExtra("appid",0);
        adapter = new esportsNewsArticleAdapter(getApplicationContext(), appID);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        Button favoriteButton = findViewById(R.id.favoriteScreenButton);

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FavoriteActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        Button HomeButton = findViewById(R.id.Home_button);

        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });


    }





}
