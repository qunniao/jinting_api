package com.zhancheng.applet.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.NewsTypeService;
import com.zhancheng.constant.R;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.NewsType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 新闻类目
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-20 16:35:52
 */
@Api(tags = "新闻类目")
@RestController
@RequestMapping("/newsTypes")
public class NewsTypeController {

    @Resource
    private NewsTypeService newsTypeService;

    @ApiOperation(value = "分页查询新闻类目列表")
    @GetMapping("/list")
    public R<IPage<NewsType>> list(PageDto<NewsType> pageDto, NewsType newsType){

        return R.ok(newsTypeService.queryPage(pageDto, newsType));
    }

}
