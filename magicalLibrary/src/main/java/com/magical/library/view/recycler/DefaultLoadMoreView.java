package com.magical.library.view.recycler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.magical.library.R;

class DefaultLoadMoreView extends FrameLayout implements IMagicalLoadMore {
    private ProgressBar mPbLoad;
    private TextView mTvLoadText;

    private boolean isLoading = false;

    public DefaultLoadMoreView(Context context) {
        this(context, null);
    }

    public DefaultLoadMoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultLoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.mrv_view_def_load_more, this);
        mPbLoad = (ProgressBar) findViewById(R.id.mrv_pbLoad);
        mTvLoadText = (TextView) findViewById(R.id.mrv_tvLoadText);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void showNormal() {
        isLoading = false;
        mPbLoad.setVisibility(GONE);
        mTvLoadText.setText(getResources().getString(R.string.mrv_def_load_more_view_status_normal));
    }

    @Override
    public void showLoading() {
        isLoading = true;
        mPbLoad.setVisibility(VISIBLE);
        mTvLoadText.setText(getResources().getString(R.string.mrv_def_load_more_view_status_loading));
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

}