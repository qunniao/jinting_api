package com.zhancheng.applet.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.applet.service.BannerService;
import com.zhancheng.constant.R;
import com.zhancheng.entity.Banner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 轮播图
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Api(tags = "轮播图")
@RestController
@RequestMapping("/banners")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @ApiOperation(value = "查询轮播图列表")
    @GetMapping("/list")
    public R<List<Banner>> queryList() {

        return R.ok(bannerService.list(new QueryWrapper<Banner>().orderByDesc("sort")));
    }
}
