package com.zhancheng.backstage.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.backstage.service.CaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.config.security.Verify;
import com.zhancheng.constant.R;
import com.zhancheng.constant.UserIdentity;
import com.zhancheng.dto.CaseDto;
import com.zhancheng.entity.Case;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 案例表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Api(tags = "案例")
@RestController
@RequestMapping("/case")
public class CaseController {

    @Resource
    private CaseService caseService;

//    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation("案例查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码",required = true),
            @ApiImplicitParam(name = "size", value = "页容量", required = true)
    })
    @GetMapping("/list")
    public R<IPage<Case>> list(Page page){
        IPage<Case> cases = caseService.queryList(page);
        return R.ok(cases);
    }

//    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation("案例详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "案例id"),
    })
    @GetMapping("/info/{cid}")
    public R<Case> info(@PathVariable("cid") Integer cid){

        return R.ok(caseService.queryInfo(cid));
    }

//    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加案例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cover", value = "案例图片"),
            @ApiImplicitParam(name = "mobileDetail", value = "手机端详情(多张图片)"),
            @ApiImplicitParam(name = "sid", value = "店铺id"),
            @ApiImplicitParam(name = "tid", value = "案例类型id"),
            @ApiImplicitParam(name = "name", value = "案例名称"),
            @ApiImplicitParam(name = "minPrice", value = "最小价格"),
            @ApiImplicitParam(name = "maxPrice", value = "最大价格"),
            @ApiImplicitParam(name = "content", value = "详细内容"),
            @ApiImplicitParam(name = "video", value = "视频"),
            @ApiImplicitParam(name = "address", value = "地址"),
            @ApiImplicitParam(name = "recommend", value = "是否推荐，1推荐"),
            @ApiImplicitParam(name = "attrs", value = "多个属性,例:类目:社交,技能:java",
                    example = "{\"类目\":\"社交,java\",\"技能\":\"java\"}"),
            @ApiImplicitParam(name = "caseImageList", value = "轮播图数组,参数: url, sort")
    })
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody CaseDto caseDto)
    {
        return R.ok(caseService.saveInfo(caseDto));
    }

//    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "修改案例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "案例id",required = true),
            @ApiImplicitParam(name = "mobileDetailFiles", value = "手机端详情(多张图片)"),
            @ApiImplicitParam(name = "sid", value = "店铺id"),
            @ApiImplicitParam(name = "tid", value = "案例类型id"),
            @ApiImplicitParam(name = "name", value = "案例名称"),
            @ApiImplicitParam(name = "minPrice", value = "最小价格"),
            @ApiImplicitParam(name = "maxPrice", value = "最大价格"),
            @ApiImplicitParam(name = "address", value = "地址"),
            @ApiImplicitParam(name = "content", value = "详细内容"),
            @ApiImplicitParam(name = "video", value = "视频"),
            @ApiImplicitParam(name = "cover", value = "案例图片"),
            @ApiImplicitParam(name = "recommend", value = "是否推荐，1推荐"),
            @ApiImplicitParam(name = "attrs", value = "多个属性,例:类目:社交,技能:java",
                    example = "{\"类目\":\"社交,java\",\"技能\":\"java\"}"),
            @ApiImplicitParam(name = "caseImageList", value = "轮播图数组,参数: url, sort")
    })
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody CaseDto caseDto){

        return R.ok(caseService.updateInfo(caseDto));
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "删除案例")
    @ApiImplicitParam(value = "cIds", name = "一个或多个主键id",required = true)
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam List<Integer> cIds){
        return R.ok(caseService.removeByIds(cIds));
    }

}

