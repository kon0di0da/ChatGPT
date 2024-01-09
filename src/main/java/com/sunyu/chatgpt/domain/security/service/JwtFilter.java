package com.sunyu.chatgpt.domain.security.service;

import com.sunyu.chatgpt.domain.security.model.vo.JwtToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends AccessControlFilter {
    private Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        JwtToken token = new JwtToken(request.getParameter("token"));
        try {
            getSubject(servletRequest,servletResponse).login(token);
            return true;
        }catch (Exception e){
            logger.error("鉴权失败",e);
            onLoginFail(servletResponse);

        }

        return false;
    }

    public void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("Auth Err! 孙雨不让你进来");
    }
}
