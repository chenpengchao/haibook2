package com.hyjz.hnovel.bean;

import java.util.ArrayList;
import java.util.List;

public class ReaderCodeBean {
    private Integer endRow;//: 10
    private Integer firstPage;//: 1
    private boolean hasNextPage;//: true
    private boolean hasPreviousPage;//: false
    private boolean isFirstPage;//: true
    private boolean isLastPage;//: false
    private Integer lastPage;//: 2
    private List<ReadCodeListBean> list;//:// [{likeStaus: 0, lastChapterTitle: "第259章 去追啊（完）", bookStatus: 2, hasNew: false,…},…]
    private Integer navigateFirstPage;//: 1
    private Integer navigateLastPage;//: 2
    private Integer navigatePages;//: 8
    private List<Integer> navigatepageNums = new ArrayList<>();//: [1, 2]
    private Integer nextPage;//: 2
    private String orderBy;//: null
    private Integer pageNum;//: 1
    private Integer pageSize;//: 10
    private Integer pages;//: 2
    private Integer prePage;//: 0
    private Integer size;//: 10
    private Integer startRow;//: 1
    private Integer total;//: 19

    public static class ReadCodeListBean {
        private String authorName;//: "拾巳"
        private String bookCover;//: "http://kwayxiaoshuo.oss-cn-beijing.aliyuncs.com/b6df6121-80a2-417e-be04-88a4548646cd"
        private Integer bookId;//: 1502
        private String bookName;//: "乡村奇门医圣"
        private Integer bookStatus;//: 2
        private boolean hasNew;//: false
        private String lastChapterTitle;//: "第259章 去追啊（完）"
        private Integer likeStaus;//: 0
        private Integer readLikeId;//: 1995
        private String readingChapterOrder;//: "阅读至5章"
        private String updateTime;//: "更新于4个月前"

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getBookCover() {
            return bookCover;
        }

        public void setBookCover(String bookCover) {
            this.bookCover = bookCover;
        }

        public Integer getBookId() {
            return bookId;
        }

        public void setBookId(Integer bookId) {
            this.bookId = bookId;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public Integer getBookStatus() {
            return bookStatus;
        }

        public void setBookStatus(Integer bookStatus) {
            this.bookStatus = bookStatus;
        }

        public boolean isHasNew() {
            return hasNew;
        }

        public void setHasNew(boolean hasNew) {
            this.hasNew = hasNew;
        }

        public String getLastChapterTitle() {
            return lastChapterTitle;
        }

        public void setLastChapterTitle(String lastChapterTitle) {
            this.lastChapterTitle = lastChapterTitle;
        }

        public Integer getLikeStaus() {
            return likeStaus;
        }

        public void setLikeStaus(Integer likeStaus) {
            this.likeStaus = likeStaus;
        }

        public Integer getReadLikeId() {
            return readLikeId;
        }

        public void setReadLikeId(Integer readLikeId) {
            this.readLikeId = readLikeId;
        }

        public String getReadingChapterOrder() {
            return readingChapterOrder;
        }

        public void setReadingChapterOrder(String readingChapterOrder) {
            this.readingChapterOrder = readingChapterOrder;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Integer getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public List<ReadCodeListBean> getList() {
        return list;
    }

    public void setList(List<ReadCodeListBean> list) {
        this.list = list;
    }

    public Integer getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(Integer navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public Integer getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(Integer navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public Integer getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(Integer navigatePages) {
        this.navigatePages = navigatePages;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
