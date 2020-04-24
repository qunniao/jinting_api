package com.zhancheng.backstage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Admin;
import com.zhancheng.entity.User;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
public interface AdminService extends IService<Admin> {

    /**
     * 获取 管理员用户id
     * @return
     */
    Integer getAdminId();

    IPage findList(PageDto<Admin> pageDto, String phone, Boolean sortType);

}
