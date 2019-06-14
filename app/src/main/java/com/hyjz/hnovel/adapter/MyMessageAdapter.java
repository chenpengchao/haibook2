package com.hyjz.hnovel.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.bean.MyMessageBean;
import com.hyjz.hnovel.bean.ReaderCodeBean;
import com.hyjz.hnovel.utils.GlideUtils;
import com.hyjz.hnovel.weight.CircleImageView;

public class MyMessageAdapter extends BaseQuickAdapter<MyMessageBean.MessageListBean,BaseViewHolder> {
    CircleImageView cv;
    public MyMessageAdapter() {
        super(R.layout.item_my_message_list, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMessageBean.MessageListBean item) {
        cv = helper.getView(R.id.cv_message);
        cv.setImageResource( R.mipmap.systemnotice);
        //标题
        helper.setText(R.id.tv_message_title, item.getInformName()+"");

        //时间
        helper.setText(R.id.tv_message_time, item.getInformTime()+"");

        //内容
        helper.setText(R.id.tv_message_content, item.getInformContent()+ "");

    }
}
