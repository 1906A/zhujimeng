package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhu
 * @date 2020/6/17 - 15:55
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class LyAuthService {
    public static void main(String[] args) {
        SpringApplication.run(LyAuthService.class,args);
    }
}
