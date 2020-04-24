package com.zhancheng.backstage.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.NewsType;

/**
 * 新闻类目
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-20 16:35:52
 */
public interface NewsTypeService extends IService<NewsType> {

    /**
     * 分页查询新闻类目列表
     * @param pageDto  分页信息
     * @param newsType  新闻类目信息
     * @return
     */
    IPage<NewsType> queryPage(PageDto<NewsType> pageDto, NewsType newsType);
}

