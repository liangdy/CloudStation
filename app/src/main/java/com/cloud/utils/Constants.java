package com.cloud.utils;

import android.net.Uri;

import java.io.File;

/**
 * Project: CloudStation
 * FileName: Constants.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:14 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:14 PM
 * Remark:
 */
public class Constants {

    private Constants() {
        throw new Error("Do not need instantiate!");
    }

    public static class DIR {
        public static final String ROOT = "/CloudStation";
        public static final String CACHE = ROOT + File.separator + ".cache";
        public static final String TEMP = ROOT + File.separator + "temp";
        public static final String IMAGE = ROOT + File.separator + "image";
        public static final String LOG = ROOT + File.separator + "log";
    }

    //nav tag
    public static final String NAV_TAG = "cur_nav";

    /**
     * play service
     */
    // 播放状态
    public static final int MPS_NOFILE = -1; // 无音乐文件
    public static final int MPS_INVALID = 0; // 当前音乐文件无效
    public static final int MPS_PREPARE = 1; // 准备就绪
    public static final int MPS_PLAYING = 2; // 播放中
    public static final int MPS_PAUSE = 3; // 暂停
    // 播放模式
    public static final int MPM_LIST_LOOP_PLAY = 0; // 列表循环
    public static final int MPM_ORDER_PLAY = 1; // 顺序播放
    public static final int MPM_RANDOM_PLAY = 2; // 随机播放
    public static final int MPM_SINGLE_LOOP_PLAY = 3; // 单曲循环

    public static final String BROADCAST_NAME = "somebody_z.me.zuimusic.broadcast";

    public final static String KEY_MUSIC = "music";
    public static final String BROADCAST_SHAKE = "somebody_z.me.zuimusic.shake";
    public static final Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

    /**
     * play action
     */
    public static final String ACTION_PLAY = "com.cloud.action.play";
    public static final String ACTION_PAUSE = "com.cloud.action.pause";
    public static final String ACTION_NEXT = "com.cloud.action.next";
    public static final String ACTION_PREV = "com.cloud.action.prev";
    public static final String ACTION_PLAY_STATUE = "com.cloud.action.play.statue";

    /**
     * play mode
     */
    public static final int PLAY_MODE_LIST_LOOP = 0;//列表循环
    public static final int PLAY_MODE_ORDER = 1;//顺序播放
    public static final int PLAY_MODE_RANDOM = 2;//随机播放
    public static final int PLAY_MODE_SINGLE_LOOP = 3;//单曲循环

    /**
     * play statue
     */
    public static final int PLAY_STATUE_NO_FILE = -1; //无音乐文件
    public static final int PLAY_STATUE_INVALID = 0; //当前音乐文件无效
    public static final int PLAY_STATUE_PREPARE = 1; //准备就绪
    public static final int PLAY_STATUE_PLAYING = 2; //播放中
    public static final int PLAY_STATUE_PAUSE = 3; //暂停
    /**
     * play intent key
     */
    public static final String PLAY_STATE_NAME = "PLAY_STATE_NAME";
    public static final String PLAY_MUSIC_INDEX = "PLAY_MUSIC_INDEX";

    public static final String SERVICE_NAME = "com.cloud.service.AudioPlayService";
}
