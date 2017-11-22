package com.cloud.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cloud.R;

/**
 * Project: CloudStation
 * FileName: BottomDialog.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/11/17 6:13 PM
 * Editor: ldy
 * Modify Date: 8/11/17 6:13 PM
 * Remark:
 */
public class BottomDialog {

    protected final LayoutInflater inflater;

    protected Dialog mDialog;

    public BottomDialog(Context context) {

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDialog = new Dialog(context, R.style.sheet_dialog_style);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.sheet_dialog_anim);
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wLayoutParams = dialogWindow.getAttributes();
        wLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        wLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(wLayoutParams);
    }

    public void setContentView(int resId) {
        if (mDialog != null) {
            mDialog.setContentView(resId);
        }
    }

    public void setContentView(View contentView) {
        if (mDialog != null) {
            mDialog.setContentView(contentView);
        }
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        if (mDialog != null) {
            mDialog.setOnDismissListener(listener);
        }
    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }


    public View findViewById(int id) {
        return mDialog.findViewById(id);
    }

}
