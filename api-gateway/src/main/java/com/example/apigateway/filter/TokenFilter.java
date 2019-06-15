package com.example.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        // 前置过滤器
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 数字越小越靠前
        return PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();

        String token=request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            // 返回网关不通过
            requestContext.setSendZuulResponse(false);
            // HTTP状态码
            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }

        return null;
    }
}
