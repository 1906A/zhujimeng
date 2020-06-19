package com.leyou.filter;

import com.leyou.common.CookieUtils;
import com.leyou.common.JwtUtils;
import com.leyou.config.FilterProperties;
import com.leyou.config.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhu
 * @date 2020/6/18 - 16:36
 */
@Component
@EnableConfigurationProperties(JwtProperties.class)
public class LoginFilter extends ZuulFilter {

    @Autowired
    private JwtProperties properties;

    @Autowired
    FilterProperties filterProperties;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest request = context.getRequest();
        // 获取token
        String token = CookieUtils.getCookieValue(request, this.properties.getCookieName());
        // 校验
        try {
            // 校验通过什么都不做，即放行
            JwtUtils.getInfoFromToken(token, this.properties.getPublicKey());
        } catch (Exception e) {
            // 校验出现异常，返回403
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null;
    }
}