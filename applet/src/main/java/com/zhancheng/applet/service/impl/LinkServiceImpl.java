package com.zhancheng.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.LinkService;
import com.zhancheng.dao.LinkMapper;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Link;
import org.springframework.stereotype.Service;

/**
 * 友情链接表
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-26 15:27:44
 */

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

}