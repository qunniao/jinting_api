package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.NewsTypeService;
import com.zhancheng.dao.NewsTypeMapper;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.NewsType;
import org.springframework.stereotype.Service;

/**
 * 新闻类目
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-20 16:35:52
 */

@Service
public class NewsTypeServiceImpl extends ServiceImpl<NewsTypeMapper, NewsType> implements NewsTypeService {

    @Override
    public IPage<NewsType> queryPage(PageDto<NewsType> pageDto, NewsType newsType) {

        IPage<NewsType> newsTypePages = baseMapper.selectPage(pageDto.getPage(),
                new QueryWrapper<>());

        return newsTypePages;
    }
}