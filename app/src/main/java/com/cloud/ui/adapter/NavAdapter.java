package com.cloud.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.NavModel;
import com.cloud.utils.Constants;
import com.magical.library.utils.PrefManager;
import com.magical.library.view.recycler.AdapterItem;
import com.magical.library.view.recycler.dapter.CommonRcvAdapter;
import com.magical.library.view.recycler.SimpleItem;
import com.magical.library.view.ripple.RippleRelativeLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: NavAdapter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/3/17 3:00 PM
 * Editor: ldy
 * Modify Date: 3/3/17 3:00 PM
 * Remark:
 */
public class NavAdapter extends CommonRcvAdapter<NavModel> {

    Context mContext;

    @Inject
    public NavAdapter(Context mContext, List<NavModel> data) {
        super(data);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AdapterItem<NavModel> getItemView(Object type) {
        return new NavItem();
    }

    class NavItem extends SimpleItem<NavModel> {

        @BindView(R.id.item_nav_icon)
        ImageView itemNavIcon;
        @BindView(R.id.item_nav_name)
        TextView itemNavName;
        @BindView(R.id.item)
        RippleRelativeLayout item;

        @Override
        public int getLayoutResId() {
            return R.layout.item_nav;
        }

        @Override
        public void onUpdateViews(NavModel model, int position) {
            if (model == null) {
                return;
            }
            itemNavName.setText(model.navName);
            itemNavIcon.setImageResource(model.navResId);
            int curType = PrefManager.getInstance().getIntFromPrefs(mContext, Constants.NAV_TAG, -1);
            if (curType != -1) {
                if (curType == model.navType) {
                    itemNavName.setTextColor(mContext.getResources().getColor(R.color.green));
                } else {
                    itemNavName.setTextColor(mContext.getResources().getColor(R.color.black));
                }
            } else {
                itemNavName.setTextColor(mContext.getResources().getColor(R.color.black));
            }
        }
    }
}
