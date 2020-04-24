package com.zhancheng.applet.controller;

import com.zhancheng.applet.service.CaseLabelService;
import com.zhancheng.constant.R;
import com.zhancheng.entity.CaseLabel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 案例标签 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Api(tags = "案例分类")
@RestController
@RequestMapping("/caseLabel")
public class CaseLabelController {

    @Resource
    private CaseLabelService caseLabelService;

    @ApiOperation(value = "查询案例分类列表")
    @GetMapping("/list")
    public R<List<CaseLabel>> list() {

        return R.ok(caseLabelService.list());
    }
    @ApiOperation(value = "查询案例分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bid", value = "主键id",required = true)
    })
    @GetMapping("/info/{bid}")
    public R<CaseLabel> info(@PathVariable Integer bid) {
        return R.ok(caseLabelService.getById(bid));
    }

}

