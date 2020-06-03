package com.leyou.client;

import com.leyou.pojo.client.BrandClientServer;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhu
 * @date 2020/6/3 - 14:09
 */
@FeignClient(name = "item-service")
public interface BrandClient extends BrandClientServer {
}
