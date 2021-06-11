package com.wyy.util;

public class PageSupport {
//    当前页码来自于用户输入
    private int currentPageNo = 1;

//    总行数（表）
    private int totalCount = 0;

//    页面容量
    private int pageSize = 0;

//    总页数
    private int totalPageCount = 1;

//    设置当前页码
    public void setCurrentPageNo(int currentPageNo){
        if(currentPageNo>0){
            this.currentPageNo = currentPageNo;
        }
    }
//    返回当前页码
    public  int getCurrentPageNo(){
        return currentPageNo;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
//            设置总页数
            this.setTotalPageCountByRs();
        }
    }
//        设置页面容量
    public void setPagesize(int pageSize){
            if(pageSize>0){
                this.pageSize = pageSize;
            }
        }
//        获取页面容量
    public int getPageSize(){
        return pageSize;
    }

//  设置总页数
    public void setTotalPageCount(int totalPageCount){
            this.totalPageCount = totalPageCount;
    }
    public int getTotalPageCount(){
        return totalPageCount;
    }
/***
 * 设置总页数，如果总行数不能被页面大小整除，则多余的行数要在新的一页展示
 * 所以总页数会加一
 */
    public void setTotalPageCountByRs(){
        if(this.totalCount%pageSize==0&&this.totalCount%pageSize<0){
            this.totalPageCount = this.totalCount / pageSize;
        }
        else if(this.totalCount%pageSize>0){
            this.totalPageCount = (this.totalCount /pageSize)+1;
        }
        else{
            this.totalPageCount = 0;
        }
    }

    }


