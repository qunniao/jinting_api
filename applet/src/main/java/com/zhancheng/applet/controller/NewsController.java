package com.zhancheng.applet.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.applet.service.NewsService;
import com.zhancheng.config.exception.MyException;
import com.zhancheng.constant.CodeMsg;
import com.zhancheng.constant.R;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.News;
import com.zhancheng.vo.NewsListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 新闻资讯
 * @menu
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-20 16:35:52
 */
@Api(tags = "新闻资讯")
@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    @ApiOperation(value = "分页查询新闻资讯列表 按照 sort 和 创建时间倒序排列")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "分类id")
    })
    @GetMapping("/list")
    public R<IPage<NewsListVo>> list(PageDto<News> pageDto, Integer typeId){

        return R.ok(newsService.queryPage(pageDto, typeId));
    }

    @ApiOperation(value = "查询新闻资讯详情")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/info/{id}")
    public R<News> info(@PathVariable("id") Integer id){

        return R.ok(newsService.getById(id));
    }

    @ApiOperation(value = "增加浏览量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id",required = true),
    })
    @PutMapping("/update/{id}")
    public R<Boolean> updateWatchNum(@PathVariable Integer id){

        News news = newsService.getById(id);

        if (ObjectUtil.isNull(news)){
            throw new MyException(CodeMsg.NEWS_NOT_EXISTED);
        }

        news.setWatchNum(news.getWatchNum() + 1);

        return R.ok(news.updateById());
    }
}
