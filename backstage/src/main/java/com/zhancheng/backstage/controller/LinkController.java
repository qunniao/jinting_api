package com.zhancheng.backstage.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.LinkService;
import com.zhancheng.config.security.Verify;
import com.zhancheng.constant.R;
import com.zhancheng.constant.UserIdentity;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Link;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;



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

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "分页查询友情链接列表")
    @GetMapping("/list")
    public R<IPage<Link>> list(PageDto<Link> pageDto){

        return R.ok(linkService.page(pageDto.getPage(),
                new QueryWrapper<Link>().eq("is_deleted", 0)));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询友情链接详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<Link> info(@PathVariable("id") Integer id){

        return R.ok(linkService.getById(id));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加友情链接")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "name", value = "名称",required = true),
                        @ApiImplicitParam(name = "cover", value = "封面图"),
                        @ApiImplicitParam(name = "www", value = "跳转网址", required = true),
                        @ApiImplicitParam(name = "province", value = "省"),
                        @ApiImplicitParam(name = "city", value = "市"),
                        @ApiImplicitParam(name = "typeInt", value = "0表示所有页面都放"),
                        @ApiImplicitParam(name = "sort", value = "排序")
            })
    @PostMapping("/save")
    public R<Boolean> save(Link link){

        return R.ok(linkService.save(link));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改友情链接")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "id", value = "主键id",required = true),
                        @ApiImplicitParam(name = "name", value = "名称"),
                        @ApiImplicitParam(name = "cover", value = "封面图"),
                        @ApiImplicitParam(name = "www", value = "跳转网址"),
                        @ApiImplicitParam(name = "province", value = "省"),
                        @ApiImplicitParam(name = "city", value = "市"),
                        @ApiImplicitParam(name = "typeInt", value = "0表示所有页面都放"),
                        @ApiImplicitParam(name = "sort", value = "排序")
            })
    @PutMapping("/update")
    public R<Boolean> update(Link link){

        return R.ok(linkService.updateById(link));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除友情链接")
    @ApiImplicitParam(name = "ids", value = "主键id集合")
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> ids){

        return R.ok(linkService.removeByIds(ids));
    }

}
