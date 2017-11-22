package com.magical.library.view.recycler;

import android.view.View;

public interface IMagicalLoadMore {

    void showLoading();

    void showNormal();

    boolean isLoading();

    View getView();

}
