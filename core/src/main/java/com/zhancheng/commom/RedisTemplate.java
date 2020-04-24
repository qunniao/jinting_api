package com.zhancheng.commom;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zhancheng.entity.Admin;
import com.zhancheng.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author tangchao
 */

@Component
public class RedisTemplate extends StringRedisTemplate {

    @Autowired
    public RedisTemplate(RedisConnectionFactory connectionFactory) {
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }

    /**
     * 过期时间(秒为单位)
     */
    private long expireTime = 30 * 24 * 60 * 60;


    /**
     * 通过key获取value，并重置过期时间
     *
     * @param key key
     * @return value
     */
    public String get(String key) {
        String value = super.opsForValue().get(key);
        if (!StringUtils.isBlank(value)) {
            super.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 存储数据
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, String value) {
        super.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }


    /**
     * 存储手机验证码
     *
     * @param key   键
     * @param value 值
     */
    public void setSmsCode(String key, String value) {
        super.opsForValue().set(key, value, 320, TimeUnit.SECONDS);
    }

    /**
     * 存储用户信息
     *
     * @param user 用户信息
     * @return 用户token
     */
    public String setUser(User user) {
//        判断用户是否已经存入redis,如果有则删除信息
        String oldToken = get("jinTing:user:uid:" + user.getUid());
        if (oldToken != null) {
            super.delete("jinTing:user:token:" + oldToken);
        }
        String token = IdUtil.simpleUUID();
        Integer uid = user.getUid();
        //存储id : token
        set("jinTing:user:uid:" + uid, token);
        Map<String, Object> userMap = new HashMap<>(2);
        userMap.put("uid", user.getUid());
        String userString = JSONUtil.toJsonStr(userMap);
        //存储token : 用户信息
        set("jinTing:user:token:" + token, userString);
        return token;
    }

    public String setAdmin(Admin admin) {
//        判断用户是否已经存入redis,如果有则删除信息
        String oldToken = get("jinTing:admin:uid:" + admin.getId());
        if (oldToken != null) {
            super.delete("jinTing:admin:token:" + oldToken);
        }
        String token = IdUtil.simpleUUID();
        System.err.println(token);
        Integer uid = admin.getId();
        //存储id : token
        set("jinTing:admin:uid:" + uid, token);
        Map<String, Object> userMap = new HashMap<>(2);
        userMap.put("uid", admin.getId());
        String userString = JSONUtil.toJsonStr(userMap);
        //存储token : 用户信息
        set("jinTing:admin:token:" + token, userString);
        return token;
    }

    /**
     * 传入token,返回用户信息
     *
     * @param token
     * @return 返回用户信息, 如果没有则返回null
     */
    public JSONObject getAdmin(String token) {
        String s = get("jinTing:admin:token:" + token);
        if (StringUtils.isBlank(s)) {
            return null;
        }
        return JSONUtil.parseObj(s);
    }

    /**
     * 传入token,返回用户信息
     *
     * @param token
     * @return 返回用户信息, 如果没有则返回null
     */
    public JSONObject getUser(String token) {
        String s = get("jinTing:user:token:" + token);
        if (StringUtils.isBlank(s)) {
            return null;
        }
        return JSONUtil.parseObj(s);
    }


    /**
     * 查询用户Id
     *
     * @param token
     * @return 返回用户Id
     */
    public Integer getUserUid(String token) {
        String s = get("jinTing:user:token:" + token);
        if (StringUtils.isBlank(s)) {
            return null;
        }
        JSONObject jsonObject = JSONUtil.parseObj(s);

        return jsonObject.getInt("uid");
    }

    /**
     * 查询用户Id
     *
     * @param token
     * @return 返回用户Id
     */
    public Integer getAdminUid(String token) {
        String s = get("jinTing:admin:token:" + token);
        System.err.println(s + ":  s");
        if (StringUtils.isBlank(s)) {
            return null;
        }
        JSONObject jsonObject = JSONUtil.parseObj(s);

        return jsonObject.getInt("uid");
    }
}
