package com.leyou.dao;

import com.leyou.pojo.Category;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhu
 * @date 2020/5/11 - 15:42
 */
@org.apache.ibatis.annotations.Mapper
public interface CategoryMapper extends Mapper<Category> {

    public Category findCategoryById(int id);




}
