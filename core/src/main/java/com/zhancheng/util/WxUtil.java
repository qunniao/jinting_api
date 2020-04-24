package com.zhancheng.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zhancheng.config.exception.MyException;
import com.zhancheng.config.wx.WxConfig;
import com.zhancheng.constant.CodeMsg;
import com.zhancheng.enums.UrlEnum;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BianShuHeng
 * @decription
 * @project WxUtil
 * @date 2019/9/24 18:29
 */
@Component
public class WxUtil {

    @Autowired
    private WxConfig wxConfig;

    Logger logger = LoggerFactory.getLogger(WxUtil.class);

    /**
     * 用户登录
     *
     * @param code          前端登录code
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv            加密算法的初始向量
     * @return 返回用户信息 nickName,avatarUrl,gender,unionid,city,province,country,openid
     * @throws Exception
     */
    public JSONObject loginByWeiXin(String code, String encryptedData, String iv) throws Exception {

        //根据code去调用接口获取用户openid和session_key
        JSONObject json = getSessionKeyAndOpenid(code);
        System.out.println("返回过来的json数据:" + json.toString());
        //会话秘钥
        String sessionKey = json.get("session_key").toString();
        //用户唯一标识
        String openid = json.get("openid").toString();

        //拿到用户session_key和用户敏感数据进行解密，拿到用户信息。
        String decrypts = decrypt(encryptedData, sessionKey, iv, "utf-8");
        JSONObject jsons = JSONUtil.parseObj(decrypts);
        jsons.put("openid", openid);
        return jsons;
    }

    /**
     * 通过code获取sessionKey 和 openid
     *
     * @param code
     * @return sessionKey, openid
     */
    public JSONObject getSessionKeyAndOpenid(String code) {

        String url = UrlEnum.JS_CODE2_SESSION.getUrl();

        Map<String, Object> map = new HashMap<>();
        map.put("appid", wxConfig.getAppId());
        map.put("secret", wxConfig.getSecret());
        map.put("js_code", code);
        map.put("grant_type", wxConfig.getGrantType());
        HttpRequest.get(url).form(map);
        HttpRequest httpRequest = new HttpRequest(url).form(map);
        String body = httpRequest.execute().body();

        JSONObject jsonObject = JSONUtil.parseObj(body);

        String openid = jsonObject.getStr("openid");
        if (StrUtil.isBlank(openid)) {
            System.err.println(jsonObject);
            throw new MyException(CodeMsg.WE_CHAT_CODE_ERROR);
        }
        return jsonObject;
    }

    /**
     * 获取AccessToken
     *
     * @return
     */
    public JSONObject getAccessToken() {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("grant_type", "client_credential");
        paramMap.put("appid", wxConfig.getAppId());
        paramMap.put("secret", wxConfig.getSecret());

        String accessToken = HttpUtil.get(UrlEnum.GET_ACCESSTOKEN.getUrl(), paramMap);
        JSONObject jsonObject = JSONUtil.parseObj(accessToken);
        String errcode = jsonObject.getStr("errcode");
        if (ObjectUtil.isNotNull(errcode) && StrUtil.isNotBlank(errcode)) {
            logger.error("获取accessToken失败{}", errcode);
            throw new MyException(CodeMsg.ACCESS_TOKEN_ERROR);
        }

        return jsonObject;
    }

    /**
     * 获取小程序码
     * @param cid
     * @return
     */
    public String createQRCode(Integer cid) {
        JSONObject jsonObject = getAccessToken();
        String accessToken = jsonObject.getStr("access_token");

        HttpRequest post = HttpUtil.createPost(UrlEnum.CREATE_QR_CODE.getUrl() + accessToken);

        JSONObject param = new JSONObject();
        param.put("path", "pagesA/houseDetails/houseDetails?cid=" + cid);
        String jsonStr = JSONUtil.toJsonStr(param);

        HttpResponse execute = post.body(jsonStr).execute();
        InputStream stream = execute.bodyStream();

        return FileUtils.addInputStream(stream, cid);
    }


    /**
     * AES解密
     *
     * @param data           包括敏感数据在内的完整用户信息的加密数据，
     * @param key            秘钥
     * @param iv             加密算法的初始向量，
     * @param encodingFormat 解密后的结果需要进行的编码
     * @return String
     * @see Exception
     */
    public String decrypt(String data, String key, String iv, String encodingFormat) throws Exception {
//        initialize();

        //被加密的数据
        byte[] dataByte = Base64.decodeBase64(data);
        //加密秘钥
        byte[] keyByte = Base64.decodeBase64(key);
        //偏移量
        byte[] ivByte = Base64.decodeBase64(iv);


        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, encodingFormat);
                return result;
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
