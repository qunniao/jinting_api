package com.zhancheng.backstage.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.UserService;
import com.zhancheng.config.security.Verify;
import com.zhancheng.constant.R;
import com.zhancheng.constant.UserIdentity;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import javax.annotation.Resource;


/**
 * 用户
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-24 11:24:39
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "size", value = "页容量", required = true),
            @ApiImplicitParam(name = "phone", value = "手机号"),
            @ApiImplicitParam(name = "sortType", value = "默认为0；1 时间排序")
    })
    @GetMapping("/list")
    public R<IPage> list(PageDto<User> pageDto, String phone, Boolean sortType){

        return R.ok(userService.findList(pageDto, phone, sortType));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询用户详情")
    @ApiImplicitParam(name = "uid", value = "主键id")
    @GetMapping("/info/{uid}")
    public R<User> info(@PathVariable("uid") Integer uid){

        return R.ok(userService.getById(uid));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(name = "uids", value = "主键id")
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> uids){

        return R.ok(userService.removeByIds(uids));
    }

}
