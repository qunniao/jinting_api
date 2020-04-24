package com.zhancheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "管理员id", example = "1")
    private Integer uid;

    @ApiModelProperty(name = "username", value = "用户名", example = "zhangsan")
    private String username;

    @ApiModelProperty(name = "pwd", value = "登录密码", example = "123456")
    @JsonIgnore
    private String pwd;

    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\\\d{4,8}$", message = "请输入正确的手机号")
    @ApiModelProperty(name = "phone", value = "登录手机号", example = "123456")
    private String phone;

    @ApiModelProperty(name = "openid", value = "openid", example = "123456")
    private String openid;

    @ApiModelProperty(name = "nickname", value = "昵称", example = "大牛")
    private String nickname;

    @ApiModelProperty(name = "signature", value = "个性签名", example = "你管我是谁")
    private String signature;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(name = "birth", value = "出生年月日", example = "1998-01-12")
    private Date birth;

    @ApiModelProperty(name = "gender", value = "性别0为保密，1为男，2为女", example = "1")
    private Integer gender;

    @ApiModelProperty(name = "email", value = "邮箱", example = "13541641@163.com")
    @Email(message = "请输入正确的邮箱格式")
    private String email;

    @ApiModelProperty(name = "cover", value = "头像")
    private String cover;

    @ApiModelProperty(name = "status", value = "0是管理员用户,1是普通用户", example = "1")
    private String status;

    @ApiModelProperty(name = "jmsgName", value = "极光账号", example = "zhangsan")
    private String jmsgName;

    @ApiModelProperty(name = "jmsgPwd", value = "极光密码", example = "123456")
    private String jmsgPwd;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "gmtCreate", value = "注册时间", example = "123456")
    private String gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "gmtLogin", value = "最后登录时间", example = "123456")
    private Date gmtLogin;

    @TableLogic
    private Boolean isDeleted;

    private Date gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
