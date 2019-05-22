package com.hyjz.hnovel.adapter;


import com.hyjz.hnovel.adapter.hoder.CollBookHolder;
import com.hyjz.hnovel.base.adapter.IViewHolder;
import com.hyjz.hnovel.ireader.model.bean.CollBookBean;
import com.hyjz.hnovel.ireader.widget.adapter.WholeAdapter;

/**
 * Created by newbiechen on 17-5-8.
 */

public class CollBookAdapter extends WholeAdapter<CollBookBean> {

    @Override
    protected IViewHolder<CollBookBean> createViewHolder(int viewType) {
        return new CollBookHolder();
    }

}
