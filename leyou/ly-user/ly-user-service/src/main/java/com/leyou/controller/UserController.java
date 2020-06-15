package com.leyou.controller;

import com.leyou.pojo.User;
import com.leyou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhu
 * @date 2020/6/15 - 15:29
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;


     //实现用户数据的校验，主要包括对：手机号、用户名的唯一性校验。
     @GetMapping("/check/{data}/{type}")
     public Boolean check(@PathVariable("data") String data,@PathVariable("type") Integer type){
         System.out.println("校验"+data+"=="+type);
         return userService.check(data,type);
     }
     //根据用户输入的手机号，生成随机验证码
     @PostMapping("/code")
     public void code(@RequestParam("phone") String phone){
         System.out.println("Code"+phone);

         //1.生成一个六位数的随机码    code


         //2.调用短信服务发送验证码    phone,code


         //3.



     }
    //用户注册
     @PostMapping("/register")
     public void register(User user,String code){
         System.out.println("用户注册："+user.getUsername()+"Code="+code);
     }
    //根据用户名和密码查询用户
     @GetMapping("/query")
     public User query(@RequestParam("username")String username,@RequestParam("password")String password){
         System.out.println("用户注册：username"+username+"密码="+password);
         return new User();
     }
}
