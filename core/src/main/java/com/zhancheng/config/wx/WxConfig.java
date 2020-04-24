package com.zhancheng.config.wx;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author BianShuHeng
 * @decription
 * @project WxConfig
 * @date 2019/9/25 10:09
 */
@Component
@Data
public class WxConfig {

    /**
     * appId
     */
    @Value("${weChat.appId}")
    private String appId;

    /**
     * secret
     */
    @Value("${weChat.secret}")
    private String secret;

    /**
     * 创建类型
     */
    @Value("${weChat.grantType}")
    private String grantType;
}
