package com.magical.library.upload.uploader;

import com.magical.library.upload.listener.OnFileTransferredListener;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Project: TShow
 * FileName: ProgressRequestBody.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class ProgressRequestBody extends RequestBody {

    /**
     * 实际的带包装请求体
     */
    private final RequestBody mRequestBody;

    /**
     * 传输进度监听
     */
    private final OnFileTransferredListener mOnFileTransferredListener;

    private BufferedSink mBufferedSink;

    public ProgressRequestBody(RequestBody requestBody, OnFileTransferredListener listener) {
        mRequestBody = requestBody;
        mOnFileTransferredListener = listener;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if(mBufferedSink == null) {
            mBufferedSink = Okio.buffer(sink(sink));
        }
        mRequestBody.writeTo(mBufferedSink);
        mBufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {

            long contentLength = 0l;
            long bytesWritten = 0l;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if(contentLength == 0) {
                    contentLength = contentLength();
                }
                bytesWritten += byteCount;
                mOnFileTransferredListener.transferred(bytesWritten, contentLength);
            }
        };
    }

}