package com.zhancheng.applet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.FormService;
import com.zhancheng.constant.R;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Form;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "分页查询表单数据列表")
    @GetMapping("/list")
    public R<IPage<Form>> list(PageDto<Form> pageDto){

        return R.ok(formService.queryPage(pageDto));
    }

    @ApiOperation(value = "查询表单数据详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<Form> info(@PathVariable("id") Integer id){

        return R.ok(formService.getById(id));
    }

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

}
