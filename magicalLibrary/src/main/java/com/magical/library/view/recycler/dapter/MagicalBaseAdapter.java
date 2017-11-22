package com.magical.library.view.recycler.dapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.magical.library.view.recycler.AdapterItem;
import com.magical.library.view.recycler.AdapterItemUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class MagicalBaseAdapter<T> extends RecyclerView.Adapter {

    private final boolean DEBUG = false;

    private List<T> mData;

    private Object mItemType;

    private AdapterItemUtil mUtil = new AdapterItemUtil();

    public MagicalBaseAdapter() {
        this(null);
    }

    public MagicalBaseAdapter(List<T> data) {
        mData = data == null ? new ArrayList<T>() : data;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<T> getData() {
        return mData;
    }

    public int getDataSize() {
        return mData.size();
    }

    public T getData(int index) {
        return mData.size() > index ? mData.get(index) : null;
    }

    public void add(T d) {
        int startPos = mData.size();
        mData.add(d);
        notifyItemInserted(startPos);
    }

    public void addAll(List<T> data) {
        int curSize = mData.size();
        mData.addAll(data);
        if (curSize == 0) {
            notifyDataSetChanged();
        } else {
            notifyItemRangeInserted(curSize, data.size());
        }
    }

    public void remove(T d) {
        if (mData.contains(d)) {
            int posIndex = mData.indexOf(d);
            mData.remove(d);
            notifyItemRemoved(posIndex);
        }
    }

    public void remove(int index) {
        if (mData.size() > index) {
            mData.remove(index);
            notifyItemRemoved(index);
        }
    }

    public boolean contains(T d) {
        return mData.contains(d);
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 可以被复写用于单条刷新等
     */
    public void updateData(@NonNull List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Deprecated
    @Override
    public int getItemViewType(int position) {
        mItemType = getItemViewType(mData.get(position));
        return mUtil.getIntType(mItemType);
    }

    public Object getItemViewType(T t) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MagicalBaseAdapter.RcvAdapterItem(parent
                .getContext(), parent, getItemView(mItemType));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (DEBUG) {
            MagicalBaseAdapter.RcvAdapterItem item = (MagicalBaseAdapter.RcvAdapterItem) holder;
            item.itemView
                    .setBackgroundColor(item.isNew ? 0xffff0000 : 0xff00ff00);
            item.isNew = false;
        }
        ((MagicalBaseAdapter.RcvAdapterItem) holder).getItem().onUpdateViews(mData
                .get(position), position);
    }

    public abstract
    @NonNull
    AdapterItem<T> getItemView(Object type);

    private class RcvAdapterItem extends RecyclerView.ViewHolder {

        private AdapterItem<T> mItem;

        public boolean isNew = true; // debug中才用到


        protected RcvAdapterItem(Context context, ViewGroup parent, AdapterItem<T> item) {
            super(LayoutInflater.from(context)
                    .inflate(item.getLayoutResId(), parent, false));
            mItem = item;
            mItem.onBindViews(itemView);
            mItem.onSetViews();
        }

        protected AdapterItem<T> getItem() {
            return mItem;
        }
    }
}
