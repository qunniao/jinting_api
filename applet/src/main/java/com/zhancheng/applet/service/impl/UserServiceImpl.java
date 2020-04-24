package com.zhancheng.applet.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhancheng.applet.service.UserService;
import com.zhancheng.commom.RedisTemplate;
import com.zhancheng.constant.R;
import com.zhancheng.dao.AdminMapper;
import com.zhancheng.dao.UserMapper;
import com.zhancheng.dto.PageDto;
import com.zhancheng.entity.User;
import com.zhancheng.util.WxUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project UserServiceImpl
 * @date 2019/9/24 9:54
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private HttpServletRequest request;

    @Resource
    private WxUtil wxUtil;

    @Override
    public Integer getUserId() {
        String token = request.getHeader("token");
        return redisTemplate.getUserUid(token);
    }

    @Override
    public Map<String, Object> userLogin(String code, String encryptedData, String iv) throws Exception {
        Map<String, Object> map = new HashMap<>(2);
        JSONObject jsonObject = wxUtil.loginByWeiXin(code, encryptedData, iv);
        User userInfo = baseMapper.selectOne(new QueryWrapper<User>()
                .eq("openid", jsonObject.getStr("openid"))
                .eq("is_deleted", 0));
        if (ObjectUtil.isNotNull(userInfo)) {
            userInfo.setGmtLogin(new Date());
            userInfo.updateById();
            String token = redisTemplate.setUser(userInfo);
            map.put("token", token);
            map.put("user", userInfo);
        } else {
            User user = new User();
            user.setNickname(jsonObject.getStr("nickName"))
                    .setOpenid(jsonObject.getStr("openid"))
                    .setGender(jsonObject.getInt("gender"))
                    .setStatus("1")
                    .setCover(jsonObject.getStr("avatarUrl"))
                    .setGmtLogin(new Date());
            user.insert();
            String token = redisTemplate.setUser(user);
            map.put("token", token);
            map.put("user", user);
        }
        return map;
    }

    @Override
    public IPage findList(PageDto<User> page, String phone, Boolean sortType) {

        return baseMapper.selectPage(page.getPage(), new QueryWrapper<User>()
                .likeRight(StrUtil.isNotBlank(phone),"phone", phone)
                .orderByDesc(sortType, "gmt_create"));
    }
}
