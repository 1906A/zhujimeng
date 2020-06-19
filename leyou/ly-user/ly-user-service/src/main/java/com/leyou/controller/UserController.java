package com.leyou.controller;

import com.leyou.pojo.User;
import com.leyou.service.UserService;
import com.leyou.utils.CodeUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhu
 * @date 2020/6/15 - 15:29
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

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

         //1.生成一个六位数的随机码     code
        String code = CodeUtils.messageCode(6);


         //2.使用rabbitMQ发送短信    phone,code
         Map<String,String> map = new HashMap<>();
         map.put("phone",phone);
         map.put("code",code);
        //amqpTemplate.convertAndSend("sms.changes","sms.send",map);

         //3.发送短信后存放redis
         //验证码  code
         stringRedisTemplate.opsForValue().set("lysms_"+phone,code,55, TimeUnit.MINUTES);//放入redis中的有效期为五分钟


     }
    //用户注册
     @PostMapping("/register")
     public void register(@Valid User user, String code){
         System.out.println("用户注册："+user.getUsername()+"Code="+code);

         if (user!=null) {

             //1.判断code验证码是否一致
             String redisCode = stringRedisTemplate.opsForValue().get("lysms_" + user.getPhone());
             //2.判断code验证码是否一致
             if (redisCode.equals(code)){
                 userService.insertUser(user);
             }
         }

     }
    //根据用户名和密码查询用户
     @GetMapping("/query")
     public User query(@RequestParam("username")String username,@RequestParam("password")String password){
         System.out.println("用户注册：username"+username+"密码="+password);
         return new User();
     }


     @PostMapping("login")
     public String login(@RequestParam("username")String username,@RequestParam("password")String password){
         String result = "1";
        //根据用户名查询用户信息
        User user = userService.findUser(username);
        if (user!=null){
            //对比密码
            String newPwd = DigestUtils.md5Hex(password + user.getSalt());
            if (newPwd.equals(user.getPassword())){
                result = "0";
            }
        }


        return result;
     }




}
