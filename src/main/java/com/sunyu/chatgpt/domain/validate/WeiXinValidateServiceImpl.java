package com.sunyu.chatgpt.domain.validate;

//import cn.bugstack.chatgpt.application.IWeiXinValidateService;
//import cn.bugstack.chatgpt.infrastructure.util.sdk.SignatureUtil;
import com.sunyu.chatgpt.application.IWeiXinValidateService;
import com.sunyu.chatgpt.infrastracture.util.sdk.SignatureUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 小傅哥，微信：fustack
 * @description
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Service
public class WeiXinValidateServiceImpl implements IWeiXinValidateService {

    @Value("${wx.config.token}")
    private String token;

    @Override
    public boolean checkSign(String signature, String timestamp, String nonce) {
        return SignatureUtil.check(token, signature, timestamp, nonce);
    }

}
