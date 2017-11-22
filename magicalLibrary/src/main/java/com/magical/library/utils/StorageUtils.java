package com.magical.library.utils;

import android.os.Environment;
import android.os.StatFs;

/**
 * Project: TShow
 * FileName: StorageUtils.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:40 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:40 PM
 * Remark:
 */
public class StorageUtils {

    private StorageUtils() {
        throw new Error("Do not need instantiate!");
    }


    /**
     * 是否挂载SD卡
     */
    public static boolean hasSD() {
        // return false;
        return Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * SD卡根路径
     */
    public static String getSdRoot() {
        if (hasSD()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }


    /**
     * 判断SD卡是否已满了
     */
    public static boolean SDIsFull() {
        if (hasSD() && (getAvailableStore(getSdRoot()) < 1024 * 32)) {
            return true;
        }
        return false;
    }


    /**
     * 返回单位是 B
     */
    public static long getAvailableStore(String filePath) {
        StatFs statFs = new StatFs(filePath);
        long blocSize = statFs.getBlockSize();
        long availaBlock = statFs.getAvailableBlocks();
        long availableSpare = availaBlock * blocSize;
        return availableSpare;
    }


    /**
     * 取得手机sd卡剩余空间
     */
    public static long getSDAvailableStore() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (Environment.getExternalStorageDirectory().canWrite()) {
                return getAvailableStore(Environment
                        .getExternalStorageDirectory().getPath());
            }
        }
        return -1;
    }


    /**
     * 取得手机存储剩余空间
     */
    public static long getInternalAvailableStore() {
        return getAvailableStore(Environment.getDataDirectory()
                .getAbsolutePath());
    }
}
