package com.hyjz.hnovel.bean;

import java.util.ArrayList;
import java.util.List;

public class MyCommentBean {
    private Integer endRow;//: 10
    private Integer firstPage;//: 1
    private boolean hasNextPage;//: true
    private boolean hasPreviousPage;//: false
    private boolean isFirstPage;//: true
    private boolean isLastPage;//: false
    private Integer lastPage;//: 2
    private List<CommentListBean> list;//:// [{likeStaus: 0, lastChapterTitle: "第259章 去追啊（完）", bookStatus: 2, hasNew: false,…},…]
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

    public static class CommentListBean {
        private String agentLevelName;//: "嗨赚Lv2"
        private Integer agentLevelNo;//: 2
        private Integer auditStatus;//: 1
        private Long bookId;//: 1896
        private String bookName;//: "《异世之古道武帝》"
        private Long commentId;//: 45
        private Integer commentNum;//: 0
        private String commentorHeadImg;//: "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKoVn29tXiaGc3tCnovDOk5FXdPkA68iapZDKkm8zCyBXn7Xj8nOdVBxicQSSmWdF4ZtUguvVsGfnwbQ/132"
        private Long commentorId;//: 617
        private String commentorName;//: "达人君"
        private Integer commentorSex;//: 1
        private String content;//: "不错！"
        private Long createTime;//: 1555569566000
        private Integer isVip;//: 0
        private Long readDueTime;//: 1558155735000
        private String rejectReason;//: null
        private String title;//: null
        private Integer viewNum;//: 5

        public String getAgentLevelName() {
            return agentLevelName;
        }

        public void setAgentLevelName(String agentLevelName) {
            this.agentLevelName = agentLevelName;
        }

        public Integer getAgentLevelNo() {
            return agentLevelNo;
        }

        public void setAgentLevelNo(Integer agentLevelNo) {
            this.agentLevelNo = agentLevelNo;
        }

        public Integer getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(Integer auditStatus) {
            this.auditStatus = auditStatus;
        }

        public Long getBookId() {
            return bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public Long getCommentId() {
            return commentId;
        }

        public void setCommentId(Long commentId) {
            this.commentId = commentId;
        }

        public Integer getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Integer commentNum) {
            this.commentNum = commentNum;
        }

        public String getCommentorHeadImg() {
            return commentorHeadImg;
        }

        public void setCommentorHeadImg(String commentorHeadImg) {
            this.commentorHeadImg = commentorHeadImg;
        }

        public Long getCommentorId() {
            return commentorId;
        }

        public void setCommentorId(Long commentorId) {
            this.commentorId = commentorId;
        }

        public String getCommentorName() {
            return commentorName;
        }

        public void setCommentorName(String commentorName) {
            this.commentorName = commentorName;
        }

        public Integer getCommentorSex() {
            return commentorSex;
        }

        public void setCommentorSex(Integer commentorSex) {
            this.commentorSex = commentorSex;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public Integer getIsVip() {
            return isVip;
        }

        public void setIsVip(Integer isVip) {
            this.isVip = isVip;
        }

        public Long getReadDueTime() {
            return readDueTime;
        }

        public void setReadDueTime(Long readDueTime) {
            this.readDueTime = readDueTime;
        }

        public String getRejectReason() {
            return rejectReason;
        }

        public void setRejectReason(String rejectReason) {
            this.rejectReason = rejectReason;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getViewNum() {
            return viewNum;
        }

        public void setViewNum(Integer viewNum) {
            this.viewNum = viewNum;
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

    public List<CommentListBean> getList() {
        return list;
    }

    public void setList(List<CommentListBean> list) {
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
