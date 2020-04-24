package com.zhancheng.applet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.LinkService;
import com.zhancheng.constant.R;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Link;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 友情链接表
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-26 15:27:44
 */
@Api(tags = "友情链接")
@RestController
@RequestMapping("/links")
public class LinkController {

    @Resource
    private LinkService linkService;

    @ApiOperation(value = "分页查询友情链接列表")
    @GetMapping("/list")
    public R<IPage<Link>> list(PageDto<Link> pageDto){

        return R.ok(linkService.page(pageDto.getPage(),
                new QueryWrapper<Link>().eq("is_deleted", 0)));
    }

    @ApiOperation(value = "查询友情链接详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<Link> info(@PathVariable("id") Integer id){

        return R.ok(linkService.getById(id));
    }

}
