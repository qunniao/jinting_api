package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Link;

/**
 * 友情链接表
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-26 15:27:44
 */
public interface LinkService extends IService<Link> {

    /**
     * 分页查询友情链接表列表
     * @param pageDto  分页信息
     * @param link  友情链接表信息
     * @return
     */
    IPage<Link> queryPage(PageDto<Link> pageDto, Link link);
}

