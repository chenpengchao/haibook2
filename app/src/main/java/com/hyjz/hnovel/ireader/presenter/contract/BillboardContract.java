package com.hyjz.hnovel.ireader.presenter.contract;

import com.hyjz.hnovel.base.BaseContract;
import com.hyjz.hnovel.ireader.model.bean.packages.BillboardPackage;


/**
 * Created by newbiechen on 17-4-23.
 */

public interface BillboardContract {

    interface View extends BaseContract.BaseView{
        void finishRefresh(BillboardPackage beans);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void loadBillboardList();
    }
}
