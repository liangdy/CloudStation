package com.magical.library.utils;

import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

/**
 * Project: TShow
 * FileName: MagicalLog.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:33 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:33 PM
 * Remark:
 */
public class MagicalLog {

    private static final String TAG = "MagicalLog";

    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_WARNING = 3;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    private static int sLevel = 0;


    private MagicalLog() {
        throw new Error("Do not need instantiate!");
    }


    public static void setLogLevel(int level) {
        sLevel = level;
    }


    public static Settings init() {
        return Logger.init(TAG);
    }


    public static Settings init(String tag) {
        return Logger.init(tag);
    }


    public static void clear() {
        Logger.resetSettings();
    }


    public static void t(String tag) {
        if (sLevel <= 0) {
            Logger.t(tag);
        }
    }


    public static void t(int methodCount) {
        if (sLevel <= 0) {
            Logger.t(methodCount);
        }
    }


    public static void t(String tag, int methodCount) {
        if (sLevel <= 0) {
            Logger.t(tag, methodCount);
        }
    }


    public static void v(String msg, Object... args) {
        if (sLevel <= 0) {
            if (args != null && args.length >= 1) {
                Logger.v(msg, args);
            } else {
                Logger.v(msg, new Object[0]);
            }
        }
    }


    public static void d(String msg, Object... args) {
        if (sLevel <= 1) {
            if (args != null && args.length >= 1) {
                Logger.d(msg, args);
            } else {
                Logger.d(msg, new Object[0]);
            }
        }
    }


    public static void i(String msg, Object... args) {
        if (sLevel <= 2) {
            if (args != null && args.length >= 1) {
                Logger.i(msg, args);
            } else {
                Logger.i(msg, new Object[0]);
            }
        }
    }


    public static void w(String msg, Object... args) {
        if (sLevel <= 3) {
            if (args != null && args.length >= 1) {
                Logger.w(msg, args);
            } else {
                Logger.w(msg, new Object[0]);
            }
        }
    }


    public static void e(String msg, Object... args) {
        if (sLevel <= 4) {
            if (args != null && args.length >= 1) {
                Logger.e(msg, args);
            } else {
                Logger.e(msg, new Object[0]);
            }
        }
    }


    public static void e(Throwable throwable, String msg, Object... args) {
        if (sLevel <= 4) {
            if (args != null && args.length >= 1) {
                Logger.e(throwable, msg, args);
            } else {
                Logger.e(throwable, msg, new Object[0]);
            }
        }
    }


    public static void f(String msg, Object... args) {
        if (sLevel <= 5) {
            if (args != null && args.length >= 1) {
                Logger.wtf(msg, args);
            } else {
                Logger.wtf(msg, new Object[0]);
            }
        }
    }


    public static void json(String json) {
        if (sLevel <= 0) {
            Logger.json(json);
        }
    }


    public static void xml(String xml) {
        if (sLevel <= 0) {
            Logger.xml(xml);
        }
    }
}
