package com.hyjz.hnovel.ireader.widget.page;

/**
 * Created by newbiechen on 17-7-1.
 */

public class TxtChapter{
    private Integer chapterCoin;//: 0
    private Long chapterId;//: 96053
    private Integer chapterOrder;//: 1
    private String chapterTitle;//: "第1章青田遗书"
    private boolean isReaded;//: true
    private String updateTime;//: "2019-01-22 20:35:18"
    //章节所属的小说(网络)
    String bookId="53663ae356bdc93e49004474";
    //章节的链接(网络)
    String link="";

    //章节名(共用)
    String title="";

    //章节内容在文章中的起始位置(本地)
    long start=1;
    //章节内容在文章中的终止位置(本地)
    long end=45;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String id) {
        this.bookId = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "TxtChapter{" +
                "chapterCoin=" + chapterCoin +
                ", chapterId=" + chapterId +
                ", chapterOrder=" + chapterOrder +
                ", chapterTitle='" + chapterTitle + '\'' +
                ", isReaded=" + isReaded +
                ", updateTime='" + updateTime + '\'' +
                ", bookId='" + bookId + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
