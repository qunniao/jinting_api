package com.zhancheng.backstage.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.User;

/**
 * 用户
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-24 11:24:39
 */
public interface UserService extends IService<User> {

    /**
     * 查询用户列表
     * @param pageDto 分页信息
     * @param phone 手机号
     * @param sortType 排序类型
     * @return
     */
    IPage findList(PageDto<User>  pageDto, String phone, Boolean sortType);
}

