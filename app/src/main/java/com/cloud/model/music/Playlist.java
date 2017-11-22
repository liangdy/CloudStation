package com.cloud.model.music;

/**
 * Project: CloudStation
 * FileName: Playlist.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/7/17 6:57 PM
 * Editor: ldy
 * Modify Date: 9/7/17 6:57 PM
 * Remark:
 */
public class Playlist {

    public final long id;
    public final String name;
    public final int songCount;
    public final String albumArt;
    public final String author;

    public Playlist() {
        this.id = -1;
        this.name = "";
        this.songCount = -1;
        this.albumArt = "";
        this.author = "";
    }

    public Playlist(long _id, String _name, int _songCount, String _albumArt, String author) {
        this.id = _id;
        this.name = _name;
        this.songCount = _songCount;
        this.albumArt = _albumArt;
        this.author = author;
    }
}
