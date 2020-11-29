package dev.kvan93.esportsnews;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;


@Dao
public interface FavoriteDao {

    @Query("INSERT INTO favorites (guidId, title, content, feedlabel, articleUrl) VALUES (:guidId,:title, :content, :feedlabel, :articleUrl)")
    void create(String guidId, String title, String content, String feedlabel, String articleUrl);

    @Query("Select id, guidId,title, content, feedlabel, articleUrl from favorites")
    List<Favorite> getAllFavorites();

    @Query("DELETE FROM favorites")
    void clearAll();

    @Query("DELETE FROM favorites WHERE guidId = :guidId")
    void delete(String guidId);

    @Query("SELECT count(id) FROM favorites")
    LiveData<Integer> getRowCount();

    @Query("Select count(guidID) from favorites where guidId= :guidId")
    Integer isFavorite(String guidId);

}
