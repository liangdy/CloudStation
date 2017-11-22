package com.cloud.ui.nav;

import android.os.Bundle;
import android.view.View;

import com.cloud.R;
import com.cloud.model.NavModel;
import com.cloud.ui.BaseFragment;
import com.cloud.ui.adapter.NavAdapter;
import com.cloud.utils.Constants;
import com.magical.library.utils.PrefManager;
import com.magical.library.view.recycler.MagicalRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: NavFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/1/17 11:16 AM
 * Editor: ldy
 * Modify Date: 3/1/17 11:16 AM
 * Remark:
 */
public class NavFragment extends BaseFragment implements NavContract.View {

    private static final String TAG = NavFragment.class.getSimpleName();

    @BindView(R.id.type_list)
    MagicalRecyclerView typeList;

    @Inject
    NavPresenter mPresenter;
    @Inject
    NavAdapter mAdapter;

    @Override
    protected void initInjector() {
        DaggerNavComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .navModule(new NavModule(getContext()))
                .build()
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_nav;
    }

    @Override
    public void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public String getPageName() {
        return TAG;
    }

    @Override
    public void initialized() {
        final List<NavModel> list = NavModel.getLocalNavList(getBaseActivity());
        typeList.setAdapter(mAdapter);

        typeList.setOnItemClickListener(new MagicalRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(MagicalRecyclerView magicalRecyclerView, View view, int position) {
                NavModel model = list.get(position);
                if (mPresenter != null) {
                    mPresenter.goMainPage(model.navType);
                }
                PrefManager.getInstance().putIntToPrefs(getContext(), Constants.NAV_TAG, model.navType);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
