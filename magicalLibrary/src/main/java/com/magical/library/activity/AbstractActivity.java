package com.magical.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Project: TShow
 * FileName: AbstractActivity.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public abstract class AbstractActivity extends AppCompatActivity {

    protected abstract void initUI();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract int fragmentRoot();

    protected abstract String getPageName();

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initUI();
        initListener();
        initData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 删除Fragment,但是并不出栈
     */
    public void removeFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        ft.remove(fragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * 删除Fragment,而且是弹出栈，彈出頂部棧Fragment
     */
    public void removeFragment() {
        getSupportFragmentManager().popBackStackImmediate();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈
     */
    public void addFragmentAddStack(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fragmentRoot(), fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈
     */
    public void addFragmentAddStack(Fragment fragment, int addLayID) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(addLayID, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 不执行 ft.addToBackStack(null);
     */
    public void addFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fragmentRoot(), fragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * 不执行 ft.addToBackStack(null);
     */
    public void addFragment(Fragment fragment, int addLayID) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(addLayID, fragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈
     */
    public void replaceFragmentAddStack(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(fragmentRoot(), fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈
     */
    public void replaceFragmentAddStack(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(fragmentRoot(), fragment, tag);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈
     */
    public void replaceFragmentAddStack(Fragment fragment, int replaceLayID) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(replaceLayID, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 不执行 ft.addToBackStack(null);
     */
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(fragmentRoot(), fragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * 不执行 ft.addToBackStack(null);
     */
    public void replaceFragment(Fragment fragment, int replaceLayID) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(replaceLayID, fragment);
        ft.commitAllowingStateLoss();
    }

    /*********************************************************** 动画区域 Start **************************/
    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈
     */
    public void addFragmentAddStack(Fragment fragment, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
        ft.add(fragmentRoot(), fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈
     */
    public void addFragmentAddStack(Fragment fragment, int addLayID, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
        ft.add(addLayID, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 不执行 ft.addToBackStack(null);
     */
    public void addFragment(Fragment fragment, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
        ft.add(fragmentRoot(), fragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * 不执行 ft.addToBackStack(null);
     */
    public void addFragment(Fragment fragment, int addLayID, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);

        ft.add(addLayID, fragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈，可以设置动画
     */
    public void replaceFragmentAddStack(Fragment fragment, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
        ft.replace(fragmentRoot(), fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈，可以设置动画
     */
    public void replaceFragmentAddStack(Fragment fragment, int enter, int exit, int popEnter, int popExit, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
        ft.replace(fragmentRoot(), fragment, tag);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 执行 ft.addToBackStack(null); 圧栈，返回的时候弹出栈，可以设置动画
     */
    public void replaceFragmentAddStack(Fragment fragment, int replaceLayID, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
        ft.replace(replaceLayID, fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }


    /**
     * 不执行 ft.addToBackStack(null);,可以设置动画
     */
    public void replaceFragment(Fragment fragment, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
        ft.replace(fragmentRoot(), fragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * 不执行 ft.addToBackStack(null);可以设置动画
     */
    public void replaceFragment(Fragment fragment, int replaceLayID, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
        ft.replace(replaceLayID, fragment);
        ft.commitAllowingStateLoss();
    }
}
