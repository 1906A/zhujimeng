package com.leyou;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhu
 * @date 2020/6/21 - 17:23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Test
    public void test(){
        //System.out.println("00000010i203");

        BoundHashOperations<String, Object, Object> hashOps = stringRedisTemplate.boundHashOps("ly_carts");

        hashOps.put("skuId_123","{\"title\":\"小米手机\"}");

        System.out.println(hashOps);

    }
}
