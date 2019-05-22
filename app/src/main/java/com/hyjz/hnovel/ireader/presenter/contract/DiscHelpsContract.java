package com.hyjz.hnovel.ireader.presenter.contract;

import com.hyjz.hnovel.base.BaseContract;
import com.hyjz.hnovel.ireader.model.bean.BookHelpsBean;
import com.hyjz.hnovel.ireader.model.flag.BookDistillate;
import com.hyjz.hnovel.ireader.model.flag.BookSort;


import java.util.List;

/**
 * Created by newbiechen on 17-4-21.
 */

public interface DiscHelpsContract {

    interface View extends BaseContract.BaseView{
        void finishRefresh(List<BookHelpsBean> beans);
        void finishLoading(List<BookHelpsBean> beans);
        void showErrorTip();
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void firstLoading(BookSort sort, int start, int limited, BookDistillate distillate);
        void refreshBookHelps(BookSort sort, int start, int limited, BookDistillate distillate);
        void loadingBookHelps(BookSort sort, int start, int limited, BookDistillate distillate);
        void saveBookHelps(List<BookHelpsBean> beans);
    }
}
