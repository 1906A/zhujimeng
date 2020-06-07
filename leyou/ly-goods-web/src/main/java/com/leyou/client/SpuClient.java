package com.leyou.client;

import com.leyou.pojo.client.SpuClientServer;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhu
 * @date 2020/5/28 - 17:45
 */

@FeignClient(name = "item-service")
public interface SpuClient extends SpuClientServer {

}
