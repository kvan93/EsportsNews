package dev.kvan93.esportsnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import static dev.kvan93.esportsnews.FavoritesDatabase.MIGRATION_1_2;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private esportsNewsAdapter adapter;
    public static FavoritesDatabase database;
    // TODO Make a Loading Screen?



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        database = Room.databaseBuilder(getApplicationContext(), FavoritesDatabase.class, "favorites")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .build();

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new esportsNewsAdapter(getApplicationContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Favorite Button

        Button favoriteButton = findViewById(R.id.favoriteScreenButton);

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FavoriteActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }


}