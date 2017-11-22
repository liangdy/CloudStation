package com.cloud.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Project: CloudStation
 * FileName: TextParser.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 4/23/17 2:14 PM
 * Editor: ldy
 * Modify Date: 4/23/17 2:14 PM
 * Remark:
 */
public class TextParser {

    private List<TextBean> textList;

    public TextParser() {
        textList = new LinkedList<TextBean>();
    }

    /**
     * 添加文字
     */
    public TextParser append(String text, int size, int color) {
        if (text == null) {
            return this;
        }
        TextBean bean = new TextBean();
        bean.text = text;
        bean.size = size;
        bean.color = color;
        textList.add(bean);
        return this;
    }

    /**
     * 添加文字
     */
    public TextParser append(String text, int size, String color) {
        if (text == null) {
            return this;
        }
        TextBean bean = new TextBean();
        bean.text = text;
        bean.size = size;
        bean.color = Color.parseColor(color);
        textList.add(bean);
        return this;
    }

    /**
     * 添加带链接的文字
     */
    public TextParser append(String text, int size, int color,
                             View.OnClickListener onClickListener) {
        if (text == null) {
            return this;
        }
        TextBean bean = new TextBean();
        bean.text = text;
        bean.size = size;
        bean.color = color;
        bean.onClickListener = onClickListener;
        textList.add(bean);
        return this;
    }

    /**
     * 添加带链接的文字
     */
    public TextParser append(String text, int size, String color,
                             View.OnClickListener onClickListener) {
        if (text == null) {
            return this;
        }
        TextBean bean = new TextBean();
        bean.text = text;
        bean.size = size;
        bean.color = Color.parseColor(color);
        bean.onClickListener = onClickListener;
        textList.add(bean);

        return this;
    }


    public void parse(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            textView.setText("");
            return;
        }
        StringBuilder sBuilder = new StringBuilder(str);
        SpannableStringBuilder style = new SpannableStringBuilder(sBuilder);
        int position = 0;
        for (TextBean textBean : textList) {
            if (!str.contains(textBean.text)) {
                continue;
            }
            int start = str.indexOf(textBean.text, position);
            if (textBean.onClickListener != null) {
                // 如果有点击，则在上面添加点击处理的Span  
                style.setSpan(new MyClickableSpan(textBean.onClickListener),// Span接口用于实现对文本的修饰的具体内容  
                        start,// 修饰的起始位置  
                        start + textBean.text.length(),// 修饰的结束位置  
                        Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            // 字体上色，字体的背景颜色也可以单独改变  
            style.setSpan(new ForegroundColorSpan(textBean.color), start,
                    start + textBean.text.length(),
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            // 改变字体大小  
            style.setSpan(new AbsoluteSizeSpan(textBean.size), start, start
                    + textBean.text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            position = start + textBean.text.length();
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(style);
    }

    /**
     * 写入TextView
     */
    public void parse(TextView textView) {
        // 先将文字放在一起，传入到SpannableBuilder中  
        // 后面做的是对文本进行修饰和替换  
        StringBuilder sBuilder = new StringBuilder();
        for (TextBean bean : textList) {
            sBuilder.append(bean.text);
        }

        // 所有的文字和效果都要写在Spannable中，SpanableStringBuilder用于创建Spannable，  
        // 其实它也是Spannable的一个实现类  
        SpannableStringBuilder style = new SpannableStringBuilder(sBuilder);
        int position = 0;
        for (TextBean bean : textList) {
            if (bean.onClickListener != null) {
                // 如果有点击，则在上面添加点击处理的Span  
                style.setSpan(new MyClickableSpan(bean.onClickListener),// Span接口用于实现对文本的修饰的具体内容  
                        position,// 修饰的起始位置  
                        position + bean.text.length(),// 修饰的结束位置  
                        Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            // 字体上色，字体的背景颜色也可以单独改变  
            style.setSpan(new ForegroundColorSpan(bean.color), position,
                    position + bean.text.length(),
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            // 改变字体大小  
            style.setSpan(new AbsoluteSizeSpan(bean.size), position, position
                    + bean.text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);  
            /* 
             * 如上所示，Spannalbe接口是说这个类可以被改变，Span说的是这个类可以去改变别的类 
             * 整个改变样式做的就是用Span去改变Spannable中的内容。 
             */
            position += bean.text.length();
        }

        // 设置TextView让文字可以被点击  
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(style);
    }

    /**
     * 用于记录文本的内容，字体，大小和监听器
     */
    private class TextBean {
        public View.OnClickListener onClickListener;
        public String text;
        public int size;
        public int color;
    }

    /**
     * 用于更改文字点击的事件和效果
     */
    private static class MyClickableSpan extends ClickableSpan {
        private View.OnClickListener mOnClickListener;

        public MyClickableSpan(View.OnClickListener onClickListener) {
            mOnClickListener = onClickListener;
        }

        @Override
        public void onClick(View widget) {
            if (mOnClickListener != null) {
                mOnClickListener.onClick(widget);
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
        }
    }
}
