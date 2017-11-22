package com.magical.library.upload.listener;


import com.magical.library.upload.FileUploadInfo;

/**
 * Project: TShow
 * FileName: OnUploadListener.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public interface OnUploadListener {

    /**
     * 上传失败
     *
     * @param uploadData
     * @param errorType
     * @param msg
     */
    public void onError(FileUploadInfo uploadData, int errorType, String msg);

    /**
     * 上传成功
     *
     * @param uploadData
     * @param data 数据返回的解析结果
     */
    public void onSuccess(FileUploadInfo uploadData, Object data);

}