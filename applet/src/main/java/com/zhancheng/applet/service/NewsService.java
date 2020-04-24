package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.News;
import com.zhancheng.vo.NewsListVo;

/**
 * 新闻资讯
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-20 16:35:52
 */
public interface NewsService extends IService<News> {

    /**
     * 分页查询新闻资讯列表
     * @param pageDto  分页信息
     * @param typeId  分类id
     * @return
     */
    IPage<NewsListVo> queryPage(PageDto<News> pageDto, Integer typeId);
}

