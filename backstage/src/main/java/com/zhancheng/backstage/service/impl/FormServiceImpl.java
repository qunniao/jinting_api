package com.zhancheng.backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.FormService;
import com.zhancheng.dao.FormMapper;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Form;
import org.springframework.stereotype.Service;

/**
 * 表单数据
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-27 14:16:30
 */

@Service
public class FormServiceImpl extends ServiceImpl<FormMapper, Form> implements FormService {

    @Override
    public IPage<Form> queryPage(PageDto<Form> pageDto) {

        return baseMapper.selectPage(pageDto.getPage(), new QueryWrapper<Form>().eq("is_deleted", 0));
    }

}