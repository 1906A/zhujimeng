package com.leyou.client;

import com.leyou.pojo.client.SkuClientServer;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhu
 * @date 2020/5/28 - 18:18
 */
@FeignClient(name = "item-service")
public interface SkuClient extends SkuClientServer {




}
