package com.hyjz.hnovel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.bean.MyCommentBean;
import com.hyjz.hnovel.utils.GlideUtils;
import com.hyjz.hnovel.utils.TimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyCommentAdapter extends BaseQuickAdapter<MyCommentBean.CommentListBean,BaseViewHolder> {

    public MyCommentAdapter() {
        super(R.layout.item_my_comment,null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCommentBean.CommentListBean item) {

        //评论头像
        GlideUtils.load(mContext, item.getCommentorHeadImg(), helper.getView(R.id.cv_comment));
        //VIP等级
        helper.setText(R.id.tv_comment_vip, item.getAgentLevelName());
        //评论内容
        helper.setText(R.id.tv_commnet_content, item.getContent());
        //评论时间
        helper.setText(R.id.tv_comment_time, TimeUtils.getMsgFormatTime(item.getCreateTime()));
        //评论的书籍
        helper.setText(R.id.tv_book_title, item.getBookName());
    }

}
