package com.zhancheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.entity.Case;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 案例表 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Repository
public interface CaseMapper extends BaseMapper<Case> {

    IPage<Case> queryList(Page page);

    Case queryInfo(@Param("cid") Integer cid);
}
