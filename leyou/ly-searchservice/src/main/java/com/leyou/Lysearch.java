package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhu
 * @date 2020/5/31 - 14:08
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Lysearch {
    public static void main(String[] args) {
        SpringApplication.run(Lysearch.class,args);
    }
}
