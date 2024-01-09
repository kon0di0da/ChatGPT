package com.sunyu.chatgpt.domain.security.model.vo;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
    private String jwt;
    public JwtToken(String token) {
        this.jwt = token;
    }

    @Override
    public String getPrincipal() {
        return jwt;
    }

    @Override
    public String getCredentials() {
        return jwt;
    }
}
