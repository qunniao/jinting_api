package com.zhancheng.backstage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.backstage.service.AdminService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhancheng.commom.RedisTemplate;
import com.zhancheng.dao.AdminMapper;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.Admin;
import com.zhancheng.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author tangchao
 * @since 2019-07-29
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HttpServletRequest request;

    @Override
    public Integer getAdminId() {
        String token = request.getHeader("token");

        return redisTemplate.getAdminUid(token);
    }

    @Override
    public IPage findList(PageDto<Admin> page, String phone, Boolean sortType) {

        return baseMapper.selectPage(page.getPage(), new QueryWrapper<Admin>()
                .likeRight(StrUtil.isNotBlank(phone),"phone", phone)
                .orderByDesc(ObjectUtil.isNotNull(sortType), "gmt_create"));
    }
}
