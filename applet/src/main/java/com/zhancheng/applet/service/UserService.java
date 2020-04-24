package com.zhancheng.applet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.User;

import java.util.Map;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
public interface UserService extends IService<User> {

    Integer getUserId();

    /**
     * 用户登录
     * @param code
     * @param encryptedData
     * @param iv
     * @return
     * @throws Exception
     */
    Map<String, Object> userLogin(String code, String encryptedData, String iv)throws Exception ;

    IPage findList(PageDto<User> pageDto, String phone, Boolean sortType);

}
