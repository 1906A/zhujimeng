package com.leyou.dao;

import com.leyou.pojo.Spu;
import com.leyou.pojo.vo.SpuVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author zhu
 * @date 2020/5/19 - 17:44
 */
@org.apache.ibatis.annotations.Mapper
public interface SpuMapper extends Mapper<Spu> {

    List<SpuVo> findSpuPage(@Param("key") String key,
                            @Param("saleable") Integer saleable);

}
