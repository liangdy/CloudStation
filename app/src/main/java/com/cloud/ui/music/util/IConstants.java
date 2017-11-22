package com.cloud.ui.music.util;

/**
 * Project: CloudStation
 * FileName: IConstants.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/15/17 2:57 PM
 * Editor: ldy
 * Modify Date: 8/15/17 2:57 PM
 * Remark:
 */
public interface IConstants {

    String MUSIC_COUNT_CHANGED = "com.wm.music.musiccountchanged";
    String PLAYLIST_ITEM_MOVED = "com.wm.music.mmoved";
    String PLAYLIST_COUNT_CHANGED = "com.wm.music.playlistcountchanged";
    String CHANGE_THEME = "com.wm.music.themechange";
    String EMPTY_LIST = "com.wm.music.emptyplaylist";
    String PACKAGE = "com.wm.music";
    int MUSICOVERFLOW = 0;
    int ARTISTOVERFLOW = 1;
    int ALBUMOVERFLOW = 2;
    int FOLDEROVERFLOW = 3;

    //歌手和专辑列表点击都会进入MyMusic 此时要传递参数表明是从哪里进入的
    int START_FROM_ARTIST = 1;
    int START_FROM_ALBUM = 2;
    int START_FROM_LOCAL = 3;
    int START_FROM_FOLDER = 4;
    int START_FROM_FAVORITE = 5;

    int FAV_PLAYLIST = 10;


    String NAVIGATE_NOWPLAYING = "navigate_nowplaying";
}
