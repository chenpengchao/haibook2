package com.hyjz.hnovel.adapter;

import android.view.View;
import android.view.ViewGroup;


import com.hyjz.hnovel.adapter.hoder.CategoryHolder;
import com.hyjz.hnovel.base.EasyAdapter;

import com.hyjz.hnovel.base.adapter.IViewHolder;
import com.hyjz.hnovel.ireader.widget.page.TxtChapter;

/**
 * Created by newbiechen on 17-6-5.
 */

public class CategoryAdapter extends EasyAdapter<TxtChapter> {
    private int currentSelected = 0;
    @Override
    protected IViewHolder<TxtChapter> onCreateViewHolder(int viewType) {
        return new CategoryHolder();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        CategoryHolder holder = (CategoryHolder) view.getTag();

        if (position == currentSelected){
            holder.setSelectedChapter();
        }

        return view;
    }

    public void setChapter(int pos){
        currentSelected = pos;
        notifyDataSetChanged();
    }
}
