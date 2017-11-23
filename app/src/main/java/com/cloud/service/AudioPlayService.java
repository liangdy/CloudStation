package com.cloud.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.widget.RemoteViews;

import com.cloud.CloudApplication;
import com.cloud.IMediaService;
import com.cloud.R;
import com.cloud.model.music.ContentBean;
import com.cloud.ui.music.play.PlayActivity;
import com.cloud.utils.Constants;
import com.magical.library.utils.NotificationUtils;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Project: CloudStation
 * FileName: AudioPlayService.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 11/23/17 3:30 PM
 * Editor: ldy
 * Modify Date: 11/23/17 3:30 PM
 * Remark:
 */
public class AudioPlayService extends IntentService {

    private static final String TAG = AudioPlayService.class.getSimpleName();

    private static final int AUDIO_PLAY_NOTIFICATION_ID = 0x001;

    private AudioPlayController mController;
    private ServiceStub mBinder = new ServiceStub(this);

    public AudioPlayService() {
        this("AudioPlayService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public AudioPlayService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mController = new AudioPlayController(this);
        registerBroadcast();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        
    }

    private void updateNotification(Bitmap bitmap, String title, String name) {
        Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews mRemoteViews = new RemoteViews(this.getPackageName(), R.layout.notification);
        Notification notification = new Notification();
        //TODO:change icon
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = title;
        notification.contentIntent = pi;
        notification.contentView = mRemoteViews;
        notification.flags |= Notification.FLAG_ONGOING_EVENT;

        if (bitmap != null) {
            mRemoteViews.setImageViewBitmap(R.id.image, bitmap);
        } else {
            //TODO:change icon
            mRemoteViews.setImageViewResource(R.id.image, R.drawable.ic_notification);
        }

        mRemoteViews.setTextViewText(R.id.title, title);
        mRemoteViews.setTextViewText(R.id.text, name);

        if (mController.getPlayState() == Constants.PLAY_STATUE_PAUSE) {
            mRemoteViews.setImageViewResource(R.id.iv_pause, R.drawable.note_btn_play);
            Intent playIntent = new Intent(Constants.ACTION_PLAY);
            PendingIntent playPIntent = PendingIntent.getBroadcast(this, 0, playIntent, 0);
            mRemoteViews.setOnClickPendingIntent(R.id.iv_pause, playPIntent);
        } else if (mController.getPlayState() == Constants.PLAY_STATUE_PLAYING) {
            mRemoteViews.setImageViewResource(R.id.iv_pause, R.drawable.note_btn_pause);
            Intent pauseIntent = new Intent(Constants.ACTION_PAUSE);
            PendingIntent pausePIntent = PendingIntent.getBroadcast(this, 0, pauseIntent, 0);
            mRemoteViews.setOnClickPendingIntent(R.id.iv_pause, pausePIntent);
        }
        Intent nextIntent = new Intent(Constants.ACTION_NEXT);
        PendingIntent nextPIntent = PendingIntent.getBroadcast(this, 0, nextIntent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.iv_next, nextPIntent);

        startForeground(AUDIO_PLAY_NOTIFICATION_ID, notification); // 开启前台服务
    }

    public void cancelNotification() {
        stopForeground(true);
        NotificationUtils.cancel(AUDIO_PLAY_NOTIFICATION_ID, this);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if (TextUtils.equals(action, Constants.ACTION_PLAY)) {
                //TODO:action play
            } else if (TextUtils.equals(action, Constants.ACTION_PAUSE)) {
                //TODO:action pasue
            } else if (TextUtils.equals(action, Constants.ACTION_NEXT)) {
                //TODO:action next
            } else if (TextUtils.equals(action, Constants.ACTION_PREV)) {
                //TODO:action prev
            }
        }
    };

    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ACTION_PLAY);
        filter.addAction(Constants.ACTION_PAUSE);
        filter.addAction(Constants.ACTION_NEXT);
        filter.addAction(Constants.ACTION_PREV);
        LocalBroadcastManager.getInstance(CloudApplication.getApplication()).registerReceiver(receiver, filter);
    }

    private void unregisterBroadcast() {
        if (receiver != null) {
            LocalBroadcastManager.getInstance(CloudApplication.getApplication()).unregisterReceiver(receiver);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
    }

    private class ServiceStub extends IMediaService.Stub {

        private WeakReference<AudioPlayService> mService;

        public ServiceStub(AudioPlayService service) {
            this.mService = new WeakReference<>(service);
        }

        @Override
        public boolean pause() throws RemoteException {
            return mService.get().mController.pause();
        }

        @Override
        public boolean prev() throws RemoteException {
            return mService.get().mController.prev();
        }

        @Override
        public boolean next() throws RemoteException {
            return mService.get().mController.next();
        }

        @Override
        public boolean play(int pos) throws RemoteException {
            return mService.get().mController.play(pos);
        }

        @Override
        public long duration() throws RemoteException {
            return mService.get().mController.duration();
        }

        @Override
        public long position() throws RemoteException {
            return mService.get().mController.position();
        }

        @Override
        public boolean seekTo(int progress) throws RemoteException {
            return mService.get().mController.seekTo(progress);
        }

        @Override
        public void refreshMusicList(List<ContentBean> musicList) throws RemoteException {
            mService.get().mController.refreshMusicList(musicList);
        }

        @Override
        public void getMusicList(List<ContentBean> musicList) throws RemoteException {
            List<ContentBean> music = mService.get().mController.getMusicList();
            for (ContentBean m : music) {
                musicList.add(m);
            }
        }

        @Override
        public int getPlayState() throws RemoteException {
            return mService.get().mController.getPlayState();
        }

        @Override
        public int getPlayMode() throws RemoteException {
            return mService.get().mController.getPlayMode();
        }

        @Override
        public void setPlayMode(int mode) throws RemoteException {
            mService.get().mController.setPlayMode(mode);
        }

        @Override
        public void sendPlayStateBroadcast() throws RemoteException {
            mService.get().mController.sendPlayStateBroadcast();
        }

        @Override
        public void exit() throws RemoteException {
            cancelNotification();
            stopSelf();
            mService.get().mController.exit();
        }

        @Override
        public boolean rePlay() throws RemoteException {
            return mService.get().mController.replay();
        }

        @Override
        public int getCurMusicId() throws RemoteException {
            return mService.get().mController.getCurMusicId();
        }

        @Override
        public int getBufferProgress() throws RemoteException {
            return mService.get().mController.getBufferProgress();
        }

        @Override
        public void updateNotification(Bitmap bitmap, String title, String name) throws RemoteException {
            mService.get().updateNotification(bitmap, title, name);
        }

        @Override
        public void cancelNotification() throws RemoteException {
            mService.get().cancelNotification();
        }

        @Override
        public boolean playById(int id) throws RemoteException {
            return mService.get().mController.playById(id);
        }

        @Override
        public ContentBean getCurMusic() throws RemoteException {
            return mService.get().mController.getCurMusic();
        }
    }
}
