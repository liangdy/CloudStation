package com.cloud.ui.nav;

import android.content.Context;

import com.cloud.model.NavModel;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: MainModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/23/17 10:38 AM
 * Editor: ldy
 * Modify Date: 2/23/17 10:38 AM
 * Remark:
 */
@Module
public class NavModule {

    private Context context;

    public NavModule(Context context) {
        this.context = context;
    }

    @Provides
    List<NavModel> providesNavList() {
        List<NavModel> list = NavModel.getLocalNavList(context);
        return list;
    }
}
