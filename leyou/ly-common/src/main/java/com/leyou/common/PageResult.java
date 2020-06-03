package com.leyou.common;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/13 - 13:59
 */
public class PageResult<T> {
    //总条数
    private Long total;
    //数据
    private List<T> items;



    public PageResult(Long total, List<T> items, Integer totalPage) {
        this.total = total;
        this.items = items;
        this.totalPage = totalPage;
    }

    private Integer totalPage;


    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public PageResult(Long total) {
        this.total = total;
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
