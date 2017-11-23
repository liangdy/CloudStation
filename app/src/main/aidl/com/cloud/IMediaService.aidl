// IMediaService.aidl
package com.cloud;

import com.cloud.model.music.ContentBean;
import android.graphics.Bitmap;

// Declare any non-default types here with import statements

interface IMediaService {
    boolean play(int pos);
    boolean playById(int id);
    boolean rePlay();
    boolean pause();
    boolean prev();
    boolean next();
    long duration();
    long position();
    boolean seekTo(int progress);
    void refreshMusicList(in List<ContentBean> musicList);
    void getMusicList(out List<ContentBean> musicList);
    int getPlayState();
    int getPlayMode();
    void setPlayMode(int mode);
    void sendPlayStateBroadcast();
    void exit();
    int getCurMusicId();
    int getBufferProgress();
    void updateNotification(in Bitmap bitmap, String title, String name);
    void cancelNotification();
    ContentBean getCurMusic();
}