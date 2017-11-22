package com.magical.library.view.recycler;

import android.view.View;

import butterknife.ButterKnife;

public abstract class SimpleItem<T> implements AdapterItem<T> {

    @Override
    public void onBindViews(View root) {
        ButterKnife.bind(this, root);
    }

    @Override
    public void onSetViews() {
    }

}
