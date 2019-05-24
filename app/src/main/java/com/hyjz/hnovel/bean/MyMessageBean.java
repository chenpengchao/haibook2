package com.hyjz.hnovel.bean;

import java.util.ArrayList;
import java.util.List;

public class MyMessageBean {
    private Integer endRow;//: 10
    private Integer firstPage;//: 1
    private boolean hasNextPage;//: true
    private boolean hasPreviousPage;//: false
    private boolean isFirstPage;//: true
    private boolean isLastPage;//: false
    private Integer lastPage;//: 2
    private List<MessageListBean> list;//:// [{likeStaus: 0, lastChapterTitle: "第259章 去追啊（完）", bookStatus: 2, hasNew: false,…},…]
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

    public static class MessageListBean {
        private String informContent;//: "系统更新"
        private Long informId;//: 1
        private String informName;//: "系统通知"
        private Integer informStatus;//: 1
        private String informTime;//: "2019-01-15 15:10:12"
        private Integer informType;//: 1

        public String getInformContent() {
            return informContent;
        }

        public void setInformContent(String informContent) {
            this.informContent = informContent;
        }

        public Long getInformId() {
            return informId;
        }

        public void setInformId(Long informId) {
            this.informId = informId;
        }

        public String getInformName() {
            return informName;
        }

        public void setInformName(String informName) {
            this.informName = informName;
        }

        public Integer getInformStatus() {
            return informStatus;
        }

        public void setInformStatus(Integer informStatus) {
            this.informStatus = informStatus;
        }

        public String getInformTime() {
            return informTime;
        }

        public void setInformTime(String informTime) {
            this.informTime = informTime;
        }

        public Integer getInformType() {
            return informType;
        }

        public void setInformType(Integer informType) {
            this.informType = informType;
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

    public List<MessageListBean> getList() {
        return list;
    }

    public void setList(List<MessageListBean> list) {
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
