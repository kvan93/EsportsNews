package dev.kvan93.esportsnews;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "favorites")
public class Favorite {
    @PrimaryKey
    public int id;

    @ColumnInfo(name="guidId")
    public String guidId;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "feedlabel")
    public String feedlabel;

    @ColumnInfo(name = "articleUrl")
    public String articleUrl;

}
