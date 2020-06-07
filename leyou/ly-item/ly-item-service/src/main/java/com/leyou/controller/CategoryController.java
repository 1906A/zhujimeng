package com.leyou.controller;

import com.leyou.pojo.Category;
import com.leyou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/11 - 15:43
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //利用通过Mapper的查询方法
    //通过节点id查询
    @RequestMapping("list")
    public List<Category> list(@RequestParam("pid") long pid){
        Category category = new Category();
        category.setParentId(pid);
        return categoryService.findCategory(category);
    }
    //测试根据id查询
    @RequestMapping("index")
    public Object findCategoryById(){
        return categoryService.findCategoryById(1);
    }
    //添加商品的分类
    @RequestMapping("add")
    public String add(@RequestBody Category category){
        String result="SUCC";
        try {
            categoryService.cateGoryAdd(category);

        }catch (Exception e){

            System.out.println("添加商品分类异常！");
            result="FAIL";
        }
        return result;
    }

    //修改商品的分类
    @RequestMapping("update")
    public String update(@RequestBody Category category){
        String result="SUCC";
        try {
            categoryService.cateGoryEdit(category);

        }catch (Exception e){

            System.out.println("修改商品分类异常！");
            result="FAIL";
        }
        return result;
    }


    //删除 商品的分类
    @RequestMapping("deleteById")
    public String deleteById(@RequestParam("id") Long id){
        String result="SUCC";
        try {
            categoryService.deleteById(id);
        }catch (Exception e){
            System.out.println("删除商品分类异常！");
            result="FAIL";
        }
        return result;
    }

    //根据分类id查询分类名称
    @RequestMapping("findCategoryById")
    public Category findCategoryById(@RequestParam("id") Long id){
       return categoryService.findCategoryById(id);
    }

    //根据分类id查询分类名称
    @RequestMapping("findCategoryByCids")
    public List<Category> findCategoryByCids(@RequestBody List<Long> ids){
        return categoryService.findCategoryByCids(ids);
    }

}
