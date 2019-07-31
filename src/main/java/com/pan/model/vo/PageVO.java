package com.pan.model.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Pangaofeng on 2018/8/29
 */
public class PageVO {
    @ApiModelProperty(value = "是否分页1分页2不分页")
    private int isPage;
    @ApiModelProperty(value = "页码")
    private int pageNo;
    @ApiModelProperty(value = "条数")
    private int pageSize;

    public int getIsPage() {
        return isPage;
    }

    public void setIsPage(int isPage) {
        this.isPage = isPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "isPage=" + isPage +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
