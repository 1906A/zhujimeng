package com.leyou.pojo.client;

import com.leyou.pojo.Sku;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/28 - 18:18
 */
@RequestMapping("sku")
public interface SkuClientServer {
    @RequestMapping("list")
    public List<Sku> findSkusBySkuId(@RequestParam("id") Long id);
}
