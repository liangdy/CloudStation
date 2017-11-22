package com.magical.library.view.recycler.dapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public abstract class MagicalEasyAdapter<T> extends MagicalBaseAdapter {
    private Context mContext;
    private int mItemLayoutId;

    public MagicalEasyAdapter(Context context, int itemLayoutId) {
        this(context, itemLayoutId, null);
    }

    public MagicalEasyAdapter(Context context, int itemLayoutId, List<T> data) {
        super(data);
        mContext = context;
        mItemLayoutId = itemLayoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(mItemLayoutId, parent, false));
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> mViews;
        private View mItemView;

        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mViews = new SparseArray<>();
        }

        public <T extends View> T findView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mItemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
    }
}
