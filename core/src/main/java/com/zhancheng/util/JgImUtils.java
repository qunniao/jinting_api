package com.zhancheng.util;

import cn.hutool.core.util.StrUtil;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.RegisterInfo;
import cn.jmessage.api.message.MessageListResult;
import cn.jmessage.api.user.UserListResult;
import com.zhancheng.config.exception.MyException;
import com.zhancheng.constant.CodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project Urora
 * @date 2019/9/24 14:49
 */
@Component
public class JgImUtils {
    /**
     * 需要先去极光申请注册极光IM，获取appKey和masterSecret
     */
    private static String APP_KEY;
    private static String MASTER_SECRET;

    private static JMessageClient client;

    @Value("${JgIm.appKey}")
    public void getAppKey(String appKey) {
        JgImUtils.APP_KEY = appKey;
    }

    @Value("${JgIm.masterSecret}")
    public void getMasterSecret(String masterSecret) {
        JgImUtils.MASTER_SECRET = masterSecret;
    }

    @PostConstruct
    public void getClient(){
        client = new JMessageClient(APP_KEY, MASTER_SECRET);
    }



    /**
     * 注册用户
     */
    public static void registerUsers(String username, String password, String nickname)
            throws APIConnectionException, APIRequestException {
        // username和password的长度限制在 4 ~ 128 位
        checkUsername(username);
        checkUsername(password);

        List<RegisterInfo> users = new ArrayList<>();
        RegisterInfo user = RegisterInfo.newBuilder()
                .setUsername(username).setPassword(password).setNickname(nickname).build();
        users.add(user);
        RegisterInfo[] regUsers = new RegisterInfo[users.size()];
        client.registerUsers(users.toArray(regUsers));
    }

    /**
     * 注册管理员账号
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public static void registerAdmin(String username, String password) throws APIConnectionException, APIRequestException {

        // username和password的长度限制在 4 ~ 128 位
        checkUsername(username);
        checkUsername(password);

        client.registerAdmins(username, password);

    }

    /**
     * 获取所有用户列表
     */
    public static UserListResult getAllUsers(Integer start, Integer end)
            throws APIConnectionException, APIRequestException {

        return client.getUserList(start, end);
    }

    /**
     * 更新昵称
     */
    public static void changeNickname(String username, String nickname)
            throws APIConnectionException, APIRequestException {
        JMessageClient client = new JMessageClient(APP_KEY, MASTER_SECRET);
        // username和password的长度限制在 4 ~ 128 位
        checkUsername(username);
        client.updateUserInfo(username, nickname, null, null, 0, null, null, null);

    }


    /**
     * 删除用户
     */
    public static void deleteUser(String username)
            throws APIConnectionException, APIRequestException {
        JMessageClient client = new JMessageClient(APP_KEY, MASTER_SECRET);
        // username和password的长度限制在 4 ~ 128 位
        checkUsername(username);
        client.deleteUser(username);

    }

    /**
     * 禁用/启用 用户,1启用 0禁用
     */
    public static void forbidUser(String username, Integer disable) throws APIConnectionException, APIRequestException {

        JMessageClient client = new JMessageClient(APP_KEY, MASTER_SECRET);

        ResponseWrapper res = client.forbidUser(username, disable == 0);

    }

    /**
     * 获取用户聊天记录 时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static MessageListResult getUserMessages(String username, Integer count, String beginTime, String endTime)
            throws APIConnectionException, APIRequestException {

        JMessageClient client = new JMessageClient(APP_KEY, MASTER_SECRET);

        return client.getUserMessages(username, count, beginTime, endTime);
    }

    /**
     * 验证用户名和密码是否合法
     * @param username
     */
    private static void checkUsername(String username) {
        // username和password的长度限制在 4 ~ 128 位
        if (StrUtil.isBlank(username) || username.length() < 4 || username.length() > 128) {
            throw new MyException(CodeMsg.JG_USERNAME_NOT_VALID);
        }
    }
}
