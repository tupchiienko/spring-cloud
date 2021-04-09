package com.intentsg.service.edge.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter2 extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        if (currentContext.get("SERVICE_KEY_ID") == null) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {

        System.out.println("FILTER 2------------------------------------------------------");
        return null;
    }
}
