package com.zhancheng.constant;

import lombok.Data;

/**
 * rest返回对象
 *
 * @param <T>
 * @author tangchao
 */
@Data
public class R<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态描述
     */
    private String message;

    /**
     * 服务器响应数据
     */
    private T data;

    /**
     * 返回默认成功方法
     *
     * @return
     */
    public static R ok() {
        R r = new R();
        r.code = 200;
        r.message = "success";
        return r;
    }

    /**
     * 带参数的成功方法
     *
     * @param data 需要返回的数据
     * @return
     */
    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setCode(200);
        r.setMessage("success");
        r.data= data;
        return r;
    }


    /**
     * 返回带参数的错误方法
     *
     * @param code 错误码
     * @param message 错误信息
     * @return
     */
    public static R fail(int code, String message) {
        R r = new R();
        r.code = code;
        r.message = message;
        return r;
    }

    /**
     * (可以用于处理系统异常)
     * 推荐用枚举类型
     */
    public static R fail(CodeMsg codeMsg) {
        return fail(codeMsg.getStatus(), codeMsg.getDescription());
    }

    /**
     * 返回默认的错误方法
     *
     * @return
     */
    public static R fail() {
        R r = new R();
        r.code = 500;
        r.message = "服务器错误";
        return r;
    }

}