package com.zhancheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.entity.Form;
import org.springframework.stereotype.Repository;

/**
 * 表单数据
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-27 14:16:30
 */
@Repository
public interface FormMapper extends BaseMapper<Form> {

    /**
     * 查询表单列表
     * @param page
     * @return
     */
    IPage<Form> queryList(Page<Form> page);
}
