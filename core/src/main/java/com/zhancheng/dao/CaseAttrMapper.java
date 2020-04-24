package com.zhancheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.entity.CaseAttr;
import com.zhancheng.entity.CaseLabel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 案例属性名称和值 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Repository
public interface CaseAttrMapper extends BaseMapper<CaseAttr> {
    /**
     * 查询案例参数列表
     *
     * @return list
     */
    List<CaseAttr> queryList(Integer cid);
}
