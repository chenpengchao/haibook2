package com.hyjz.hnovel.ireader.presenter.contract;

import com.hyjz.hnovel.ireader.model.bean.packages.BookSortPackage;
import com.hyjz.hnovel.ireader.model.bean.packages.BookSubSortPackage;
import com.hyjz.hnovel.base.BaseContract;

/**
 * Created by newbiechen on 17-4-23.
 */

public interface BookSortContract {

    interface View extends BaseContract.BaseView{
        void finishRefresh(BookSortPackage sortPackage, BookSubSortPackage subSortPackage);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void refreshSortBean();
    }
}
