package com.hyjz.hnovel.ireader.presenter.contract;

import com.hyjz.hnovel.ireader.model.bean.BookReviewBean;
import com.hyjz.hnovel.ireader.model.flag.BookDistillate;
import com.hyjz.hnovel.ireader.model.flag.BookSort;
import com.hyjz.hnovel.ireader.model.flag.BookType;
import com.hyjz.hnovel.base.BaseContract;

import java.util.List;

/**
 * Created by newbiechen on 17-4-21.
 */

public interface DiscReviewContract {
    interface View extends BaseContract.BaseView {
        void finishRefresh(List<BookReviewBean> beans);
        void finishLoading(List<BookReviewBean> beans);
        void showErrorTip();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void firstLoading(BookSort sort, BookType bookType, int start, int limited, BookDistillate distillate);
        void refreshBookReview(BookSort sort, BookType bookType, int start, int limited, BookDistillate distillate);
        void loadingBookReview(BookSort sort, BookType bookType, int start, int limited, BookDistillate distillate);
        void saveBookReview(List<BookReviewBean> beans);
    }
}
