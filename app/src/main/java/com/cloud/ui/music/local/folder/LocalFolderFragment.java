package com.cloud.ui.music.local.folder;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.FolderInfo;
import com.cloud.ui.BaseFragment;
import com.cloud.ui.adapter.LocalFolderAdapter;
import com.cloud.view.SideBar;
import com.magical.library.view.recycler.MagicalRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: LocalFolderFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/6/17 2:43 PM
 * Editor: ldy
 * Modify Date: 9/6/17 2:43 PM
 * Remark:
 */
public class LocalFolderFragment extends BaseFragment implements LocalFolderContract.View {

    private static final String TAG = LocalFolderFragment.class.getSimpleName();

    @Inject
    LocalFolderPresenter mPresenter;
    @Inject
    LocalFolderAdapter mAdapter;
    @BindView(R.id.folder_list)
    MagicalRecyclerView folderList;
    @BindView(R.id.dialog_text)
    TextView dialogText;
    @BindView(R.id.sidebar)
    SideBar sidebar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInjector() {
        DaggerLocalFolderComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .localFolderModule(new LocalFolderModule())
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_local_folder;
    }

    @Override
    public void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void initialized() {
        folderList.setAdapter(mAdapter);
        folderList.setHasFixedSize(true);
        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                dialogText.setText(s);
                sidebar.setView(dialogText);
                /*if (positionMap.get(s) != null) {
                    int i = positionMap.get(s);
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(i + 1, 0);
                }*/

            }
        });
        folderList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    sidebar.setVisibility(View.VISIBLE);
                }
            }
        });
        mAdapter.setListener(new LocalFolderAdapter.ActionListener() {
            @Override
            public void actionMore(FolderInfo model) {
                
            }

            @Override
            public void onItemClick(FolderInfo model) {

            }
        });
    }

    @Override
    public void loadLocalFolder() {
        if (mPresenter != null) {
            mPresenter.getLocalFolder();
        }
    }

    @Override
    public void updateFolderInfo(List<FolderInfo> folderInfos) {
        if (mAdapter != null && mPresenter != null) {
            mPresenter.updateFolderInfo(mAdapter, folderInfos);
        }
    }

    @Override
    public String getPageName() {
        return TAG;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
