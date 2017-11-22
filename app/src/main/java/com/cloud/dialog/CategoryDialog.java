package com.cloud.dialog;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.SongSheetCategory;
import com.cloud.view.BottomDialog;
import com.magical.library.utils.ScreenUtils;
import com.magical.library.utils.UiUtils;
import com.magical.library.view.CircleAngleTitleView;
import com.magical.library.view.recycler.AdapterItem;
import com.magical.library.view.recycler.MagicalRecyclerView;
import com.magical.library.view.recycler.SimpleItem;
import com.magical.library.view.recycler.dapter.CommonRcvAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: CloudStation
 * FileName: CategoryDialog.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/11/17 10:09 PM
 * Editor: ldy
 * Modify Date: 8/11/17 10:09 PM
 * Remark:
 */
public class CategoryDialog extends BottomDialog {

    private Activity mActivity;

    private CategoryAdapter adapter;
    private List<SongSheetCategory.ContentBean> categories = new ArrayList<>();

    public void setCategories(List<SongSheetCategory.ContentBean> categories) {
        this.categories.addAll(categories);
        adapter.notifyDataSetChanged();
    }

    @Inject
    public CategoryDialog(Activity activity) {
        super(activity);
        mActivity = activity;
        initView();
    }

    private void initView() {
        View root = inflater.inflate(R.layout.dialog_category, null);
        ViewHolder holder = new ViewHolder(root);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) holder.contentLayout.getLayoutParams();
        params.width = ScreenUtils.getScreenWidth(mActivity);
        params.height = ScreenUtils.getScreenHeight(mActivity) / 2 + UiUtils.dp2px(mActivity, 40);
        holder.contentLayout.setLayoutParams(params);
        setContentView(root);
        adapter = new CategoryAdapter(categories);
        holder.categoryList.setAdapter(adapter);
        View header = View.inflate(mActivity, R.layout.item_textview, null);
        header.setLayoutParams(new MagicalRecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        holder.categoryList.addHeaderView(header);
    }

    static class ViewHolder {
        @BindView(R.id.category_list)
        MagicalRecyclerView categoryList;
        @BindView(R.id.content_layout)
        LinearLayout contentLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class CategoryAdapter extends CommonRcvAdapter<SongSheetCategory.ContentBean> {

        protected CategoryAdapter(List<SongSheetCategory.ContentBean> data) {
            super(data);
        }

        @NonNull
        @Override
        public AdapterItem<SongSheetCategory.ContentBean> getItemView(Object type) {
            return new CategoryItem();
        }
    }

    class CategoryItem extends SimpleItem<SongSheetCategory.ContentBean> {

        @BindView(R.id.category_type_tv)
        TextView categoryTypeTv;
        @BindView(R.id.recd_grid)
        MagicalRecyclerView recdGrid;
        @BindView(R.id.item)
        LinearLayout item;

        @Override
        public int getLayoutResId() {
            return R.layout.item_song_sheet_category;
        }

        @Override
        public void onUpdateViews(SongSheetCategory.ContentBean model, int position) {
            categoryTypeTv.setText(mActivity.getString(R.string.music_category_type_text, model.title, model.num + ""));
            TagsAdapter adapter = new TagsAdapter(model.tags);
            recdGrid.setAdapter(adapter);
        }
    }

    class TagsAdapter extends CommonRcvAdapter<SongSheetCategory.TagsBean> {

        protected TagsAdapter(List<SongSheetCategory.TagsBean> data) {
            super(data);
        }

        @NonNull
        @Override
        public AdapterItem<SongSheetCategory.TagsBean> getItemView(Object type) {
            return new TagsItem();
        }

    }

    class TagsItem extends SimpleItem<SongSheetCategory.TagsBean> {

        @BindView(R.id.content_tv)
        CircleAngleTitleView contentTv;

        @Override
        public int getLayoutResId() {
            return R.layout.item_textview;
        }

        @Override
        public void onUpdateViews(SongSheetCategory.TagsBean model, int position) {
            contentTv.setText(model.tag);
        }
    }
}
