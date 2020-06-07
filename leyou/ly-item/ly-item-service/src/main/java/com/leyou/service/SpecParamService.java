package com.leyou.service;

import com.leyou.dao.SpecParamMapper;
import com.leyou.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/19 - 13:37
 */
@Service
public class SpecParamService {

    @Autowired
    private SpecParamMapper specParamMapper;

    //新增规格参数组下的参数
    public void saveSpecParam(SpecParam specParam) {
        specParamMapper.insert(specParam);
    }
    //修改规格参数组下的参数
    public void updateSpecParam(SpecParam specParam) {
        specParamMapper.updateByPrimaryKey(specParam);
    }
    //根据id删除规格参数组下的参数
    public void deleteById(Long id) {
        specParamMapper.deleteByPrimaryKey(id);
    }
    //根据分类id查询规格参数集合
    public List<SpecParam> findSpecParamByCid(Long cid) {
        return specParamMapper.findSpecParamByCid(cid);
    }
    //根据三级分类id+搜索条件为1的参数查询规格参数集合
    public List<SpecParam> findSpecParamByCidAndSearching(Long cid) {
        SpecParam specParam = new SpecParam();
        specParam.setCid(cid);
        specParam.setSearching(true);
        return specParamMapper.select(specParam);
    }
    //根据三级分类id+是否通用参数值查询
    public List<SpecParam> findParamsByCidGeneric(Long cid, boolean generic) {
        SpecParam specParam = new SpecParam();
        specParam.setCid(cid);
        specParam.setGeneric(generic);
        return specParamMapper.select(specParam);
    }
}
