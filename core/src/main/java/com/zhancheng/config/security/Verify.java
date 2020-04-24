package com.zhancheng.config.security;


import com.zhancheng.constant.UserIdentity;

import java.lang.annotation.*;

/**
 * @author Demon
 */
//@Target表示注解能用于哪些元素上面
@Target({ElementType.METHOD, ElementType.TYPE})
//@Retention用于什么时候执行
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Mapping
public @interface Verify {

    /**
     * 标识权限
     *
     * @return
     */
    String role() default UserIdentity.NOT;
}