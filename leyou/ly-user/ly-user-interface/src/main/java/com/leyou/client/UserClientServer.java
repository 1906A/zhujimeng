package com.leyou.client;

import com.leyou.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhu
 * @date 2020/6/18 - 10:28
 */
public interface UserClientServer {
    @GetMapping("/query")
    public User query(@RequestParam("username")String username, @RequestParam("password") String password);
}
