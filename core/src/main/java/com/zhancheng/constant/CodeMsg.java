package com.zhancheng.constant;

/**
 * 返回状态的枚举
 *
 * @author Demon
 */
public enum CodeMsg {

    /**
     * 成功(所有正常状态的返回)
     */
    OK("SUCCESS", 200),
    SERVER_ERROR("Server error", 500),
    IMAGE_MISMATCHING("图片格式不匹配", 4105),
    RUNTIME_EXCEPTION("运行时异常", 4106),
    NULL_POINTER_EXCEPTION("空指针异常", 4107),
    CLASS_CAST_EXCEPTION("类型强制转换异常", 4108),
    IO_EXCEPTION("IO异常", 4109),
    CLASS_NOT_FOUND_EXCEPTION("指定的类不存在", 4110),
    ARITHMETIC_EXCEPTION("算术异常", 4111),
    ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION("数组下标越界", 4112),
    INDEX_OUT_OF_BOUNDS_EXCEPTION("索引越界", 4113),
    NEGATIVE_ARRAY_SIZE_EXCEPTION("数组负下标", 4114),
    ILLEGAL_ARGUMENT_EXCEPTION("方法的参数错误", 4115),
    ILLEGAL_ACCESS_EXCEPTION("没有访问权限", 4116),
    SECURITY_EXCEPTION("违背安全原则", 4117),
    EOF_EXCEPTION("文件已结束异常", 4118),
    FILE_NOT_FOUND_EXCEPTION("文件未找到", 4119),
    NUMBER_FORMAT_EXCEPTION("数字格式化异常", 4120),
    SQL_EXCEPTION("操作数据库异常", 4121),
    NO_SUCH_METHOD_EXCEPTION("方法未找到", 4122),
    ARRAY_STORE_EXCEPTION("数组存储异常", 4123),
    CLONE_NOT_SUPPORTED_EXCEPTION("不支持克隆", 4124),
    ENUM_CONSTANT_NOT_PRESENT_EXCEPTION("枚举常量不存在", 4125),
    INSTANTIATION_EXCEPTION("实例化异常", 4126),
    TYPE_NOT_PRESENT_EXCEPTION("类型不存在", 4127),
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("缺少Servlet请求参数", 4128),
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("Http消息不可读", 4129),
    TYPE_MISMATCH_EXCEPTION("类型不匹配", 4130),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("Http请求方法错误", 4131),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION("Http媒体类型不可接受", 4132),
    CONVERSION_NOT_SUPPORTED_EXCEPTION("不支持转换", 4133),
    HTTP_MESSAGE_NOT_WRITABLE_EXCEPTION("Http消息不可写", 4134),
    PARAM_VERIFY_EXCEPTION("参数校验异常", 4134),
    JG_USERNAME_NOT_VALID("极光用户名或密码不合法", 4135),
    ALIYUN_ERROR("阿里云服务器请求失败", 4136),
    ALIYUN_BUCKET_NOT_EXIST("阿里云文件上传bucket不存在 ", 4137),
    WECHAT_ERROR("微信服务器请求失败", 4138),
    WE_CHAT_CODE_ERROR("微信code无效", 4139),
    WECHAT_GET_USER_ERROR("微信获得用户信息失败", 4140),


    /**
     * 业务逻辑异常
     */
    TOKEN_IS_NULL("token为空", 41000),
    TO_KENG_ABNORMAL("token 异常", 41001),
    PERMISSION_ERROR("权限不足可能没有登录", 41002),
    PASSWORD_ERROR("账号或者密码错误", 41003),
    USER_NOT_EXISTED("用户不存在!!!", 41004),
    PHONE_NUMBER_EMPTY("手机号为空", 41005),
    VERIFICATION_CODE_ERROR("验证码错误", 41006),
    PARAMETER_NULL("参数为空", 41007),
    ALREADY_EXISTED("添加的信息已经存在", 41008),
    PARAMETER_ERROR("参数错误", 41009),
    QUERY_EMPTY("查询为空", 41010),
    GOODS_NUM_ERROR("商品低于1或者超过库存", 41011),
    COUPON_EXPIRED("优惠券过期", 41012),
    COUPON_FAIL("优惠券不满足条件", 41013),
    NEWS_NOT_EXISTED("新闻资讯不存在", 41014),
    ACCESS_TOKEN_ERROR("获取accessToken失败", 41015),
    FILE_TYPE_ERROR("文件类型出错", 41016),

    ;

    /**
     * 构造方法
     *
     * @param description 描述状态的详细信息
     * @param status      状态代码
     */
    CodeMsg(String description, int status) {
        this.description = description;
        this.status = status;
    }

    private Integer status;

    private String description;

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

}
