package com.zhancheng.backstage.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.FormService;
import com.zhancheng.config.security.Verify;
import com.zhancheng.constant.UserIdentity;
import com.zhancheng.entity.Form;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.zhancheng.constant.R;
import com.zhancheng.dto.PageDto;

import javax.annotation.Resource;



/**
 * 表单数据
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-27 14:16:30
 */
@Api(tags = "表单数据")
@RestController
@RequestMapping("/forms")
public class FormController {

    @Resource
    private FormService formService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询表单数据列表")
    @GetMapping("/list")
    public R<IPage<Form>> list(PageDto<Form> pageDto){

        return R.ok(formService.queryPage(pageDto));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询表单数据详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<Form> info(@PathVariable("id") Integer id){

        return R.ok(formService.getById(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加表单数据")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "uid", value = "用户id"),
                        @ApiImplicitParam(name = "name", value = "姓名"),
                        @ApiImplicitParam(name = "phone", value = "联系方式"),
                        @ApiImplicitParam(name = "content", value = "表单内容")
            })
    @PostMapping("/save")
    public R<Boolean> save(Form form){

        return R.ok(formService.save(form));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改表单数据")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "id", value = "主键id", required = true),
                        @ApiImplicitParam(name = "uid", value = "用户id"),
                        @ApiImplicitParam(name = "name", value = "姓名"),
                        @ApiImplicitParam(name = "phone", value = "联系方式"),
                        @ApiImplicitParam(name = "content", value = "表单内容")
            })
    @PutMapping("/update")
    public R<Boolean> update(Form form){

        return R.ok(formService.updateById(form));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除表单数据")
    @ApiImplicitParam(name = "ids", value = "主键id")
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> ids){

        return R.ok(formService.removeByIds(ids));
    }

}
