package com.example.apigateway.filter;

import com.example.apigateway.exception.RateLimitException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;


import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

@Component
public class RateLimitFilter extends ZuulFilter {
    //import com.google.common.util.concurrent.RateLimiter;
    // 每秒钟能产生多少令牌
    private static final RateLimiter RATE_LIMITER=RateLimiter.create(100);

    @Override
    public String filterType() {
        // 前置过滤器
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 数字越小越靠前
        return SERVLET_DETECTION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if (!RATE_LIMITER.tryAcquire()) {
            throw new RateLimitException();
        }
        return null;
    }
}
