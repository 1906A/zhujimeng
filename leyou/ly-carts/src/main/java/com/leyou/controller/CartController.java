package com.leyou.controller;

import com.leyou.pojo.Sku;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhu
 * @date 2020/6/21 - 17:55
 */
@RestController
public class CartController {


    @RequestMapping("add")
    public void add(@RequestBody Sku sku){

    }

    @RequestMapping("update")
    public void update(@RequestBody Sku sku){

    }
    @RequestMapping("delete")
    public void delete(@RequestParam("id") Long id){

    }
    @RequestMapping("query")
    public void query(@RequestBody Sku sku){

    }
}
