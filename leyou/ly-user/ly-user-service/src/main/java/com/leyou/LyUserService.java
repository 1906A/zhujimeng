package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhu
 * @date 2020/6/15 - 15:08
 */
@SpringBootApplication
//@EnableEurekaClient
@MapperScan("com.leyou.dao")
public class LyUserService {
    public static void main(String[] args) {
        SpringApplication.run(LyUserService.class,args);
    }
}
