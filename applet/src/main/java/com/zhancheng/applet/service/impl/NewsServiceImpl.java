package com.zhancheng.applet.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.NewsService;
import com.zhancheng.dao.NewsMapper;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.News;
import com.zhancheng.vo.NewsListVo;
import org.springframework.stereotype.Service;

/**
 * 新闻资讯
 * @author BianShuHeng
 * @Email  13525382973@163.com
 * @date 2019-09-20 16:35:52
 */

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Override
    public IPage<NewsListVo> queryPage(PageDto<News> pageDto, Integer typeId) {

        IPage<NewsListVo> newsPages = baseMapper.queryList(pageDto.getPage(), typeId);

        return newsPages;
    }

}