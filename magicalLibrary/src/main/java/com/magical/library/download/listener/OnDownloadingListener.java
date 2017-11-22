package com.magical.library.download.listener;

import com.magical.library.download.FileDownloadTask;

import java.io.File;

/**
 * Project: TShow
 * FileName: OnDownloadingListener.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public interface OnDownloadingListener {

    /**
     * 下载失败
     *
     * @param task Downdload task
     * @param errorType DownloadErrorType
     * @param msg 错误信息
     */
    public void onDownloadFailed(FileDownloadTask task, int errorType, String msg);

    /**
     * 下载成功
     *
     * @param task Download task
     * @param outFile 下载成功后的文件
     */
    public void onDownloadSucc(FileDownloadTask task, File outFile);

}
