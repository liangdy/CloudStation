package com.magical.library.upload.uploader;

import android.text.TextUtils;

import com.magical.library.upload.CustomHttpClient;
import com.magical.library.upload.FileUploadInfo;
import com.magical.library.upload.listener.OnFileTransferredListener;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Project: TShow
 * FileName: OKHttpUploader.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class OKHttpUploader extends BaseUploader {

    @Override
    public String upload(FileUploadInfo fileUploadInfo, OnFileTransferredListener fileTransferredListener) throws IOException {
        final File file = new File(fileUploadInfo.getUploadFilePath());

        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        Map<String, String> paramMap = fileUploadInfo.getFormParamMap();
        if (paramMap != null && !paramMap.isEmpty()) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                multipartBuilder.addFormDataPart(key, value);
            }
        }

        String mimeType = fileUploadInfo.getMimeType();
        if (TextUtils.isEmpty(mimeType)) {
            mimeType = "";
        }

        String fileKey = fileUploadInfo.getName();
        if (!TextUtils.isEmpty(fileKey)) {
            multipartBuilder
                    .addFormDataPart(fileKey, file.getName(), RequestBody
                            .create(MediaType.parse(mimeType), file));
        } else {
            multipartBuilder.addPart(Headers.of("Content-Disposition",
                    "form-data; name=\"file\"; filename=\"" + file.getName() +
                            "\""), RequestBody
                    .create(MediaType.parse(mimeType), file));
        }

        RequestBody multipartBody = multipartBuilder.build();
        RequestBody requestBody = new ProgressRequestBody(multipartBody, fileTransferredListener);
        Request.Builder builder = new Request.Builder()
                .tag(generateTag(fileUploadInfo)).url(fileUploadInfo.getUrl());
        if (!fileUploadInfo.getHeaders().isEmpty()) {
            Headers headers = Headers.of(fileUploadInfo.getHeaders());
            builder.headers(headers);
        } else {
            builder.header("Content-Type", fileUploadInfo.getMimeType());
        }
        Request request = builder.post(requestBody).build();

        Response response = CustomHttpClient.execute(request);
        if (response != null) {
            if (response.isSuccessful()) {
                String respStr = response.body().string();
                return respStr;
            } else {
                throw new IOException(response.toString());
            }
        } else {
            throw new IOException("Cancelled");
        }
    }

    private String generateTag(FileUploadInfo fileUploadInfo) {
        return fileUploadInfo.getId() +
                fileUploadInfo.getUploadFilePath().hashCode();
    }
}
