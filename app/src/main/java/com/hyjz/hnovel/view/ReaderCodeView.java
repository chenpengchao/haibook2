package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.BookRecommend;
import com.hyjz.hnovel.bean.ReaderCodeBean;

public interface ReaderCodeView  extends BaseView {
    void onShowReadCode(ReaderCodeBean bean);
    void onError();
}
