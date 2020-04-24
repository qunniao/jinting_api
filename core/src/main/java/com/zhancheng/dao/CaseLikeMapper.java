package com.zhancheng.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.vo.CaseLikeVo;
import com.zhancheng.entity.CaseLike;

import java.util.List;

/**
 * <p>
 * 案例浏览点赞收藏 Mapper 接口
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
public interface CaseLikeMapper extends BaseMapper<CaseLike> {

    /**
     * 查询案例收藏列表
     * @param uid
     * @return
     */
    List<CaseLikeVo> queryList(Long uid);

}
