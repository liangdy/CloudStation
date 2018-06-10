package com.cloud.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;

import com.cloud.CloudApplication;
import com.cloud.IMediaService;
import com.cloud.model.music.ContentBean;
import com.cloud.utils.Constants;
import com.cloud.utils.ServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: CloudStation
 * FileName: AudioPlayServiceManager.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 11/22/17 4:10 PM
 * Editor: ldy
 * Modify Date: 11/22/17 4:10 PM
 * Remark:
 */
public class AudioPlayServiceManager {

    public IMediaService mService;

    private Context mContext;
    private ServiceConnection mConn;
    private IOnServiceConnectComplete mIOnServiceConnectComplete;

    public static AudioPlayServiceManager getInstance() {
        return Instance.instance;
    }

    public AudioPlayServiceManager(Context mContext) {
        this.mContext = mContext;
        initConn();
    }

    private AudioPlayServiceManager() {
        mContext = CloudApplication.getApplication();
    }

    public void initConn() {
        System.out.println("init connect");
        mConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mService = IMediaService.Stub.asInterface(iBinder);
                if (mService != null && mIOnServiceConnectComplete != null) {
                    mIOnServiceConnectComplete.onServiceConnectComplete(mService);
                }
                //设置死亡代理
                try {
                    iBinder.linkToDeath(mDeathRecipient, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                System.out.println("connect succeed");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
            }
        };
    }

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (mService == null) {
                return;
            }
            mService.asBinder().unlinkToDeath(mDeathRecipient, 0);
            mService = null;
            //retry connect
            connectService();
        }
    };

    public void connectService() {
        Intent intent = new Intent(Constants.SERVICE_NAME);
        Intent serviceIntent = new Intent(ServiceUtil.createExplicitFromImplicitIntent(mContext, intent));
        mContext.bindService(serviceIntent, mConn, Context.BIND_AUTO_CREATE);
    }

    public void disConnectService() {
        mContext.unbindService(mConn);
        mContext.stopService(new Intent(Constants.SERVICE_NAME));
    }

    public void refreshMusicList(List<ContentBean> musicList) {
        if (musicList != null && mService != null) {
            try {
                mService.refreshMusicList(musicList);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public List<ContentBean> getMusicList() {
        List<ContentBean> musicList = new ArrayList<>();
        try {
            if (mService != null) {
                mService.getMusicList(musicList);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return musicList;
    }

    public void play(int pos) {
        if (mService != null) {
            try {
                mService.play(pos);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void playById(int id) {
        if (mService != null) {
            try {
                mService.playById(id);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void rePlay() {
        if (mService != null) {
            try {
                mService.rePlay();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void pause() {
        if (mService != null) {
            try {
                mService.pause();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void prev() {
        if (mService != null) {
            try {
                mService.prev();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void next() {
        if (mService != null) {
            try {
                mService.next();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean seekTo(int progress) {
        if (mService != null) {
            try {
                return mService.seekTo(progress);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public long position() {
        if (mService != null) {
            try {
                return mService.position();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public long duration() {
        if (mService != null) {
            try {
                return mService.duration();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int getPlayState() {
        if (mService != null) {
            try {
                int mode = mService.getPlayState();
                return mService.getPlayState();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void setPlayMode(int mode) {
        if (mService != null) {
            try {
                mService.setPlayMode(mode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public int getPlayMode() {
        if (mService != null) {
            try {
                return mService.getPlayMode();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int getCurMusicId() {
        if (mService != null) {
            try {
                return mService.getCurMusicId();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public ContentBean getCurMusic() {
        if (mService != null) {
            try {
                return mService.getCurMusic();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int getBufferProgress() {
        try {
            return mService.getBufferProgress();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void sendBroadcast() {
        if (mService != null) {
            try {
                mService.sendPlayStateBroadcast();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void exit() {
        if (mService != null) {
            try {
                mService.exit();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mContext.unbindService(mConn);
        mContext.stopService(new Intent(Constants.SERVICE_NAME));
    }

    public void updateNotification(Bitmap bitmap, String title, String name) {
        try {
            mService.updateNotification(bitmap, title, name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void cancelNotification() {
        try {
            mService.cancelNotification();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setOnServiceConnectComplete(IOnServiceConnectComplete IServiceConnect) {
        mIOnServiceConnectComplete = IServiceConnect;
    }

    private static class Instance {
        private static AudioPlayServiceManager instance = new AudioPlayServiceManager();
    }
}
