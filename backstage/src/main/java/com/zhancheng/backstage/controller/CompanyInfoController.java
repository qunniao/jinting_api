package com.zhancheng.backstage.controller;

import com.zhancheng.backstage.service.CompanyInfoService;
import com.zhancheng.config.security.Verify;
import com.zhancheng.constant.R;
import com.zhancheng.constant.UserIdentity;
import com.zhancheng.entity.CompanyInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "查询公司信息详情")
    @GetMapping("/info")
    public R<List<CompanyInfo>> info(){

        return R.ok(companyInfoService.list());
    }

    @Verify(role = UserIdentity.ADMIN)
    @ApiOperation(value = "添加或修改公司信息", notes = "传id就是修改,不传id就是修改")
    @ApiImplicitParams({
                        @ApiImplicitParam(name = "id", value = "主键id"),
                        @ApiImplicitParam(name = "name", value = "公司名称"),
                        @ApiImplicitParam(name = "intro", value = "公司介绍"),
                        @ApiImplicitParam(name = "wxNumber", value = "微信号"),
                        @ApiImplicitParam(name = "qqNumber", value = "qq号"),
                        @ApiImplicitParam(name = "email", value = "邮箱"),
                        @ApiImplicitParam(name = "phone", value = "手机号"),
                        @ApiImplicitParam(name = "address", value = "公司地址"),
                        @ApiImplicitParam(name = "businessJson", value = "主营业务"),
                        @ApiImplicitParam(name = "environmentJson", value = "办公环境"),
                        @ApiImplicitParam(name = "certificateJson", value = "荣誉资质"),
                        @ApiImplicitParam(name = "activitiesJson", value = "团队活动")
            })
    @PostMapping("/saveOrUpdate")
    public R<Boolean> save(@RequestBody CompanyInfo companyInfo){

        return R.ok(companyInfoService.saveOrUpdate(companyInfo));
    }

}
