package com.zhancheng.applet.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.applet.service.UserService;
import com.zhancheng.config.security.Verify;
import com.zhancheng.constant.CodeMsg;
import com.zhancheng.constant.R;
import com.zhancheng.constant.UserIdentity;
import com.zhancheng.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags = "用户个人信息相关")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Verify
    @PostMapping("/wxLogin")
    @ApiOperation(value = "微信登录", notes = "传入从微信获得code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "凭证", required = true),
            @ApiImplicitParam(name = "encryptedData", value = "包括敏感数据在内的完整用户信息的加密数据", required = true),
            @ApiImplicitParam(name = "iv", value = "加密算法的初始向量", required = true)
    })
    public R<Map<String, Object>> wxLogin(String code, String encryptedData, String iv) throws Exception {

       return R.ok(userService.userLogin(code, encryptedData, iv));
    }

    @Verify(role = UserIdentity.ADMIN)
    @PostMapping("/updatePassword")
    @ApiOperation(value = "用户修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pwd", value = "", required = true),
            @ApiImplicitParam(name = "newPwd", value = "", required = true)
    })
    public R updatePassword(String pwd, String newPwd) {
        Integer userId = userService.getUserId();

        //判断新密码是否为空
        if (StringUtils.isBlank(newPwd)) {
            return R.fail(CodeMsg.PARAMETER_NULL);
        }
        User user = userService.getOne(new QueryWrapper<User>().eq("id", userId)
                .eq("pwd", SecureUtil.md5(pwd)).eq("is_deleted", 0));
        if (ObjectUtil.isNotNull(user)) {
            //修改密码
            user.setPwd(SecureUtil.md5(newPwd));
            user.updateById();
            return R.ok();
        } else {
            return R.fail(CodeMsg.PASSWORD_ERROR);
        }
    }

    @ApiOperation(value = "用户详情")
    @ApiImplicitParam(value = "uid", name = "uid", required = true)
    @GetMapping("/info/{uid}")
    public R<User> deleteByIds(@PathVariable Long uid) {

        return R.ok(userService.getById(uid));
    }
}

