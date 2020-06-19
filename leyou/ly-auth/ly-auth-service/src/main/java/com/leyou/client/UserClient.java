package com.leyou.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhu
 * @date 2020/6/18 - 12:04
 */
@FeignClient(name = "user-service")
public interface UserClient extends UserClientServer{

}
