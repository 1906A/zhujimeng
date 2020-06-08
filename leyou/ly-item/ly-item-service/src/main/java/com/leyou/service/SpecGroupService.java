package com.leyou.service;

import com.leyou.dao.SpecGroupMapper;
import com.leyou.dao.SpecParamMapper;

import com.leyou.pojo.SpecGroup;
import com.leyou.pojo.SpecParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhu
 * @date 2020/5/18 - 14:41
 */
@Service
public class SpecGroupService {

    @Autowired
    private SpecGroupMapper specGroupMapper;
    @Autowired
    private SpecParamMapper specParamMapper;

    //根据分类名称查询商品规格组列表
    public List<SpecGroup> findSpecGroupList(Long cateGoryId) {

        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cateGoryId);

        //根据分类id查询规格参数及组内规格参数

        List<SpecGroup> groupList = new ArrayList<>();

        groupList = specGroupMapper.select(specGroup);

        groupList.forEach(group->{
            SpecParam param = new SpecParam();

            param.setGroupId(group.getId());

            group.setParams(specParamMapper.select(param));

        });


        return groupList;

    }
    //保存商品规格组
    public void saveSpecGroup(SpecGroup specGroup) {
        specGroupMapper.insert(specGroup);
    }

    //根据id删除
    public void deleteById(Long id) {
        specGroupMapper.deleteByPrimaryKey(id);
    }
    //修改商品规格组
    public void updateSpecGroup(SpecGroup specGroup) {
        specGroupMapper.updateByPrimaryKey(specGroup);
    }


    //根据组id查询参数列表
    public List<SpecParam> findSpecParam(Long gid) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        return specParamMapper.select(specParam);
    }


}
