package com.wangyousong.app.growthbackend.common;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class WebRequestLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(WebRequestLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(httpRequest, 0);

        long startTime = System.currentTimeMillis();
        chain.doFilter(wrappedRequest, response);
        long duration = System.currentTimeMillis() - startTime;

        // 记录请求和响应信息
        logger.info("WEB REQUEST: {} {} - Status: {} - Duration: {}ms",
                httpRequest.getMethod(),
                httpRequest.getRequestURI(),
                ((HttpServletResponse) response).getStatus(),
                duration);
    }
}