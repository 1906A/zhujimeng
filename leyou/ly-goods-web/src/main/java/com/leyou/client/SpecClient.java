package com.leyou.client;

import com.leyou.pojo.client.SpecClientServer;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhu
 * @date 2020/5/28 - 18:26
 */
@FeignClient(name = "item-service")
public interface SpecClient extends SpecClientServer {



}
