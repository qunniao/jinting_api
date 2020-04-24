package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.UserService;
import com.zhancheng.dao.UserMapper;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.User;
import org.springframework.stereotype.Service;

/**
 * 用户
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-24 11:24:39
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public IPage findList(PageDto<User> page, String phone, Boolean sortType) {

        return baseMapper.selectPage(page.getPage(), new QueryWrapper<User>()
                .likeRight(StrUtil.isNotBlank(phone),"phone", phone)
                .orderByDesc(sortType, "gmt_create"));

    }

}