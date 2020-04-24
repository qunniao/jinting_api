package com.zhancheng.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-09-24 11:24:39
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
	
}
