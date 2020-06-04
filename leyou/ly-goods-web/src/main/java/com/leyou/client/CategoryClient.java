package com.leyou.client;

import com.leyou.pojo.client.CategoryClientServer;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhu
 * @date 2020/6/3 - 14:00
 */
@FeignClient(name = "item-service")
public interface CategoryClient extends CategoryClientServer {
}
