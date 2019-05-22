package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.BookDetailBean1;

public interface BookDetailView extends BaseView {
    void success(BookDetailBean1 bean);

    void success1(com.hyjz.hnovel.ireader.model.bean.BookDetailBean bookDetailBean);
}
