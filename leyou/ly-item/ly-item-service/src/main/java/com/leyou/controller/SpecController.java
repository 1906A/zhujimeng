package com.leyou.controller;

import com.leyou.pojo.SpecGroup;
import com.leyou.pojo.SpecParam;
import com.leyou.service.SpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/18 - 14:39
 */
@RestController
@RequestMapping("spec")
public class SpecController {


    @Autowired
    private SpecGroupService specGroupService;

    //查询规格参数组列表
    @RequestMapping("groups/{cid}")
    public List<SpecGroup> findSpecGroupList(@PathVariable("cid") Long cateGoryId){
        return specGroupService.findSpecGroupList(cateGoryId);
    }




    //保存商品规格组
    @RequestMapping("group")
    public void saveSpecGroup(@RequestBody SpecGroup specGroup){

        System.out.println();
        //判断是修改还是新增
        if(specGroup.getId()==null){
            specGroupService.saveSpecGroup(specGroup);
        }else {
            specGroupService.updateSpecGroup(specGroup);
        }



    }

    //根据id删除
    @RequestMapping("group/{id}")
    public void deleteById(@PathVariable("id") Long id){
        specGroupService.deleteById(id);
    }



    @RequestMapping("params")
    public List<SpecParam> findSpecParam(@RequestParam("gid") Long gid){
        return specGroupService.findSpecParam(gid);

    }


}
