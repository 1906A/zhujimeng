package com.leyou.client;

import com.leyou.pojo.client.SpecGroupClientServer;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhu
 * @date 2020/6/4 - 20:17
 */
@FeignClient(name = "item-service")
public interface SpecGroupClient extends SpecGroupClientServer {

}
