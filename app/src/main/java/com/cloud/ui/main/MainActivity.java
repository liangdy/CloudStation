package com.cloud.ui.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.cloud.R;
import com.cloud.model.NavModel;
import com.cloud.ui.BaseActivity;
import com.cloud.utils.Constants;
import com.magical.library.utils.PrefManager;
import com.magical.library.utils.StatusBarUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: MainActivity.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 5:01 PM
 * Editor: ldy
 * Modify Date: 2/22/17 5:01 PM
 * Remark:
 */
public class MainActivity extends BaseActivity implements MainContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.main_drawer)
    DrawerLayout mainDrawer;
    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    @BindView(R.id.nav_container)
    FrameLayout navContainer;
    @BindView(R.id.root)
    LinearLayout rootLayout;

    private ActionBarDrawerToggle mActionBarDrawerToggle = null;

    @Inject
    public MainPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setColor(this, getResources().getColor(R.color.green), 0);
    }

    @Override
    protected void initInjector() {
        DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .mainModule(new MainModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initUI() {
        if (null != commonToolbar) {
            setSupportActionBar(commonToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected int fragmentRoot() {
        return R.id.root;
    }

    @Override
    protected String getPageName() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initialized() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mainDrawer, commonToolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(getString(R.string.app_name));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mainDrawer.addDrawerListener(mActionBarDrawerToggle);
        if (mPresenter != null) {
            mPresenter.showMainContainer(NavModel.TYPE_MUSIC);
            mPresenter.showNavContainer();
            PrefManager.getInstance().putIntToPrefs(this, Constants.NAV_TAG, NavModel.TYPE_MUSIC);
        }
    }

    @Override
    public void closeDrawer() {
        if (mainDrawer.isDrawerOpen(Gravity.LEFT)) {
            mainDrawer.closeDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void openDrawer() {
        if (!mainDrawer.isDrawerOpen(Gravity.LEFT)) {
            mainDrawer.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void setTitleBar(String title) {
        setTitle(title);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            if (mainDrawer.isDrawerOpen(Gravity.LEFT)) {
                mainDrawer.closeDrawer(Gravity.LEFT);
            } else {
                mainDrawer.openDrawer(Gravity.LEFT);
            }
            return true;
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mainDrawer.isDrawerOpen(Gravity.LEFT)) {
                mainDrawer.closeDrawer(Gravity.LEFT);
            } else {
                if (mPresenter != null) {
                    mPresenter.exit(rootLayout);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle != null && mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_capture:

                break;
            case R.id.action_about_us:

                break;
            case R.id.action_feedback:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
