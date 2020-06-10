package com.leyou.service;

import com.leyou.client.*;
import com.leyou.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhu
 * @date 2020/6/10 - 18:14
 */
@Service
public class GoodService {
    @Autowired
    SpuClient spuClient;

    @Autowired
    SkuClient skuClient;

    @Autowired
    SpecGroupClient specGroupClient;

    @Autowired
    CategoryClient categoryClient;

    @Autowired
    SpecClient specClient;

    @Autowired
    BrandClient brandClient;

    @Autowired
    TemplateEngine templateEngine;

    public void editThymeleafData(Long spuId) {



    }


    public Map<String, Object> item(Long spuId){
        //1.spu
        Spu spu = spuClient.findSpuById(spuId);

        //2：spudetail
        SpuDetail spuDetail = spuClient.findSpuDetailBySpuId(spuId);

        //7.查询品牌
        Brand brand = brandClient.findBrandById(spu.getBrandId());

        //3.sku
        List<Sku> skuList = skuClient.findSkusBySkuId(spuId);

        //4：规格参数组及组内信息
        List<SpecGroup> groups = specGroupClient.findSpecGroupList(spu.getCid3());


        //5：规格参数详情
        List<Category> categoryList = categoryClient.findCategoryByCids(
                Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));

        //6：参数中的规格参数
        List<SpecParam> specParamList = specClient.findParamsByCidGeneric(spu.getCid3(), false);

        //7.规格参数的特殊属性
        Map<Long,String> paramMap = new HashMap<>();
        specParamList.forEach(param->{
            paramMap.put(param.getId(),param.getName());
        });


        Map<String, Object> map = new HashMap<>();

        map.put("spu",spu);
        map.put("spuDetail",spuDetail);
        map.put("categoryList",categoryList);
        map.put("brand",brand);
        map.put("skuList",skuList);
        map.put("groups",groups);
        map.put("paramMap",paramMap);

        return map;
    }

    //创建静态页面
    public void creatHtml(Long spuId) {


        PrintWriter writer = null;
        try {
            //1.创建上下文
            Context context = new Context();

            //2.把数据放入上下文中

            /*context.setVariable("spu",spu);
            context.setVariable("spuDetail",spuDetail);
            context.setVariable("categoryList",categoryList);
            context.setVariable("brand",brand);
            context.setVariable("skuList",skuList);
            context.setVariable("groups",groups);
            context.setVariable("paramMap",paramMap);*/
            context.setVariables(this.item(spuId));
            //3.写入文件    写入流
            File file = new File("F:\\Java\\nginx-1.16.1\\html\\"+spuId+".html");

            writer = new PrintWriter(file);


            //执行静态化
            templateEngine.process("item",context,writer);


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer!=null){
                //5.关闭写入流
                writer.close();
            }
        }


    }
    //删除静态页面
    public void deleteHtml(Long spuId) {

        File file = new File("F:\\Java\\nginx-1.16.1\\html\\"+spuId+".html");
        if (file!=null && file.exists()){
            file.delete();
        }
    }
}
