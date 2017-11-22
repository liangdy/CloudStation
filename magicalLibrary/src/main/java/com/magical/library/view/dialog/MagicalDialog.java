package com.magical.library.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magical.library.R;
import com.magical.library.view.FilletBtView;
import com.magical.library.view.dialog.animation.BaseEffects;

/**
 * Project: TShow
 * FileName: MagicalDialog.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:59 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:59 PM
 * Remark:
 */
public class MagicalDialog extends Dialog implements DialogInterface {

    private View mDialogView;
    private LinearLayout mLinearLayoutView;
    private RelativeLayout mRelativeLayoutView;
    private LinearLayout mLinearLayoutTopView;
    private LinearLayout mLinearLayoutMsgView;
    private FrameLayout mFrameLayoutCustomView;

    private TextView mTitle;
    private TextView mMessage;
    private ImageView mIcon;
    private FilletBtView mButton1;
    private FilletBtView mButton2;

    private Context mContext;

    private Effectstype type = null;
    private int mDuration = -1;
    private boolean isCancelable = true;


    public MagicalDialog(Context context) {
        super(context);
        mContext = context;
        init(context);
    }


    public MagicalDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        init(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void show() {
        super.show();
    }


    public MagicalDialog withLocation(int gravity, int x, int y) {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(gravity);
        lp.x = x;
        lp.y = y;
        dialogWindow.setAttributes(lp);
        return this;
    }


    public MagicalDialog withLocation(int gravity, View view, int x, int y) throws InstantiationException {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(gravity);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int dx = location[0];
        int dy = location[1];
        int viewH = view.getBottom() - view.getTop();
        lp.x = dx + x;
        lp.y = dy + y - getStatusHeight(mContext) + viewH;

        // 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
        // dialog.onWindowAttributesChanged(lp);
        dialogWindow.setAttributes(lp);
        return this;
    }


    public MagicalDialog withTitle(CharSequence title) {
        toggleView(mLinearLayoutTopView, title);
        mTitle.setText(title);
        return this;
    }


    public MagicalDialog withTitle(int textResId) {
        toggleView(mLinearLayoutTopView, textResId);
        mTitle.setText(textResId);
        return this;
    }


    public MagicalDialog withMessage(int textResId) {
        toggleView(mLinearLayoutMsgView, textResId);
        mMessage.setText(textResId);
        return this;
    }


    public MagicalDialog withMessage(CharSequence msg) {
        toggleView(mLinearLayoutMsgView, msg);
        mMessage.setText(msg);
        return this;
    }


    public MagicalDialog withIcon(int drawableResId) {
        mIcon.setImageResource(drawableResId);
        return this;
    }


    public MagicalDialog withIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
        return this;
    }


    public MagicalDialog withDuration(int duration) {
        this.mDuration = duration;
        return this;
    }


    public MagicalDialog withButton1Text(CharSequence text) {
        mButton1.setText(text);
        return this;
    }


    public MagicalDialog withButton2Text(CharSequence text) {
        mButton2.setText(text);
        return this;
    }


    public MagicalDialog setButton1Click(View.OnClickListener click) {
        mButton1.setOnClickListener(click);
        return this;
    }


    public MagicalDialog setButton2Click(View.OnClickListener click) {
        mButton2.setOnClickListener(click);
        return this;
    }


    public MagicalDialog setCustomView(int resId, Context context) {
        View customView = View.inflate(context, resId, null);
        if (mFrameLayoutCustomView.getChildCount() > 0) {
            mFrameLayoutCustomView.removeAllViews();
        }
        mFrameLayoutCustomView.addView(customView);
        return this;
    }


    public MagicalDialog setCustomView(View view, Context context) {
        if (mFrameLayoutCustomView.getChildCount() > 0) {
            mFrameLayoutCustomView.removeAllViews();
        }
        mFrameLayoutCustomView.addView(view);
        return this;
    }


    public MagicalDialog isCancelableOnTouchOutside(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }


    public MagicalDialog isCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCancelable(cancelable);
        return this;
    }


    public MagicalDialog withEffect(Effectstype type) {
        this.type = type;
        return this;
    }


    public MagicalDialog withAlpha(float alpha) {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
        lp.alpha = alpha;
        dialogWindow.setAttributes(lp);
        return this;
    }


    public MagicalDialog withWH(int width, int height) {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
        lp.width = width; // 宽度
        lp.height = height; // 高度
        dialogWindow.setAttributes(lp);
        return this;
    }


    private void toggleView(View view, Object obj) {
        if (obj == null) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }


    private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(mRelativeLayoutView);
    }


    private void init(Context context) {
        mDialogView = View
                .inflate(context, R.layout.dialog_default_layout, null);
        mLinearLayoutView = (LinearLayout) mDialogView
                .findViewById(R.id.parentPanel);
        mRelativeLayoutView = (RelativeLayout) mDialogView
                .findViewById(R.id.main);
        mLinearLayoutTopView = (LinearLayout) mDialogView
                .findViewById(R.id.topPanel);
        mLinearLayoutMsgView = (LinearLayout) mDialogView
                .findViewById(R.id.contentPanel);
        mFrameLayoutCustomView = (FrameLayout) mDialogView
                .findViewById(R.id.customPanel);
        mTitle = (TextView) mDialogView.findViewById(R.id.alertTitle);
        mMessage = (TextView) mDialogView.findViewById(R.id.message);
        mIcon = (ImageView) mDialogView.findViewById(R.id.icon);
        mButton1 = (FilletBtView) mDialogView.findViewById(R.id.button1);
        mButton2 = (FilletBtView) mDialogView.findViewById(R.id.button2);
        setContentView(mDialogView);

        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                mLinearLayoutView.setVisibility(View.VISIBLE);
                if (type == null) {
                    type = Effectstype.Slidetop;
                }
                start(type);
            }
        });

        mRelativeLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCancelable) {
                    dismiss();
                }
            }
        });
    }


    public int getStatusHeight(Context context) throws InstantiationException, NumberFormatException {
        int statusHeight = 0;
        Rect localRect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer
                        .parseInt(localClass.getField("status_bar_height")
                                .get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }
}
