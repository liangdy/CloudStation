package com.magical.library.upload.uploader;

import com.magical.library.upload.FileUploadInfo;
import com.magical.library.upload.listener.OnFileTransferredListener;

import java.io.IOException;

/**
 * Project: TShow
 * FileName: BaseUploader.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public abstract class BaseUploader {

    public abstract String upload(FileUploadInfo fileUploadInfo, OnFileTransferredListener fileTransferredListener) throws IOException;

}