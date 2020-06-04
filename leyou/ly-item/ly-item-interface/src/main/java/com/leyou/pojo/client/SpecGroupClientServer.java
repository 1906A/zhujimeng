package com.leyou.pojo.client;

import com.leyou.pojo.SpecGroup;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zhu
 * @date 2020/6/4 - 20:15
 */
@RequestMapping("spec")
public interface SpecGroupClientServer {

    @RequestMapping("groups/{cid}")
    public List<SpecGroup> findSpecGroupList(@PathVariable("cid") Long cateGoryId);

}
