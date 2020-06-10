package com.leyou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.PageResult;
import com.leyou.dao.SkuMapper;
import com.leyou.dao.SpuDetailMapper;
import com.leyou.dao.SpuMapper;
import com.leyou.dao.StockMapper;
import com.leyou.pojo.Sku;
import com.leyou.pojo.Spu;
import com.leyou.pojo.SpuDetail;
import com.leyou.pojo.Stock;
import com.leyou.pojo.vo.SpuVo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhu
 * @date 2020/5/19 - 17:44
 */
@Service
public class SpuService {
    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    //查询商品列表
    public PageResult<SpuVo> findSpuByPage(String key, Integer page, Integer rows, Integer saleable) {

        PageHelper.startPage(page,rows);

        PageInfo<SpuVo> list = new PageInfo<SpuVo>(spuMapper.findSpuPage(key,saleable));

        return new PageResult<SpuVo>(list.getTotal(),list.getList());
    }
    //保存商品信息
    public void saveSpuDetail(SpuVo spuVo) {
        Date date = new Date();
        //*
        //1.保存spu
        //2.保存spu_detail
        //3.保存sku
        //4.保存stock
        // */

        Spu spu = new Spu();
        spu.setTitle(spuVo.getTitle());
        spu.setSubTitle(spuVo.getSubTitle());
        spu.setBrandId(spuVo.getBrandId());
        spu.setCid1(spuVo.getCid1());
        spu.setCid2(spuVo.getCid2());
        spu.setCid3(spuVo.getCid3());

        //默认保存时不上架商品
        spu.setSaleable(false);
        spu.setValid(true);
        spu.setCreateTime(date);
        spu.setLastUpdateTime(date);

        spuMapper.insert(spu);

        //保存spu扩展表

        SpuDetail spuDetail = spuVo.getSpuDetail();
        spuDetail.setSpuId(spu.getId());

        spuDetailMapper.insert(spuDetail);
        /*SpuDetail spuDetail = new SpuDetail();
        spuDetail.setSpuId(spuVo.getId());
        spuDetail.setAfterService(spuVo.getSpuDetail().getAfterService());
        spuDetail.setDescription(spuVo.getSpuDetail().getDescription());
        spuDetail.setGenericSpec(spuVo.getSpuDetail().getGenericSpec());
        spuDetail.setPackingList(spuVo.getSpuDetail().getPackingList());*/





        //sku
        List<Sku> skus = spuVo.getSkus();
        skus.forEach(sku -> {
            sku.setSpuId(spu.getId());
            sku.setEnable(true);
            sku.setCreateTime(date);
            sku.setLastUpdateTime(date);

            skuMapper.insert(sku);


            //库存

            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockMapper.insert(stock);
        });

        //发送MQ消息
        //amqpTemplate.convertAndSend("item.exchange","item.insert",spu.getId());

        this.sendMsg("insert",spu.getId());



    }

    public void sendMsg(String type,Long spuId){
        //发送MQ消息
        amqpTemplate.convertAndSend("item.exchanges","item."+type,spuId);

    }





    //根据SpuId查询商品集列表
    public SpuDetail findSpuDetailBySpuId(Long spuId) {
        return spuDetailMapper.selectByPrimaryKey(spuId);
    }
    //修改商品信息
    public void UpdateSpuDetail(SpuVo spuVo) {
        Date date = new Date();
        //*
        //1.修改spu
        //2.修改spu_detail
        //3.修改sku
        //4.修改stock
        // */

        //1.修改spu
        spuVo.setCreateTime(null);
        spuVo.setLastUpdateTime(date);
        spuVo.setSaleable(null);
        spuVo.setValid(null);

        spuMapper.updateByPrimaryKeySelective(spuVo);


        //2.修改spu_detail

        SpuDetail spuDetail = spuVo.getSpuDetail();
        spuDetail.setSpuId(spuVo.getId());
        spuDetailMapper.updateByPrimaryKeySelective(spuDetail);


        //3.修改sku

        /*List<Sku> skus1 = spuVo.getSkus();
        skus1.forEach(s -> {
            //删除sku
            s.setEnable(false);

            skuMapper.updateByPrimaryKey(s);
            //库存
            stockMapper.deleteByPrimaryKey(s.getSpuId());
        });*/


        List<Sku> skuList = skuMapper.findSkusBySkuId(spuVo.getId());
        skuList.forEach(s ->{
            skuMapper.deleteByPrimaryKey(s.getId());
            stockMapper.deleteByPrimaryKey(s.getId());

        });


        //sku
            List<Sku> skus = spuVo.getSkus();
            skus.forEach(sku -> {
                sku.setSpuId(spuVo.getId());
                sku.setEnable(true);
                sku.setCreateTime(date);
                sku.setLastUpdateTime(date);

                skuMapper.insert(sku);


                //库存

                Stock stock = new Stock();
                stock.setSkuId(sku.getId());
                stock.setStock(sku.getStock());
                stockMapper.insert(stock);
            });


            //发送MQ消息
            this.sendMsg("update",spuVo.getId());

        }





    //根据spuId删除spu详情
    public void deleteBySpuId(Long spuId) {
        //思路：倒着删
        //1.删除spu
        //2.删除spu_detail    脏数据【无用数据】
        //3.删除sku
        //4.删除stock
        // */




        ///删除sku
        List<Sku> skuList = skuMapper.findSkusBySkuId(spuId);

        skuList.forEach(s -> {
            //删除sku
            s.setEnable(false);

            skuMapper.updateByPrimaryKeySelective(s);


            //库存
            stockMapper.deleteByPrimaryKey(s.getSpuId());
        });



        //删除spu_detail
        stockMapper.deleteByPrimaryKey(spuId);


        //删除spu
        spuMapper.deleteByPrimaryKey(spuId);

        //删除商品消息发送MQ
        this.sendMsg("delete",spuId);



    }



    //操作上下架状态
    public void upOrDown(Long spuId,int saleable) {

        Spu spu = new Spu();

        spu.setId(spuId);
        spu.setSaleable(saleable==1?true:false);

        spuMapper.updateByPrimaryKeySelective(spu);

    }

    //根据spuid查询spu
    public Spu findSpuById(Long spuId) {
       return spuMapper.selectByPrimaryKey(spuId);
    }



    public SpuVo findSpuBySpuId(Long spuId) {
        return spuMapper.findSpuBySpuId(spuId);

    }




}
