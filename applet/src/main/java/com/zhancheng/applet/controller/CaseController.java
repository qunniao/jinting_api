package com.zhancheng.applet.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.CaseService;
import com.zhancheng.config.security.Verify;
import com.zhancheng.constant.CodeMsg;
import com.zhancheng.constant.R;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Case;
import com.zhancheng.util.WxUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 案例表 前端控制器
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Api(tags = "案例相关")
@RestController
@RequestMapping("/case")
public class CaseController {

    @Resource
    private CaseService caseService;

    @Resource
    private WxUtil wxUtil;

    @Verify
    @GetMapping
    @ApiOperation(value = "获取案例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bid", value = "二级分类Id,如果为空则查询所有案例"),
            @ApiImplicitParam(name = "word", value = "搜索关键词,不填搜索所有"),
            @ApiImplicitParam(name = "page", value = "页数", required = true)

    })
    public R getCase(String bid, Integer page, String word) {
        if (page == null) {
            return R.fail(CodeMsg.PARAMETER_NULL);
        }
        word = StringUtils.trimToEmpty(word);
        return caseService.getCase(bid, page, word);
    }

    @Verify
    @GetMapping("list")
    @ApiOperation(value = "获取案例列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sortType", value = "1：最新；2: 价格升序; 3: 价格降序;", defaultValue = ""),
            @ApiImplicitParam(name = "address", value = "地址")
    })
    public R<IPage<Case>> getList(PageDto<Case> pageDto, String sortType, String address) {

        return R.ok(caseService.getList(pageDto, sortType, address));
    }

    @ApiOperation(value = "获取案例详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "案例id"),
            @ApiImplicitParam(name = "uid", value = "用户id")
    })
    @GetMapping("/info")
    public R getInfo(Integer cid, Integer uid){

        return R.ok(caseService.queryInfo(cid, uid));
    }

    @ApiOperation(value = "分享")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "案例id"),
            @ApiImplicitParam(name = "uid", value = "用户id")
    })
    @GetMapping("/qrCode/{cid}")
    public R<String> createQRCode(@PathVariable Integer cid){

        return R.ok(wxUtil.createQRCode(cid));
    }

}

