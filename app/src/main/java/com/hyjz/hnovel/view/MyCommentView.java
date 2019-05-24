package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.MyCommentBean;

public interface MyCommentView extends BaseView {
    void onCommentSuccess(MyCommentBean bean);
}
