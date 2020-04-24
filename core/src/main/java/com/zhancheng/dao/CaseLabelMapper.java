package com.zhancheng.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.entity.CaseLabel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 案例标签 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
@Repository
public interface CaseLabelMapper extends BaseMapper<CaseLabel> {

    /**
     * 查询案例类型列表
     *
     * @return list
     */
    List<CaseLabel> queryList();

    /**
     * 查询案例分类详情
     *
     * @param bid 案例分类id
     * @return case label
     */
    CaseLabel queryInfo(Integer bid);

}
