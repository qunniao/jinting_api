package com.zhancheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.entity.CaseImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品轮播图
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-12-02 15:52:03
 */
@Repository
public interface CaseImageMapper extends BaseMapper<CaseImage> {
    /**
     * 查询案例轮播图
     * @param cid
     * @return
     */
    List<CaseImage> queryInfo(@Param("cid") Integer cid);
}
