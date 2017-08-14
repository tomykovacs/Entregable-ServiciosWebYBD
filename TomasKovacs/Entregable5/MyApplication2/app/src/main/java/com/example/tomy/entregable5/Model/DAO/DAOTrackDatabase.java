package com.example.tomy.entregable5.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tomy.entregable5.Model.POJO.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dh1 on 19/06/17.
 */

public class DAOTrackDatabase extends DatabaseHelper {

    public static final String TABLE_NAME = "Tracks";
    public static final String COLUMN_ALBUMID = "album_id";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_IMAGE = "image";

    public DAOTrackDatabase(Context context) {
        super(context);
    }

    public void addTrack(Track track) {
        if (!isTrackInDB(track.getId())) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ALBUMID, track.getAlbumId());
            values.put(COLUMN_ID, track.getId());
            values.put(COLUMN_TITLE, track.getTitle());
            values.put(COLUMN_IMAGE, track.getThumbnailUrl());
            db.insert(TABLE_NAME, null, values);
            db.close();
        }
    }

    public void addTrackList(List<Track> trackList) {
        for (Track track : trackList) {
            addTrack(track);
        }
    }

    public List<Track> getTrackListFromDatabase() {
        List<Track> trackList = new ArrayList<>();

        Integer albumId;
        Integer id;
        String title;
        String image;

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            albumId = cursor.getInt(cursor.getColumnIndex(COLUMN_ALBUMID));
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
            image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
            trackList.add(new Track(albumId, id, title, image));
        }
        cursor.close();
        db.close();

        return trackList;
    }

    public Boolean isTrackInDB(Integer id) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + "'" + id + "'";
        Cursor cursor = db.rawQuery(query, null);
        Boolean isTrack = cursor.moveToNext();
        cursor.close();
        db.close();
        return isTrack;
    }
}
