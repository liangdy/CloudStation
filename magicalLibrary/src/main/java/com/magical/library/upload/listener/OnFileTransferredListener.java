package com.magical.library.upload.listener;

/**
 * Project: TShow
 * FileName: OnFileTransferredListener.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public interface OnFileTransferredListener {

    /**
     *
     * @param transferred 已经上传的大小
     * @param totalSize 文件总大小
     */
    public void transferred(long transferred, long totalSize);

}