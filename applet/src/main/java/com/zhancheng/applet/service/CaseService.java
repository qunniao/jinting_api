package com.zhancheng.applet.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.constant.R;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Case;
import com.zhancheng.vo.CaseVo;

import java.util.List;

/**
 * <p>
 * 案例表 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-08-15
 */
public interface CaseService extends IService<Case> {

    /**
     * 返回案例页面的案例信息
     *
     * @param bid
     * @param page
     * @return
     */
    R getCase(String bid, Integer page, String word);

    /**
     * 返回案例页面的案例信息
     *
     * @param pageDto 分页信息
     * @param sortType 排序类型
     * @param address 地址
     * @return
     */
    IPage<Case> getList(PageDto<Case> pageDto, String sortType, String address);

    /**
     * 查询案例详情
     *
     * @param cid 案例id
     * @param cid 案例id
     * @return case
     */
    CaseVo queryInfo(Integer cid, Integer uid);


}
