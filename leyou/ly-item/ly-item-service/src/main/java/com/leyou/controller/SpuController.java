package com.leyou.controller;

import com.leyou.common.PageResult;
import com.leyou.pojo.SpuDetail;
import com.leyou.pojo.vo.SpuVo;
import com.leyou.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhu
 * @date 2020/5/19 - 17:45
 */
@RestController
@RequestMapping("spu")
public class SpuController {

    @Autowired
    private SpuService spuService;

    //查询商品列表
    @RequestMapping("page")
    public PageResult<SpuVo> findSpuByPage(@RequestParam("key") String key,
                                           @RequestParam("page") Integer page,
                                           @RequestParam("rows") Integer rows,
                                           @RequestParam(required = false,value = "saleable") Integer saleable){

        return spuService.findSpuByPage(key,page,rows,saleable);

    }


    @RequestMapping("saveOrUpdateGoods")
    public void saveSpuDetail(@RequestBody SpuVo spuVo){
        if (spuVo!=null){
            //修改商品信息
            spuService.UpdateSpuDetail(spuVo);
        }else{
            //保存商品信息
            spuService.saveSpuDetail(spuVo);
        }

    }


        //根据SpuId查询商品集列表
    @RequestMapping("detail/{spuId}")
    public SpuDetail findSpuDetailBySpuId(@PathVariable("spuId") Long spuId){
       return spuService.findSpuDetailBySpuId(spuId);
    }

    //根据spuId删除spu详情
    @RequestMapping("deleteById/{spuId}")
    public void deleteBySpuId(@PathVariable("spuId") Long spuId){
        spuService.deleteBySpuId(spuId);
    }


    //操作上下架状态
    @RequestMapping("upOrDown")
    public void upOrDown(@RequestParam("spuId") Long spuId,@RequestParam("saleable") int saleable){
        System.out.println();
        spuService.upOrDown(spuId,saleable);
    }


}
