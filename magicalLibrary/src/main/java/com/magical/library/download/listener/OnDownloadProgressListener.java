package com.magical.library.download.listener;


import com.magical.library.download.FileDownloadTask;

/**
 * Project: TShow
 * FileName: OnDownloadProgressListener.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public interface OnDownloadProgressListener {

    /**
     * @param downloadInfo 下载信息
     * @param current      Downloaded size in bytes.
     * @param totalSize    Total size in bytes.
     */
    public void onProgressUpdate(FileDownloadTask downloadInfo, long current, long totalSize);

}
