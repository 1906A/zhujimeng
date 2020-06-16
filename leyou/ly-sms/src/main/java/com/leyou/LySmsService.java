package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhu
 * @date 2020/6/16 - 7:22
 */
@SpringBootApplication
@EnableEurekaClient
public class LySmsService {
    public static void main(String[] args) {
        SpringApplication.run(LySmsService.class,args);
    }
}
