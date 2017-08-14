package com.example.tomy.entregable5.Model.POJO;

/**
 * Created by tomy on 17/06/17.
 */

public class Track {
    private Integer albumId;
    private Integer id;
    private String title;
    private String thumbnailUrl;

    public Track(Integer albumId, Integer id, String title, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
