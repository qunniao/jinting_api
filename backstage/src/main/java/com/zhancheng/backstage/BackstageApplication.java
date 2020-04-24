package com.zhancheng.backstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author BianShuHeng
 * @decription 后台启动类
 * @project AppletApplication
 * @date 2019/9/23 14:23
 */

@ComponentScan(value = "com.zhancheng")
@SpringBootApplication
public class BackstageApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BackstageApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(BackstageApplication.class);
    }

}