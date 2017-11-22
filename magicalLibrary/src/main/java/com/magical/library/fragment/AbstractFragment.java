package com.magical.library.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Project: TShow
 * FileName: AbstractFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public abstract class AbstractFragment extends Fragment {
    public abstract int getLayoutId();

    public abstract void initData();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View
                .inflate(getActivity(), getLayoutId(), null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    /**
     * 删除Fragment,而且是弹出栈，彈出頂部棧Fragment
     */
    public void removeFragment() {
        getChildFragmentManager().popBackStackImmediate();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈
     */
    public void addFragmentAddStack(Fragment fragment, int addLayID) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(addLayID, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 不执行 ft.addToBackStack(null);
     */
    public void addFragment(Fragment fragment, int addLayID) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(addLayID, fragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈
     */
    public void replaceFragmentAddStack(Fragment fragment, int replaceLayID) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(replaceLayID, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 不执行 ft.addToBackStack(null);
     */
    public void replaceFragment(Fragment fragment, int replaceLayID) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(replaceLayID, fragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈
     */
    public void addFragmentAddStack(Fragment fragment, int addLayID, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
        ft.add(addLayID, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 不执行 ft.addToBackStack(null);
     */
    public void addFragment(Fragment fragment, int addLayID, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);

        ft.add(addLayID, fragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈，可以设置动画
     */
    public void replaceFragmentAddStack(Fragment fragment, int replaceLayID, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
        ft.replace(replaceLayID, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 不执行 ft.addToBackStack(null);可以设置动画
     */
    public void replaceFragment(Fragment fragment, int replaceLayID, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
        ft.replace(replaceLayID, fragment);
        ft.commitAllowingStateLoss();
    }


    public abstract String getPageName();
}