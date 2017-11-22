package com.magical.library.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

/**
 * Project: TShow
 * FileName: ToastUtils.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:57 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:57 PM
 * Remark:
 */
public final class ToastUtils {

    private ToastUtils(){
        throw new Error("Do not need instantiate!");
    }

    public static int CONSTANT_DURA = Toast.LENGTH_SHORT;


    /**
     * Find text from resourse and show into toast message
     *
     * @param context           {@link Activity} used to create the {@link Toast}
     *                          message
     * @param messageResourceId The resource containing the message to be
     *                          displayed
     */
    public static void show(final Context context, final int messageResourceId) {
        ToastUtils.show(context, messageResourceId, ToastUtils.CONSTANT_DURA);
    }


    /**
     * Find text from resourse and show into toast message with custome time
     * {@link } in millisecond
     *
     * @param context           {@link Activity} used to create the {@link Toast}
     *                          message
     * @param messageResourceId The resource containing the message to be
     *                          displayed
     * @param duration          How long to display the message. Either
     *                          {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
     */
    public static void show(final Context context, final int messageResourceId, final int duration) {
        ToastUtils.showToast(context, context
                .getString(messageResourceId), null, duration);
    }


    /**
     * Toast directly pass string to file to an argument
     *
     * @param context {@link Activity} used to create the {@link Toast}
     *                message
     * @param message The message to be displayed
     */
    public static void show(final Context context, final String message) {
        ToastUtils.show(context, message, ToastUtils.CONSTANT_DURA);
    }


    /**
     * Toast directly pass string to file to an argument with custom
     * {@link Toast} message
     *
     * @param context  {@link Activity} used to create the {@link Toast}
     *                 message
     * @param message  The message to be displayed
     * @param duration How long to display the message. Either
     *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
     */
    public static void show(final Context context, final String message, final int duration) {
        ToastUtils.showToast(context, message, null, duration);
    }


    /**
     * Make a standard toast that displays a {@link View}.
     *
     * @param context {@link Activity} used to create the {@link Toast}
     *                message
     * @param view    The {@link View} to show
     */
    public static void show(final Context context, final View view) {
        ToastUtils.show(context, view, ToastUtils.CONSTANT_DURA);
    }


    /**
     * Make a standard toast that displays a {@link View}.
     *
     * @param context  {@link Activity} used to create the {@link Toast}
     *                 message
     * @param view     The {@link View} to show
     * @param duration How long to display the message. Either
     *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
     */
    public static void show(final Context context, final View view, final int duration) {
        ToastUtils.showToast(context, null, view, duration);
    }


    /**
     * @param context  {@link Activity} used to create the {@link Toast}
     *                 message.
     * @param message  The message to be displayed.
     * @param view     The {@link View} to be displayed.
     * @param duration How long to display the message. Either
     *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}.
     */
    private static void showToast(Context context, String message, View view, int duration) {
        if (TextUtils.isEmpty(message) && view == null) {
            return;
        }

        final Toast toast = Toast.makeText(context, message, duration);
        if (view != null) {
            toast.setView(view);
        }
        toast.show();
    }
}
