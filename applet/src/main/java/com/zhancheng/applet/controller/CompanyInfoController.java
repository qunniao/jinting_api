package com.zhancheng.applet.controller;

import com.zhancheng.applet.service.CompanyInfoService;
import com.zhancheng.constant.R;
import com.zhancheng.entity.CompanyInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司信息
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-14 14:13:43
 */
@Api(tags = "公司信息")
@RestController
@RequestMapping("/companyInfos")
public class CompanyInfoController {

    @Resource
    private CompanyInfoService companyInfoService;

    @ApiOperation(value = "查询公司信息详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info")
    public R<List<CompanyInfo>> info(){

        return R.ok(companyInfoService.list());
    }
}
