package com.cloud.utils;

import com.magical.library.utils.FileUtils;

import java.io.File;

/**
 * Project: CloudStation
 * FileName: StorageUtils.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:13 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:13 PM
 * Remark:
 */
public class StorageUtils {

    private StorageUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 在SD里的根路径 如果该文件(夹)不存在，原因可能是本机没挂在sd卡等原因
     * 如果该文件(夹)存在，此方法会尝试new一个File，只有当new出的File成功时，才会返回文件 否则一律返回null
     */
    public static File getRootDir() {
        return FileUtils.mkDirs(Constants.DIR.ROOT);
    }

    /**
     * 图片缓存路径
     */
    public static File getImagesDir() {
        return FileUtils.mkDirs(Constants.DIR.IMAGE);
    }

    /**
     * 临时目录路径
     */
    public static File getTempDir() {
        return FileUtils.mkDirs(Constants.DIR.TEMP);
    }
    
    /**
     * 本地各种缓存路径
     */
    public static File getCacheDir() {
        return FileUtils.mkDirs(Constants.DIR.CACHE);
    }

    /**
     * 日志保存路径
     */
    public static File getLogDir() {
        return FileUtils.mkDirs(Constants.DIR.LOG);
    }
}
