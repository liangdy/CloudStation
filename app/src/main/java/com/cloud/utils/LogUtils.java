package com.cloud.utils;

import android.text.TextUtils;

import java.io.File;
import java.io.PrintWriter;

/**
 * Project: CloudStation
 * FileName: LogUtils.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 11/23/17 4:19 PM
 * Editor: ldy
 * Modify Date: 11/23/17 4:19 PM
 * Remark:
 */
public class LogUtils {

    private LogUtils() {
        throw new Error("Do not need instantiate!");
    }

    public static final void saveLog(String fileName, Exception e) {
        File file = StorageUtils.getLogDir();
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            PrintWriter writer = null;
            if (TextUtils.isEmpty(fileName)) {
                writer = new PrintWriter(file.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".log");
            } else {
                writer = new PrintWriter(file.getAbsolutePath() + File.separator + fileName + ".log");
            }
            e.printStackTrace(writer);
            writer.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
