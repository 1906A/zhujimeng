package com.leyou.controller;


import com.leyou.common.PageResult;
import com.leyou.item.Goods;
import com.leyou.pojo.SearchRequest;
import com.leyou.repository.GoodsRepository;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhu
 * @date 2020/5/31 - 11:36
 */
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    GoodsRepository goodsRepository;

    @RequestMapping("page")
    public PageResult<Goods> page(@RequestBody SearchRequest searchRequest){
        System.out.println(searchRequest.getKey()+"----------"+searchRequest.getPage());

        //去es索引库做查询
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //构造条件  从es all 中搜索
        builder.withQuery(QueryBuilders.matchQuery("all",searchRequest.getKey()).operator(Operator.AND));
        //分页查询
        builder.withPageable(PageRequest.of(searchRequest.getPage()-1,searchRequest.getSize()));
        //执行查询   TotalElements总条数   TotalPages总页数   Content数据内容
        Page<Goods> search = goodsRepository.search(builder.build());

        return new PageResult<Goods>(search.getTotalElements(), search.getContent(), search.getTotalPages());

    }



}
