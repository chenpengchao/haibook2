package com.hyjz.hnovel.ireader.event;

import com.hyjz.hnovel.ireader.model.flag.BookDistillate;
import com.hyjz.hnovel.ireader.model.flag.BookSort;
import com.hyjz.hnovel.ireader.model.flag.BookType;

/**
 * Created by newbiechen on 17-4-21.
 */

public class SelectorEvent {

    public BookDistillate distillate;

    public BookType type;

    public BookSort sort;

    public SelectorEvent(BookDistillate distillate,
                         BookType type,
                         BookSort sort) {
        this.distillate = distillate;
        this.type = type;
        this.sort = sort;
    }
}
