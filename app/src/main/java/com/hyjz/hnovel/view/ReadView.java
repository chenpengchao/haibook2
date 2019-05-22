package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.ReadBean;
import com.hyjz.hnovel.ireader.model.bean.BookChapterBean;

import java.util.List;

public interface ReadView extends BaseView {
    void showChapterInfo(ReadBean bean);
    void showCategory(List<BookChapterBean> bookChapterList);
    void showChapterList();
    void finishChapter();
    void errorChapter();
}
