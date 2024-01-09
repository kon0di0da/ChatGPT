package com.sunyu.chatgpt.domain.security.service.realm;

import com.sunyu.chatgpt.domain.security.model.vo.JwtToken;
import com.sunyu.chatgpt.domain.security.service.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;

public class JwtRealm extends AuthorizingRealm {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(JwtRealm.class);

    private static JwtUtil jwtUtil = new JwtUtil();

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwt = (String) token.getPrincipal();
        if(jwt == null){
            throw new NullPointerException();
        }
        if(!jwtUtil.isVerify(jwt)){
            throw new UnknownAccountException();
        }
        //获取username并且做处理
        String username = jwtUtil.decode(jwt).get("username").toString();
        logger.info("鉴权用户 username:{}",username);
        return new SimpleAuthenticationInfo(jwt,jwt,"JwtRealm");
    }
}
