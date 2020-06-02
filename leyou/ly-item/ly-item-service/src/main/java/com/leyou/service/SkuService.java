package com.leyou.service;

import com.leyou.dao.SkuMapper;
import com.leyou.pojo.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/21 - 16:53
 */
@Service
public class SkuService {

    @Autowired
    private SkuMapper skuMapper;



    public List<Sku> findSkusBySkuId(Long id) {
        return skuMapper.findSkusBySkuId(id);
    }
}
