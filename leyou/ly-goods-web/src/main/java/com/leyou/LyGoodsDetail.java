package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhu
 * @date 2020/6/4 - 18:40
 */
@SpringBootApplication
@EnableEurekaClient
@FeignClient
public class LyGoodsDetail {
    public static void main(String[] args) {
        SpringApplication.run(LyGoodsDetail.class,args);
    }
}
