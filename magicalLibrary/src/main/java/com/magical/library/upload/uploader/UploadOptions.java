package com.magical.library.upload.uploader;


import com.magical.library.upload.parser.BaseResponseParser;
import com.magical.library.upload.preprocessor.BasePreProcessor;

/**
 * Project: TShow
 * FileName: UploadOptions.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class UploadOptions {

    private BasePreProcessor mPreProcessor;
    private BaseResponseParser mResponseParser;

    public UploadOptions(Builder builder) {
        mPreProcessor = builder.preProcessor;
        mResponseParser = builder.responseParser;
    }

    public BasePreProcessor getPreProcessor() {
        return mPreProcessor;
    }

    public BaseResponseParser getResponseParser() {
        return mResponseParser;
    }

    public static class Builder {

        private BasePreProcessor preProcessor;
        private BaseResponseParser responseParser;

        public Builder() {

        }

        public Builder setPreProcessor(BasePreProcessor preProcessor) {
            this.preProcessor = preProcessor;
            return this;
        }

        public Builder setResponseParser(BaseResponseParser parser) {
            this.responseParser = parser;
            return this;
        }

        public UploadOptions build() {
            UploadOptions options = new UploadOptions(this);
            return options;
        }

    }

}