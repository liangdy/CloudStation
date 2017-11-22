package com.magical.library.upload;

import com.magical.library.upload.listener.OnUploadListener;
import com.magical.library.upload.listener.OnUploadProgressListener;
import com.magical.library.upload.uploader.UploadOptions;

import java.util.Map;

/**
 * Project: TShow
 * FileName: FileUploadInfo.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class FileUploadInfo {

    private Map<String, String> formParamMap;
    private Map<String, String> headers;

    private String id;
    private String name;
    private String filePath;             //要上传的文件路径
    private String mimeType;
    private String url;

    private OnUploadListener apiCallback;
    private OnUploadProgressListener progressListener;

    private UploadOptions uploadOptions;

    private String preProcessedFile;     //上传前对文件预处理后，生成的临时文件


    public FileUploadInfo(Map<String, String> formParamMap, Map<String, String> headers, String id, String name, String filePath, String mimeType, String url, OnUploadListener apiCallback, OnUploadProgressListener progressListener, UploadOptions uploadOptions) {
        this.formParamMap = formParamMap;
        this.headers = headers;
        this.id = id;
        this.name = name;
        this.filePath = filePath;
        this.mimeType = mimeType;
        this.url = url;
        this.apiCallback = apiCallback;
        this.progressListener = progressListener;
        this.uploadOptions = uploadOptions;
    }


    public String getOriginalFilePath() {
        return filePath;
    }


    public String getUploadFilePath() {
        if (preProcessedFile != null && !preProcessedFile.trim().equals("")) {
            return preProcessedFile;
        }
        return filePath;
    }


    public OnUploadListener getApiCallback() {
        return apiCallback;
    }


    public Map<String, String> getFormParamMap() {
        return formParamMap;
    }


    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getMimeType() {
        return mimeType;
    }


    public void setPreProcessedFile(String preProcessedFile) {
        this.preProcessedFile = preProcessedFile;
    }


    public OnUploadProgressListener getProgressListener() {
        return progressListener;
    }


    public UploadOptions getUploadOptions() {
        return uploadOptions;
    }


    public String getUrl() {
        return url;
    }


    public Map<String, String> getHeaders() {
        return headers;
    }


    @Override
    public String toString() {
        return "FileUploadInfo{" +
                "formParamMap=" + formParamMap +
                ", headers=" + headers +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", filePath='" + filePath + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", url='" + url + '\'' +
                ", apiCallback=" + apiCallback +
                ", progressListener=" + progressListener +
                ", uploadOptions=" + uploadOptions +
                ", preProcessedFile='" + preProcessedFile + '\'' +
                '}';
    }
}