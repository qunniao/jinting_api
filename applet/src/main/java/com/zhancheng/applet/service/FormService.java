package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Form;

/**
 * 表单数据
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-27 14:16:30
 */
public interface FormService extends IService<Form> {

    /**
     * 分页查询表单数据列表
     * @param pageDto  分页信息
     * @return
     */
    IPage<Form> queryPage(PageDto<Form> pageDto);
}

