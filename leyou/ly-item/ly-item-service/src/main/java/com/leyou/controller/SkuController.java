package com.leyou.controller;

import com.leyou.pojo.Sku;
import com.leyou.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/21 - 16:53
 */
@RestController
@RequestMapping("sku")
public class SkuController {

    @Autowired
    private SkuService skuService;

    //根据skuId查询商品集合
    @RequestMapping("list")
    public List<Sku> findSkusBySkuId(@RequestParam("id") Long id){
        return skuService.findSkusBySkuId(id);
    }

}
