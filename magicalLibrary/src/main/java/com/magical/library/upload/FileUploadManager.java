package com.magical.library.upload;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.magical.library.upload.listener.OnUploadListener;
import com.magical.library.upload.listener.OnUploadProgressListener;
import com.magical.library.upload.progressaware.ProgressAware;
import com.magical.library.upload.uploader.UploadOptions;

import java.util.Map;

/**
 * Project: TShow
 * FileName: FileUploadManager.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class FileUploadManager {

    private static volatile FileUploadManager INSTANCE;


    public static FileUploadManager getInstance() {
        if (INSTANCE == null) {
            synchronized (FileUploadManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FileUploadManager();
                }
            }
        }
        return INSTANCE;
    }


    private FileUploadConfiguration mFileUploadConfiguration;
    private FileUploadEngine mFileUploadEngine;
    private Handler mHandler;


    private FileUploadManager() {
        mHandler = new Handler(Looper.getMainLooper());
    }


    /**
     * 初始化
     */
    public synchronized void init(FileUploadConfiguration fileUploadConfiguration) {
        if (fileUploadConfiguration == null) {
            throw new IllegalArgumentException("FileUploadConfiguration can not be null.");
        }
        mFileUploadConfiguration = fileUploadConfiguration;
        mFileUploadEngine = new FileUploadEngine(fileUploadConfiguration);
    }


    /**
     * 检查是否初始化过
     */
    private void checkConfiguration() {
        if (mFileUploadConfiguration == null) {
            throw new IllegalStateException("Please call init() before use.");
        }
    }


    public void uploadFile(Map<String, String> paramMap, Map<String, String> headers, String id, String name, String filePath, String mimeType, String url, OnUploadListener apiCallback) {
        uploadFile(paramMap, headers, id, name, filePath, mimeType, url, apiCallback, null);
    }


    public void uploadFile(Map<String, String> paramMap, Map<String, String> headers, String id, String name, String filePath, String mimeType, String url, OnUploadListener apiCallback, UploadOptions options) {
        uploadFile(paramMap, headers, id, name, filePath, mimeType, url, apiCallback, null, options);
    }


    public void uploadFile(Map<String, String> paramMap, Map<String, String> headers, String id, String name, String filePath, String mimeType, String url, OnUploadListener apiCallback, OnUploadProgressListener uploadProgressListener, UploadOptions options) {
        uploadFile(null, paramMap, headers, id, name, filePath, mimeType, url, apiCallback, options);
    }


    public void uploadFile(ProgressAware progressAware, Map<String, String> paramMap, Map<String, String> headers, String id, String name, String filePath, String mimeType, String url, OnUploadListener apiCallback, UploadOptions options) {
        uploadFile(progressAware, paramMap, headers, id, name, filePath, mimeType, url, apiCallback, null, options);
    }


    /**
     * 提交上传
     */
    public void uploadFile(ProgressAware progressAware, Map<String, String> paramMap, Map<String, String> headers, String id, String name, String filePath, String mimeType, String url, OnUploadListener apiCallback, OnUploadProgressListener uploadProgressListener, UploadOptions options) {
        checkConfiguration();

        if (progressAware != null) {
            mFileUploadEngine.prepareUpdateProgressTaskFor(progressAware, id);
        }
        //是否上传任务已经存在，如果已经存在，则返回
        if (checkUploadTaskExistsAndResetProgressAware(id, filePath, progressAware)) {
            return;
        }
        FileUploadInfo fileUploadInfo = createFileUploadInfo(paramMap, headers, id, name, filePath, mimeType, url, apiCallback, uploadProgressListener, options);
        FileUploadTask fileUploadTask = new FileUploadTask(mFileUploadEngine, fileUploadInfo, progressAware, mHandler);
        mFileUploadEngine.submit(fileUploadTask);
    }


    public Object uploadFileSync(Map<String, String> paramMap, Map<String, String> headers, String id, String name, String filePath, String mimeType, String url) {
        return uploadFileSync(paramMap, headers, id, name, filePath, mimeType, url, null);
    }


    public Object uploadFileSync(Map<String, String> paramMap, Map<String, String> headers, String id, String name, String filePath, String mimeType, String url, UploadOptions options) {
        return uploadFileSync(null, paramMap, headers, id, name, filePath, mimeType, url, options);
    }


    public Object uploadFileSync(ProgressAware progressAware, Map<String, String> paramMap, Map<String, String> headers, String id, String name, String filePath, String mimeType, String url, UploadOptions options) {
        return uploadFileSync(progressAware, paramMap, headers, id, name, filePath, mimeType, url, null, options);
    }


    /**
     * 同步上传文件
     *
     * @return 根据BaseResponseParser解析http response返回的数据，默认是http请求返回的String，为null则表示上传失败了
     */
    public Object uploadFileSync(ProgressAware progressAware, Map<String, String> paramMap, Map<String, String> headers, String id, String name, String filePath, String mimeType, String url, OnUploadProgressListener uploadProgressListener, UploadOptions options) {
        checkConfiguration();
        if (progressAware != null) {
            mFileUploadEngine.prepareUpdateProgressTaskFor(progressAware, id);
        }

        SyncUploadListener listener = new SyncUploadListener();
        FileUploadInfo fileUploadInfo = createFileUploadInfo(paramMap, headers, id, name, filePath, mimeType, url, listener, uploadProgressListener, options);
        FileUploadTask fileUploadTask = new FileUploadTask(mFileUploadEngine, fileUploadInfo, progressAware, mHandler);
        fileUploadTask.setSyncLoading(true);
        fileUploadTask.run();
        return listener.getResult();
    }


    /**
     * 创建文件上传信息
     *
     * @param paramMap 表单参数
     * @param filePath 文件路径
     * @param mimeType 例如：image/*
     * @param apiCallback 回调
     * @param uploadProgressListener 上传进度监听
     */
    private FileUploadInfo createFileUploadInfo(Map<String, String> paramMap, Map<String, String> headers, String id, String name, String filePath, String mimeType, String url, OnUploadListener apiCallback, OnUploadProgressListener uploadProgressListener, UploadOptions options) {
        FileUploadInfo fileUploadInfo = new FileUploadInfo(paramMap, headers, id, name, filePath, mimeType, url, apiCallback, uploadProgressListener, options);
        return fileUploadInfo;
    }


    /**
     * 检查上传任务是否已经存在，根据"id，文件路径"判断是否是相同的任务
     */
    private boolean checkUploadTaskExistsAndResetProgressAware(String id, String filePath, ProgressAware progressAware) {
        boolean isExists = mFileUploadEngine
                .isTaskExists(id, filePath, progressAware);
        return isExists;
    }


    /**
     * 获取任务数
     *
     * @param mimeType 上传文件的类型例如图片：image/*，为null则取全部的
     */
    public int getTaskCount(String mimeType) {
        return mFileUploadEngine.getTaskCount(mimeType);
    }


    /**
     * 更新已有上传任务的进度，如果没有则不显示
     */
    public void updateProgress(String id, String filePath, ProgressAware progressAware) {
        if (progressAware == null) {
            return;
        }
        boolean isExists = mFileUploadEngine
                .isTaskExists(id, filePath, progressAware);
        //如果不存在
        if (!isExists) {
            progressAware.setVisibility(View.GONE);
        } else {
            mFileUploadEngine.prepareUpdateProgressTaskFor(progressAware, id);
        }
    }


    /**
     * 更新已有上传任务的进度，如果没有则显示默认进度值
     *
     * @param defProgress 默认进度 0-100
     */
    public void updateProgress(String id, String filePath, ProgressAware progressAware, int defProgress) {
        if (progressAware == null) {
            return;
        }
        boolean isExists = mFileUploadEngine
                .isTaskExists(id, filePath, progressAware);
        //如果不存在
        if (!isExists) {
            progressAware.setProgress(defProgress);
        } else {
            mFileUploadEngine.prepareUpdateProgressTaskFor(progressAware, id);
        }
    }


    private class SyncUploadListener implements OnUploadListener {

        private Object result;


        @Override
        public void onError(FileUploadInfo uploadData, int errorType, String msg) {
        }


        @Override
        public void onSuccess(FileUploadInfo uploadData, Object data) {
            this.result = data;
        }


        public Object getResult() {
            return result;
        }
    }
}