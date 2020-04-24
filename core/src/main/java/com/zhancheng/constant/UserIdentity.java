package com.zhancheng.constant;

/**
 * 用于标识用户的身份
 *
 * @author tangchao
 */
public interface UserIdentity {
    /**
     * 管理员用户权限
     */
    String ADMIN = "0";
    /**
     * 游客用户权限(没有验证)
     */
    String NOT = "-1";
    /**
     * 一般用户权限
     */
    String ORDINARY = "1";
}
