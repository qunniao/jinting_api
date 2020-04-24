package com.zhancheng.backstage.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.dto.CaseDto;
import com.zhancheng.entity.Case;

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
     * Query list
     *
     * @param page
     * @return page
     */
    IPage<Case> queryList(Page page);

    /**
     * 添加案例表
     * @param caseDto 案例数据
     * @return
     */
    Boolean saveInfo(CaseDto caseDto);

    /**
     * 更新信息
     *
     * @param caseDto 案例信息
     * @return boolean
     */
    Boolean updateInfo(CaseDto caseDto);

    /**
     * 查询详情
     *
     * @param cid 案例id
     * @return case
     */
    Case queryInfo(Integer cid);

}
