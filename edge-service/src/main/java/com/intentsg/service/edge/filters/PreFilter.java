package com.intentsg.service.edge.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class PreFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre";
    }
    //pre
    //post

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        logger.info("pre filter works addr of request is" + request.getRemoteAddr() + "requestURL=" + request.getRequestURL().toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(request.getParameter("name") != null) {
            currentContext.put("SERVICE_KEY_ID", request.getParameter("surname"));
        }

//        if(request.getRemoteAddr().equalsIgnoreCase("192.168.0.102")) {
//            currentContext.setResponseStatusCode(400);
//            currentContext.setResponseBody("access denied");
//            currentContext.setSendZuulResponse(false);
//        }

        return null;
    }
}
