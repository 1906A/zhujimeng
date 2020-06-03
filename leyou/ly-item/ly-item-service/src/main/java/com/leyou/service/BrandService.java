package com.leyou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.PageResult;
import com.leyou.dao.BrandMapper;
import com.leyou.pojo.Brand;
import com.leyou.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/13 - 13:54
 */
@Service
public class BrandService {


    @Autowired
    BrandMapper brandMapper;

    public PageResult<Brand> findBrand(String key, Integer page, Integer rows, String sortBy, boolean desc) {
        //pageHelper
        PageHelper.startPage(page,rows);

        List<Brand> brandList = brandMapper.findBrand(key,sortBy,desc);
        System.out.println(brandList);
        PageInfo<Brand> pageInfo = new PageInfo<Brand>(brandList);

        return new PageResult<Brand>(pageInfo.getTotal(),pageInfo.getList());
    }


    //手写sql做分页

    /*(当前页码 - 1) * 条数 ==limit ？，？*/

    public PageResult<Brand> findBrandByLimit(String key, Integer page, Integer rows, String sortBy, boolean desc) {

        //查询总条数
        Long brandCount = brandMapper.findBrandCount(key,sortBy,desc);

        List<Brand> brandList = brandMapper.findBrandLimit(key,(page-1)*rows,rows,sortBy,desc);

        return new PageResult<Brand>(brandCount,brandList);
    }



    public void brandCategorySave(Brand brand, List<Long> cids){
        //保存brand
        brandMapper.insert(brand);
        //保存tb_category_brand

        cids.forEach(id ->{
            brandMapper.addBrandAndCategory(brand.getId(),id);
        });


    }


    public void deleteById(Long id) {

        //删除brand
        Brand brand = new Brand();
        brand.setId(id);
        brandMapper.deleteByPrimaryKey(brand);


        //删除关系表
        brandMapper.deleteBrand(id);
    }


    public List<Category> findCategoryByBrandId(Long pid) {

        return brandMapper.findCategoryByBrandId(pid);
    }

    public void updateBrand(Brand brand, List<Long> cids) {
        //修改品牌表
        brandMapper.updateByPrimaryKey(brand);
        //修改品牌和分类的关系表

        brandMapper.deleteBrand(brand.getId());

        cids.forEach(cid ->{
            brandMapper.addBrandAndCategory(brand.getId(),cid);
        });




    }

    public List<Brand> findBrandBycid(Long cid) {
       return brandMapper.findBrandBycid(cid);
    }
    //根据品牌id查询品牌对象
    public Brand findBrandById(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}
