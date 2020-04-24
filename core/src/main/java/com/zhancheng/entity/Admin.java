package com.zhancheng.entity;

import cn.hutool.core.util.ReUtil;
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
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author BianShuHeng
 * @decription
 * @project Admin
 * @date 2019/9/23 18:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("zc_admin")
@Valid
public class Admin extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "管理员id", example = "1")
    private Integer id;

    @ApiModelProperty(name = "username", value = "用户名", example = "zhangsan")
    private String username;

    @ApiModelProperty(name = "pwd", value = "登录密码", example = "123456")
    @JsonIgnore
    private String pwd;

    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "请输入正确的手机号")
    @ApiModelProperty(name = "phone", value = "登录手机号", example = "123456")
    private String phone;

    @ApiModelProperty(name = "nickname", value = "昵称", example = "大牛")
    private String nickname;

    @ApiModelProperty(name = "email", value = "邮箱", example = "13541641@163.com")
    @Email(message = "请输入正确的邮箱格式")
    private String email;

    @ApiModelProperty(name = "cover", value = "头像")
    private String cover;

    @ApiModelProperty(name = "jmsgName", value = "极光账号", example = "zhangsan")
    private String jmsgName;

    @ApiModelProperty(name = "jmsgPwd", value = "极光密码", example = "123456")
    private String jmsgPwd;

    @TableLogic
    @JsonIgnore
    private Boolean isDeleted;
    @JsonIgnore
    private Date gmtCreate;
    @JsonIgnore
    private Date gmtModified;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtLogin;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
