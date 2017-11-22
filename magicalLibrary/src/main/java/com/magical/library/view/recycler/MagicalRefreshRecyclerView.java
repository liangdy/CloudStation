package com.magical.library.view.recycler;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class MagicalRefreshRecyclerView extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private MagicalRecyclerView mRvList;
    private IMagicalLoadMore mLoadMoreView;

    private OnPullRefreshListener mOnPullRefreshListener;
    private OnLoadMoreListener mOnLoadMoreListener;
    private MagicalRecyclerViewOnScrollListener mMagicalRecyclerViewOnScrollListener;
    private boolean isPullRefreshEnabled = true;
    private boolean isLoadMoreEnabled = false;

    public MagicalRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initViews(attrs);
    }

    private void initViews(AttributeSet attrs) {
        mRvList = new MagicalRecyclerView(getContext(), attrs);
        addView(mRvList, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setOnRefreshListener(this);
        setLoadMoreView(new DefaultLoadMoreView(mContext));
    }

    @Override
    public void onRefresh() {
        if (!isPullRefreshEnabled) return;

        callOnPullRefresh();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mRvList.setAdapter(adapter);
    }

    public void setLoadMoreView(IMagicalLoadMore loadMoreView) {
        if (null == loadMoreView) {
            if (null != mLoadMoreView) {
                mRvList.removeFooterView(mLoadMoreView.getView());
                mRvList.removeOnScrollListener(mMagicalRecyclerViewOnScrollListener);
                mLoadMoreView = null;
            }
            return;
        }

        mLoadMoreView = loadMoreView;
        initializeLoadMoreView();
    }

    /**
     * Get MagicalRecyclerView
     *
     * @return MagicalRecyclerView
     */
    public MagicalRecyclerView getMagicalRecyclerView() {
        return mRvList;
    }

    /**
     * Automatic pull refresh
     */
    public void autoRefresh() {
        if (!isPullRefreshEnabled) return;

        setRefreshing(true);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callOnPullRefresh();
            }
        }, 1000);
    }

    public void setPullRefreshEnabled(boolean enabled) {
        if (isPullRefreshEnabled == enabled) return;

        setEnabled(enabled);

        if (!enabled) {
            setRefreshing(false);
        }

        this.isPullRefreshEnabled = enabled;
    }

    public void setLoadMoreEnabled(boolean enabled) {
        if (isLoadMoreEnabled == enabled) return;

        if (!enabled) {
            mRvList.removeFooterView(mLoadMoreView.getView());
        } else {
            mRvList.addFooterView(mLoadMoreView.getView());
        }

        isLoadMoreEnabled = enabled;
    }

    public void pullRefreshComplete() {
        setRefreshing(false);
    }

    public void loadMoreComplete() {
        mLoadMoreView.showNormal();
    }

    private void initializeLoadMoreView() {
        if (null == mMagicalRecyclerViewOnScrollListener) {
            mMagicalRecyclerViewOnScrollListener = new MagicalRecyclerViewOnScrollListener(mRvList.getLayoutManager()) {
                @Override
                public void onScrolledToTop() {
                }

                @Override
                public void onScrolledToBottom() {
                    if (!isLoadMoreEnabled || mLoadMoreView.isLoading()) return;

                    mLoadMoreView.showLoading();
                    callOnLoadMore();
                }
            };
        }
        mRvList.addOnScrollListener(mMagicalRecyclerViewOnScrollListener);
        mLoadMoreView.getView().setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
    }

    private void callOnPullRefresh() {
        if (null != mOnPullRefreshListener) {
            mOnPullRefreshListener.onPullRefresh();
        }
    }

    private void callOnLoadMore() {
        if (null != mOnLoadMoreListener) {
            mOnLoadMoreListener.onLoadMore();
        }
    }

    public void setOnPullRefreshListener(OnPullRefreshListener onPullRefreshListener) {
        this.mOnPullRefreshListener = onPullRefreshListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    public void setOnItemClickListener(final MagicalRecyclerView.OnItemClickListener onItemClickListener) {
        if (null != onItemClickListener) {
            mRvList.setOnItemClickListener(onItemClickListener);
        }
    }

    public void setOnItemLongClickListener(final MagicalRecyclerView.OnItemLongClickListener onItemLongClickListener) {
        if (null != onItemLongClickListener) {
            mRvList.setOnItemLongClickListener(onItemLongClickListener);
        }
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        try {
            super.onRestoreInstanceState(state);
        } catch (Exception e) {
        }
        state = null;
    }

    public interface OnPullRefreshListener {
        void onPullRefresh();
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

}
