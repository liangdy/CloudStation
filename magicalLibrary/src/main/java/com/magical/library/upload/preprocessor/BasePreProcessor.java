package com.magical.library.upload.preprocessor;

/**
 * Project: TShow
 * FileName: BasePreProcessor.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public abstract class BasePreProcessor {

    /**
     * 对要上传的文件，是否还需要做额外的处理
     *
     * @param filePath 原文件
     * @return 处理后需要上传的新的文件路径
     */
    public abstract String process(String filePath);

}