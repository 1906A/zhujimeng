package com.leyou.listener;

import com.leyou.utils.SmsUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhu
 * @date 2020/6/16 - 13:47
 */
//消费者
@Component
public class MessageListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sms.send.queue",declare = "true"),
            exchange = @Exchange(name = "sms.exchanges",ignoreDeclarationExceptions = "true",type = ExchangeTypes.TOPIC),
            key = {"sms.send"}
    ))
    public void sendMsg(Map<String,String> map) throws Exception {
        System.out.println("开始监听短信发送，phone="+map.get("phone")+"===code:"+map.get("code"));

        if(map.size()>0 && map!=null){
            SmsUtils.sendMsg(map.get("phone"),map.get("code"));
        }
        System.out.println("结束短信发送");


    }





}
