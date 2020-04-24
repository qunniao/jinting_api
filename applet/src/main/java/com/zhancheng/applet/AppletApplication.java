package com.zhancheng.applet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author BianShuHeng
 * @decription 小程序启动类
 * @project AppletApplication
 * @date 2019/9/23 14:23
 */
@ComponentScan(value = "com.zhancheng")
@SpringBootApplication
public class AppletApplication  extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppletApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(AppletApplication.class);
    }
}