package com.leyou.pojo.client;

import com.leyou.pojo.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhu
 * @date 2020/6/3 - 13:58
 */
@RequestMapping("category")
public interface CategoryClientServer {

    @RequestMapping("findCategoryById")
    public Category findCategoryById(@RequestParam("id") Long id);


}
