package com.zhancheng.backstage.controller;


import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.SecureUtil;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.AdminService;
import com.zhancheng.commom.RedisTemplate;
import com.zhancheng.config.security.Verify;
import com.zhancheng.constant.CodeMsg;
import com.zhancheng.constant.R;
import com.zhancheng.constant.UserIdentity;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Admin;
import com.zhancheng.entity.User;
import com.zhancheng.util.JgImUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Api(tags="用户个人信息相关")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private AdminService adminService;


    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录名",required = true),
            @ApiImplicitParam(name = "pwd", value = "登录密码", required = true),
            @ApiImplicitParam(name = "phone", value = "登录手机号",required = true),
            @ApiImplicitParam(name = "nickname", value = "昵称"),
            @ApiImplicitParam(name = "email", value = "邮箱"),
            @ApiImplicitParam(name = "cover", value = "头像"),
            @ApiImplicitParam(name = "jmsgName", value = "极光登录用户名"),
            @ApiImplicitParam(name = "jmsgPwd", value = "极光登录密码")
    })
    @PostMapping("/save")
    public R<Boolean> save(@Valid Admin admin) throws APIConnectionException, APIRequestException {
        //注册极光
        JgImUtils.registerAdmin(admin.getJmsgName(), admin.getJmsgPwd());
        admin.setPwd(SecureUtil.md5(admin.getPwd()));
        return R.ok(admin.insert());
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改管理员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id", required = true),
            @ApiImplicitParam(name = "phone", value = "登录手机号"),
            @ApiImplicitParam(name = "openid", value = "第三方"),
            @ApiImplicitParam(name = "nickname", value = "昵称"),
            @ApiImplicitParam(name = "email", value = "邮箱"),
            @ApiImplicitParam(name = "cover", value = "头像")
    })
    @PutMapping("/update")
    public R<Boolean> update(@Valid Admin admin){

        return R.ok(adminService.updateById(admin));
    }

    @Verify
    @PostMapping("/login")
    @ApiOperation(value = "管理员登录", notes = "用户名登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true)
    })
    public R login(String username, String pwd) {
        Map<String, Object> map = new HashMap<>(2);
        Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("username", username)
                .eq("pwd", SecureUtil.md5(pwd)).eq("is_deleted", 0));
        if (ObjectUtil.isNotNull(admin)) {
            String token = redisTemplate.setAdmin(admin);
            admin.setGmtLogin(new Date());
            admin.updateById();
            map.put("token", token);
            map.put("admin",admin);
            return R.ok(map);
        } else {
            return R.fail(CodeMsg.PASSWORD_ERROR);
        }
    }

    @Verify(role = UserIdentity.ADMIN)
    @PostMapping("/updatePassword")
    @ApiOperation(value = "管理员修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pwd", value = "原密码", required = true),
            @ApiImplicitParam(name = "newPwd", value = "新密码", required = true)
    })
    public R updatePassword(String pwd, String newPwd) {
        Integer userId = adminService.getAdminId();
        //判断新密码是否为空
        if (StringUtils.isBlank(newPwd)) {
            return R.fail(CodeMsg.PARAMETER_NULL);
        }
        String password = SecureUtil.md5(pwd);
        Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("id", userId)
                .eq("pwd", password).eq("is_deleted", 0));
        if (ObjectUtil.isNotNull(admin)) {
            //修改密码
            admin.setPwd(SecureUtil.md5(newPwd));
            return R.ok(admin.updateById());
        } else {
            return R.fail(CodeMsg.PASSWORD_ERROR);
        }
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询管理员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "size", value = "页容量", required = true),
            @ApiImplicitParam(name = "phone", value = "手机号"),
            @ApiImplicitParam(name = "sortType", value = "默认为空；1 时间排序")
    })
    @GetMapping("/list")
    public R<IPage> list(PageDto<Admin> pageDto, String phone, Boolean sortType){

        return R.ok(adminService.findList(pageDto, phone, sortType));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "管理员详情")
    @ApiImplicitParam(value = "uid", name = "uid", required = true)
    @GetMapping("/info/{uid}")
    public R<Admin> deleteByIds(@PathVariable Long uid){

        return R.ok(adminService.getById(uid));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除管理员")
    @ApiImplicitParam(value = "id集合", name = "ids", required = true)
    @DeleteMapping("/delete")
    public R<Boolean> deleteByIds(@RequestParam List<Long> ids){
        return R.ok(adminService.removeByIds(ids));
    }
}

