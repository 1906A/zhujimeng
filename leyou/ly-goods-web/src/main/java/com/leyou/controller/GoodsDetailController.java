package com.leyou.controller;

import com.leyou.client.SkuClient;
import com.leyou.client.SpecGroupClient;
import com.leyou.client.SpuClient;
import com.leyou.pojo.Sku;
import com.leyou.pojo.SpecGroup;
import com.leyou.pojo.Spu;
import com.leyou.pojo.SpuDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zhu
 * @date 2020/6/4 - 18:44
 */
@Controller
public class GoodsDetailController {

    @Autowired
    SpuClient spuClient;

    @Autowired
    SkuClient skuClient;

    @Autowired
    SpecGroupClient specGroupClient;


    @RequestMapping("hello")
    public String hello(Model model){

        String name = "张安";

        model.addAttribute("name",name);

        return "hello";
    }

    /*


            1：spu
            2：spudetail
            3：sku
            4：规格参数组
            5：规格参数详情
            6：三级分类

    */
    //请求商品详情微服务
    @RequestMapping("item/{spuId}.html")
    public String item(@PathVariable("spuId") Long spuId,Model model){



        //1.spu
        Spu spu = spuClient.findSpuById(spuId);
        model.addAttribute("spu",spu);

        //2：spudetail
        SpuDetail spuDetail = spuClient.findSpuDetailBySpuId(spuId);
        model.addAttribute("spuDetail",spuDetail);

        //3.sku
        List<Sku> skuList = skuClient.findSkusBySkuId(spuId);
        model.addAttribute("skuList",skuList);

        //4：规格参数组
        List<SpecGroup> specGroupList = specGroupClient.findSpecGroupList(spu.getCid3());
        model.addAttribute("specGroupList",specGroupList);

        //5：规格参数详情


        //6：三级分类



        return "item";
    }


}
