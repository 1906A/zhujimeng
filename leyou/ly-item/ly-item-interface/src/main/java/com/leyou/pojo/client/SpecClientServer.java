package com.leyou.pojo.client;

import com.leyou.pojo.SpecParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/28 - 18:25
 */
@RequestMapping("specParam")
public interface SpecClientServer {
    @RequestMapping("paramsByCid")
    public List<SpecParam> findSpecParamByCidAndSearching(@RequestParam("cid") Long cid);
}
