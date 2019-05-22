package com.hyjz.hnovel.ireader.model.flag;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.R;

/**
 * Created by newbiechen on 17-4-25.
 */

public enum FindType {
    TOP(R.string.nb_fragment_find_top,R.drawable.ic_section_top),
    TOPIC(R.string.nb_fragment_find_topic,R.drawable.ic_section_topic),
    SORT(R.string.nb_fragment_find_sort,R.drawable.ic_section_sort),
    LISTEN(R.string.nb_fragment_find_listen,R.drawable.ic_section_listen);
    ;
    private String typeName;
    private int iconId;

    private FindType(@StringRes int typeNameId, @DrawableRes int iconId){
        this.typeName = MyApp.getContext().getResources().getString(typeNameId);
        this.iconId = iconId;
    }

    public String getTypeName(){
        return typeName;
    }

    public int getIconId(){
        return iconId;
    }
}
