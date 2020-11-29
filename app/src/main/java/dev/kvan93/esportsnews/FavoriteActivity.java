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
import androidx.room.Room;
import androidx.room.migration.Migration;

import static dev.kvan93.esportsnews.FavoritesDatabase.MIGRATION_1_2;
import static dev.kvan93.esportsnews.MainActivity.database;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private  RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);



        recyclerView = findViewById(R.id.recycler_favorites);
        layoutManager = new LinearLayoutManager(this);
        adapter = new FavoriteAdapter(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (adapter.getItemCount() == 0){
            //TODO set text "No favorites saved"
        }
        adapter.reload();
        Button clearAllButton = findViewById(R.id.clear_all_button);
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database.favoriteDao().clearAll();
                adapter.reload();
            }
        });
        Button HomeButton = findViewById(R.id.Home_button);
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.reload();
    }
}
