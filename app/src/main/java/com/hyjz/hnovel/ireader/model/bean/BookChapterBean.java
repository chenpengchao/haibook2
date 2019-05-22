package com.hyjz.hnovel.ireader.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

import java.io.Serializable;

/**
 * Created by newbiechen on 17-5-10.
 * 书的章节链接(作为下载的进度数据)
 * 同时作为网络章节和本地章节 (没有找到更好分离两者的办法)
 */
@Entity
public class BookChapterBean implements Serializable{
    private static final long serialVersionUID = 56423411313L;
    /**
     * title : 第一章 他叫白小纯
     * link : http://read.qidian.com/chapter/rJgN8tJ_cVdRGoWu-UQg7Q2/6jr-buLIUJSaGfXRMrUjdw2
     * unreadble : false
     */
    @Id
    private String id;

    private String link;

    private String title;

    //所属的下载任务
    private String taskName;

    private boolean unreadble=false;
    private Integer chapterCoin;//: 0
    private Long chapterId;//: 96053
    private Integer chapterOrder;//: 1
    private String chapterTitle;//: "第1章青田遗书"
    private boolean isReaded;//: true
    private String updateTime;//: "2019-01-22 20:35:18"
    //所属的书籍
    @Index
    private String bookId;

    //本地书籍参数


    //在书籍文件中的起始位置
    private long start;

    //在书籍文件中的终止位置
    private long end;

    @Generated(hash = 837730292)
    public BookChapterBean(String id, String link, String title, String taskName,
            boolean unreadble, Integer chapterCoin, Long chapterId, Integer chapterOrder,
            String chapterTitle, boolean isReaded, String updateTime, String bookId,
            long start, long end) {
        this.id = id;
        this.link = link;
        this.title = title;
        this.taskName = taskName;
        this.unreadble = unreadble;
        this.chapterCoin = chapterCoin;
        this.chapterId = chapterId;
        this.chapterOrder = chapterOrder;
        this.chapterTitle = chapterTitle;
        this.isReaded = isReaded;
        this.updateTime = updateTime;
        this.bookId = bookId;
        this.start = start;
        this.end = end;
    }

    @Generated(hash = 853839616)
    public BookChapterBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isUnreadble() {
        return unreadble;
    }

    public void setUnreadble(boolean unreadble) {
        this.unreadble = unreadble;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean getUnreadble() {
        return this.unreadble;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getChapterCoin() {
        return chapterCoin;
    }

    public void setChapterCoin(Integer chapterCoin) {
        this.chapterCoin = chapterCoin;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getChapterOrder() {
        return chapterOrder;
    }

    public void setChapterOrder(Integer chapterOrder) {
        this.chapterOrder = chapterOrder;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public boolean isReaded() {
        return isReaded;
    }

    public void setReaded(boolean readed) {
        isReaded = readed;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BookChapterBean{" +
                "id='" + id + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", taskName='" + taskName + '\'' +
                ", unreadble=" + unreadble +
                ", bookId='" + bookId + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public boolean getIsReaded() {
        return this.isReaded;
    }

    public void setIsReaded(boolean isReaded) {
        this.isReaded = isReaded;
    }
}