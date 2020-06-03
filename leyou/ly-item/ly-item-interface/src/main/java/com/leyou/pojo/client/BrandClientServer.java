package com.leyou.pojo.client;

import com.leyou.pojo.Brand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhu
 * @date 2020/6/3 - 14:08
 */
@RequestMapping("brand")
public interface BrandClientServer {
    @RequestMapping("findBrandById")
    public Brand findBrandById(@RequestParam("id") Long id);
}
