package com.hyjz.hnovel.adapter.hoder;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.adapter.ViewHolderImpl;
import com.hyjz.hnovel.ireader.utils.BookManager;
import com.hyjz.hnovel.ireader.widget.page.TxtChapter;


/**
 * Created by newbiechen on 17-5-16.
 */

public class CategoryHolder extends ViewHolderImpl<TxtChapter> {

    private TextView mTvChapter;
    private ImageView iv_lock;

    @Override
    public void initView() {
        mTvChapter = findById(R.id.category_tv_chapter);
        iv_lock = findById(R.id.iv_lock);
    }

    @Override
    public void onBind(TxtChapter value, int pos){
        //首先判断是否该章已下载
        Drawable drawable = null;

        //TODO:目录显示设计的有点不好，需要靠成员变量是否为null来判断。
        //如果没有链接地址表示是本地文件
        if (value.getLink() == null){
            drawable = ContextCompat.getDrawable(getContext(),R.drawable.selector_category_load);
        }
        else {
            if (value.getBookId() != null
                    && BookManager
                    .isChapterCached(value.getBookId(),value.getTitle())){
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.selector_category_load);
            }
            else {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.selector_category_unload);
            }
        }

        mTvChapter.setSelected(false);
        mTvChapter.setTextColor(ContextCompat.getColor(getContext(),R.color.nb_text_default));
        mTvChapter.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
        mTvChapter.setText(value.getTitle());
        if (value.getChapterCoin() > 0) {
            iv_lock.setVisibility(View.VISIBLE);
        } else {
            iv_lock.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_category;
    }

    public void setSelectedChapter(){
        mTvChapter.setTextColor(ContextCompat.getColor(getContext(),R.color.light_red));
        mTvChapter.setSelected(true);
    }
}
