package com.leyou.service;

import com.leyou.dao.CategoryMapper;
import com.leyou.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/11 - 15:43
 */
@Service
public class CategoryService {


    @Autowired
    CategoryMapper categoryMapper;


    //根据节点id查询所有分类信息！
    public List<Category> findCategory(Category category){
        return categoryMapper.select(category);
    }

    //测试
    public Category findCategoryById(int id){
        return categoryMapper.findCategoryById(id);
    }
    //添加商品的分类
    public void cateGoryAdd(Category category) {
        categoryMapper.insertSelective(category);
    }
    //添加商品的分类
    public void cateGoryEdit(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }
    //删除商品的分类
    public void deleteById(Long id){
        Category category = new Category();
        category.setId(id);
        categoryMapper.deleteByPrimaryKey(category);
    }
    //根据分类id查询分类名称
    public Category findCategoryById(Long id) {
       return categoryMapper.selectByPrimaryKey(id);
    }
}
