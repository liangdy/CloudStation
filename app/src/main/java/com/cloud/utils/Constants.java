package com.cloud.utils;

import java.io.File;

/**
 * Project: CloudStation
 * FileName: Constants.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:14 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:14 PM
 * Remark:
 */
public class Constants {

    private Constants() {
        throw new Error("Do not need instantiate!");
    }

    public static class DIR {
        public static final String ROOT = "/CloudStation";
        public static final String CACHE = ROOT + File.separator + ".cache";
        public static final String TEMP = ROOT + File.separator + "temp";
        public static final String IMAGE = ROOT + File.separator + "image";
        public static final String LOG = ROOT + File.separator + "log";
    }

    //nav tag
    public static final String NAV_TAG = "cur_nav";
}
