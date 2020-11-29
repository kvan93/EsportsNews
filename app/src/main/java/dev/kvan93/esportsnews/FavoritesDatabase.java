package dev.kvan93.esportsnews;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Favorite.class}, version =  2)
public  abstract class FavoritesDatabase extends RoomDatabase {
    public abstract FavoriteDao favoriteDao();

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE favorites ADD COLUMN guidId TEXT");
        }
    };
}
