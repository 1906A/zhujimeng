package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhu
 * @date 2020/6/21 - 17:26
 */
@SpringBootApplication
@EnableEurekaClient

public class LyCartServicce {
    public static void main(String[] args) {
        SpringApplication.run(LyCartServicce.class,args);
    }
}
