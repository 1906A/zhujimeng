package com.leyou.controller;

import com.leyou.common.PageResult;
import com.leyou.pojo.Brand;
import com.leyou.pojo.Category;
import com.leyou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/13 - 13:48
 */
@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    //品牌列表分页查询
    @RequestMapping("page")
    public Object findBrandByPage(@RequestParam("key") String key,
                                  @RequestParam("page") Integer page,
                                  @RequestParam("rows") Integer rows,
                                  @RequestParam("sortBy") String sortBy,
                                  @RequestParam("desc") boolean desc){

        System.out.println(key+"=="+page+"=="+rows+"=="+sortBy+"==="+desc);
        System.out.println("================111111111111111111111");
        //PageResult<Brand> brandList = brandService.findBrand(key,page,rows,sortBy,desc);
        PageResult<Brand> brandList = brandService.findBrandByLimit(key,page,rows,sortBy,desc);
        System.out.println("0000000000000000000000000000000000000");
        return brandList;

    }

    @RequestMapping("addOrEditBrand")
    public void addOrEditBrand(Brand brand,
                               @RequestParam(required = false,value = "cids") List<Long> cids){


        //判断主键id   是否有值
        if(brand.getId()!=null){
            //修改
            brandService.updateBrand(brand,cids);
        }else {
            brandService.brandCategorySave(brand,cids);

        }




    }
    //根据品牌id删除
    @RequestMapping("deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id){
        brandService.deleteById(id);
    }


    //根据品牌id查询具体分类
    @RequestMapping("bid/{id}")
    public List<Category> findCategoryByBrandId(@PathVariable("id") Long pid){

        return brandService.findCategoryByBrandId(pid);
    }

    //根据分类id查询对应的品牌
    @RequestMapping("cid/{cid}")
    public List<Brand> findBrandBycid(@PathVariable("cid") Long cid){
        return brandService.findBrandBycid(cid);
    }


}
