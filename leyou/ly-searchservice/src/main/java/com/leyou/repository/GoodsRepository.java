package com.leyou.repository;

import com.leyou.item.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhu
 * @date 2020/5/28 - 18:50
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {

}
