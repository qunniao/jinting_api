package com.zhancheng.backstage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhancheng.backstage.service.BannerService;
import com.zhancheng.config.security.Verify;
import com.zhancheng.constant.R;
import com.zhancheng.constant.UserIdentity;
import com.zhancheng.entity.Banner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 轮播图
 *
 * @author BianShuHeng
 * @menu
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Api(tags = "轮播图")
@RestController
@RequestMapping("/banners")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询轮播图列表")
    @GetMapping("/list")
    public R<List<Banner>> queryList() {

        return R.ok(bannerService.list(new QueryWrapper<Banner>().orderByDesc("sort")));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "轮播图id"),
            @ApiImplicitParam(name = "type", value = "类型：1.首页"),
            @ApiImplicitParam(name = "fileType", value = "文件类型 1: 图片，2：视频"),
            @ApiImplicitParam(name = "cover", value = "图片"),
            @ApiImplicitParam(name = "productId", value = "商品id"),
            @ApiImplicitParam(name = "url", value = "外部链接"),
            @ApiImplicitParam(name = "sort", value = "排序")
    })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody Banner banner) {

        return R.ok(bannerService.save(banner));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键id"),
    })
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> ids) {

        return R.ok(bannerService.removeByIds(ids));
    }

}
