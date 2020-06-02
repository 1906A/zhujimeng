package com.leyou.controller;

import com.leyou.pojo.SpecParam;
import com.leyou.service.SpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/19 - 15:55
 */
@RestController
@RequestMapping("specParam")
public class SpecParamController {

    @Autowired
    private SpecParamService specParamService;

    @RequestMapping("param")
    public void saveSpecParam(@RequestBody SpecParam specParam){

        if(specParam.getId()==null){
            //新增规格参数组下的参数
            specParamService.saveSpecParam(specParam);
        }else {
            //修改规格参数组下的参数
            specParamService.updateSpecParam(specParam);
        }


    }

    //根据id删除规格参数组下的参数
    @RequestMapping("param/{id}")
    public void deleteById(@PathVariable("id") Long id){
        specParamService.deleteById(id);
    }
    //根据分类id查询规格参数集合
    @RequestMapping("params")
    public List<SpecParam> findSpecParamByCid(@RequestParam("cid") Long cid){
        return specParamService.findSpecParamByCid(cid);
    }
    //根据三级分类id+搜索条件为1的参数查询规格参数集合
    @RequestMapping("paramsByCid")
    public List<SpecParam> findSpecParamByCidAndSearching(@RequestParam("cid") Long cid){
        return specParamService.findSpecParamByCidAndSearching(cid);
    }
}
