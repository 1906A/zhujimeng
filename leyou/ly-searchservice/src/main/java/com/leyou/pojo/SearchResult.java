package com.leyou.pojo;

import com.leyou.common.PageResult;
import com.leyou.item.Goods;

import java.util.List;
import java.util.Map;

/**
 * @author zhu
 * @date 2020/6/3 - 14:14
 */
public class SearchResult extends PageResult<Goods> {


    private List<Category> categoryList;

    private List<Brand> brandList;

    private List<Map<String,Object>> paramList;


    public SearchResult(Long total, List<Goods> items, Integer totalPage,
                        List<Category> categoryList, List<Brand> brandList,List<Map<String,Object>> paramList) {
        super(total, items, totalPage);
        this.categoryList = categoryList;
        this.brandList = brandList;
        this.paramList = paramList;
    }

    public List<Map<String, Object>> getParamList() {
        return paramList;
    }

    public void setParamList(List<Map<String, Object>> paramList) {
        this.paramList = paramList;
    }

    public SearchResult(Long total, List<Goods> items, Integer totalPage) {
        super(total, items, totalPage);
    }

    public SearchResult(Long total) {
        super(total);
    }

    public SearchResult(Long total, List<Goods> items) {
        super(total, items);
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }
}
