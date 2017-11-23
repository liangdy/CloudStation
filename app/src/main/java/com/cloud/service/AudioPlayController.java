package com.cloud.service;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;

import com.cloud.model.music.ContentBean;
import com.cloud.utils.Constants;
import com.cloud.utils.LogUtils;
import com.cloud.utils.MusicUtils;
import com.magical.library.utils.MagicalLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Project: CloudStation
 * FileName: AudioPlayMannager.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 11/23/17 3:44 PM
 * Editor: ldy
 * Modify Date: 11/23/17 3:44 PM
 * Remark:
 */
public class AudioPlayController implements IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener,
        IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnSeekCompleteListener {

    private static final String TAG = AudioPlayService.class.getSimpleName();

    private Context mContext;
    private Random mRandom;
    private IjkMediaPlayer mMediaPlayer;

    private int mPlayMode;
    private int mPlayState;
    private int mCurPlayIndex;
    private int mCurMusicId;
    private int bufferProgress = 0;

    private List<ContentBean> mMusicList = new ArrayList<>();

    private ContentBean mCurMusic;

    public AudioPlayController(Context context) {
        mContext = context;
        mMediaPlayer = new IjkMediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayMode = Constants.PLAY_MODE_LIST_LOOP;
        mPlayState = Constants.PLAY_STATUE_NO_FILE;
        mCurPlayIndex = -1;
        mCurMusicId = -1;
        mRandom = new Random();
        mRandom.setSeed(System.currentTimeMillis());
        initListener();
    }

    private void initListener() {
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnBufferingUpdateListener(this);
        mMediaPlayer.setOnSeekCompleteListener(this);
    }

    public boolean play(int pos) {
        if (mCurPlayIndex == pos) {
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.start();
                mPlayState = Constants.PLAY_STATUE_PREPARE;
                sendPlayStateBroadcast();
            } else {
                pause();
            }
            return true;
        }
        if (!prepare(pos)) {
            return false;
        }
        return replay();
    }

    public boolean playById(long id) {
        int position = MusicUtils.seekPosInListById(mMusicList, id);
        mCurPlayIndex = position;
        if (mCurMusicId == id) {
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.start();
                mPlayState = Constants.PLAY_STATUE_PLAYING;
                sendPlayStateBroadcast();
            } else {
                pause();
            }
            return true;
        }
        if (!prepare(position)) {
            return false;
        }
        return replay();
    }

    public boolean replay() {
        if (mPlayState == Constants.PLAY_STATUE_INVALID || mPlayState == Constants.PLAY_STATUE_NO_FILE) {
            return false;
        }
        mMediaPlayer.start();
        mPlayState = Constants.PLAY_STATUE_PLAYING;
        sendPlayStateBroadcast();
        return true;
    }

    public boolean prepare(final int pos) {
        mCurPlayIndex = pos;
        mMediaPlayer.reset();
        String path = mMusicList.get(pos).localUrl;
        try {
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepareAsync();
            mPlayState = Constants.PLAY_STATUE_PREPARE;
            //TODO: play record action
        } catch (IOException e) {
            e.printStackTrace();
            MagicalLog.e(TAG, e.toString());
            LogUtils.saveLog(null, e);
            mPlayState = Constants.PLAY_STATUE_INVALID;
            if (pos < mMusicList.size()) {
                playById(Long.valueOf(mMusicList.get(pos + 1).song_id));
            }
            return false;
        }
        sendPlayStateBroadcast();
        return true;
    }

    public boolean pause() {
        if (mPlayState != Constants.PLAY_STATUE_PLAYING) {
            return false;
        }
        mMediaPlayer.pause();
        mPlayState = Constants.PLAY_STATUE_PAUSE;
        sendPlayStateBroadcast();
        return true;
    }

    public boolean prev() {
        if (mPlayState == Constants.PLAY_STATUE_NO_FILE) {
            return false;
        }
        mCurPlayIndex--;
        mCurPlayIndex = reviseIndex(mCurPlayIndex);
        if (!prepare(mCurPlayIndex)) {
            return false;
        }
        return replay();
    }

    public boolean next() {
        if (mPlayState == Constants.PLAY_STATUE_NO_FILE) {
            return false;
        }
        mCurPlayIndex++;
        mCurPlayIndex = reviseIndex(mCurPlayIndex);
        if (!prepare(mCurPlayIndex)) {
            return false;
        }
        return replay();
    }

    private int reviseIndex(int index) {
        if (index < 0) {
            index = mMusicList.size() - 1;
        }
        if (index >= mMusicList.size()) {
            index = 0;
        }
        return index;
    }

    public long position() {
        if (mPlayState == Constants.PLAY_STATUE_PLAYING || mPlayState == Constants.PLAY_STATUE_PAUSE) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public long duration() {
        if (mPlayState == Constants.PLAY_STATUE_INVALID || mPlayState == Constants.PLAY_STATUE_NO_FILE) {
            return 0;
        }
        return mMediaPlayer.getDuration();
    }

    public boolean seekTo(int progress) {
        if (mPlayState == Constants.PLAY_STATUE_INVALID || mPlayState == Constants.PLAY_STATUE_NO_FILE) {
            return false;
        }
        int pro = reviseSeekValue(progress);
        long time = mMediaPlayer.getDuration();
        long curTime = (int) ((float) pro / 100 * time);
        mMediaPlayer.seekTo(curTime);
        return true;
    }

    private int reviseSeekValue(int progress) {
        if (progress < 0) {
            progress = 0;
        } else if (progress > 100) {
            progress = 100;
        }
        return progress;
    }

    public void refreshMusicList(List<ContentBean> musicList) {
        mMusicList.clear();
        mMusicList.addAll(musicList);
        if (mMusicList.size() == 0) {
            mPlayState = Constants.PLAY_STATUE_NO_FILE;
            mCurPlayIndex = -1;
            return;
        }
    }

    public int getCurMusicId() {
        return mCurMusicId;
    }

    public ContentBean getCurMusic() {
        return mCurMusic;
    }

    public int getPlayState() {
        return mPlayState;
    }

    public int getPlayMode() {
        return mPlayMode;
    }

    public void setPlayMode(int mode) {
        switch (mode) {
            case Constants.PLAY_MODE_LIST_LOOP:
            case Constants.PLAY_MODE_ORDER:
            case Constants.PLAY_MODE_RANDOM:
            case Constants.PLAY_MODE_SINGLE_LOOP:
                mPlayMode = mode;
                break;
        }
    }

    public List<ContentBean> getMusicList() {
        return mMusicList;
    }

    private int getRandomIndex() {
        int size = mMusicList.size();
        if (size == 0) {
            return -1;
        }
        return Math.abs(mRandom.nextInt() % size);
    }

    public void exit() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mCurPlayIndex = -1;
        mMusicList.clear();
    }

    public void sendPlayStateBroadcast() {
        Intent intent = new Intent(Constants.ACTION_PLAY_STATUE);
        intent.putExtra(Constants.PLAY_STATE_NAME, mPlayState);
        intent.putExtra(Constants.PLAY_MUSIC_INDEX, mCurPlayIndex);
        if (mPlayState != Constants.PLAY_STATUE_NO_FILE && mMusicList.size() > 0) {
            Bundle bundle = new Bundle();
            mCurMusic = mMusicList.get(mCurPlayIndex);
            mCurMusicId = Integer.valueOf(mCurMusic.song_id);
            bundle.putParcelable(Constants.KEY_MUSIC, mCurMusic);
            intent.putExtra(Constants.KEY_MUSIC, bundle);
        }
        mContext.sendBroadcast(intent);
    }

    public int getBufferProgress() {
        return bufferProgress;
    }

    @Override
    public void onCompletion(IMediaPlayer mp) {
        switch (mPlayMode) {
            case Constants.PLAY_MODE_LIST_LOOP:
                next();
                break;
            case Constants.PLAY_MODE_ORDER:
                if (mCurPlayIndex != mMusicList.size() - 1) {
                    next();
                } else {
                    prepare(mCurPlayIndex);
                }
                break;
            case Constants.PLAY_MODE_RANDOM:
                int index = getRandomIndex();
                if (index != -1) {
                    mCurPlayIndex = index;
                } else {
                    mCurPlayIndex = 0;
                }
                if (prepare(mCurPlayIndex)) {
                    replay();
                }
                break;
            case Constants.PLAY_MODE_SINGLE_LOOP:
                play(mCurPlayIndex);
                break;
        }
    }

    @Override
    public boolean onError(IMediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer mp, int percent) {
        bufferProgress = percent;
    }

    @Override
    public void onSeekComplete(IMediaPlayer mp) {

    }
}
