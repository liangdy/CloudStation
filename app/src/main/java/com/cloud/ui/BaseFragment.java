package com.cloud.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloud.CloudApplication;
import com.cloud.injector.component.ActivityComponent;
import com.cloud.injector.component.ApplicationComponent;
import com.cloud.injector.module.ActivityModule;
import com.magical.library.fragment.AbstractFragment;

/**
 * Project: CloudStation
 * FileName: BaseFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:43 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:43 PM
 * Remark:
 */
public abstract class BaseFragment extends AbstractFragment {

    private static final String TAG = BaseFragment.class.getSimpleName();

    protected ActivityComponent mActivityComponent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getApplicationComponent().inject(getBaseActivity());
        initInjector();
        super.onCreate(savedInstanceState);
        
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(getBaseActivity());
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected ApplicationComponent getApplicationComponent() {
        return CloudApplication.getApplication().getApplicationComponent();
    }

    /**
     * 注入Injector
     */
    protected abstract void initInjector();
}
