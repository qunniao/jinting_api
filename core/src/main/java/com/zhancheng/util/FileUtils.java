package com.zhancheng.util;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.*;
import com.zhancheng.config.exception.MyException;
import com.zhancheng.constant.CodeMsg;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author x
 */
public class FileUtils {

    /**
     * endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
     */

    private static String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    private static String bucketDomain = "https://jinting-jiye.oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = "LTAI8VoT591CDwLd";
    private static String accessKeySecret = "fK8hu4R3k1y24EFsrlLDpuRsIM8O6U";
    private static String bucketName = "jinting-jiye";
    private static String JPG = "jpg";
    private static String PNG = "png";
    private static String GIF = "gif";

    /**
     * 上传图片
     *
     * @return
     */
    public static String uploadImage(List<MultipartFile> images) {

        if (ObjectUtil.isEmpty(images)) {
            return null;
        }
        List<String> list = addFile(images);
        String strip = "";
        if (ObjectUtil.isNotEmpty(list)){
            strip = StringUtils.strip(list.toString(), "[]");
        }

        return strip;
    }

    public static List<String> addFile(List<MultipartFile> fileList) {

        List<String> list = new ArrayList<>();

        if (ObjectUtil.isEmpty(fileList)) {
            return null;
        }

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        for (MultipartFile file : fileList) {

            // 获取文件名
            String fileName = file.getOriginalFilename();

            if (StrUtil.isBlank(fileName)) {
                throw new MyException(CodeMsg.FILE_TYPE_ERROR);
            }

            String fileType = fileName.substring(fileName.lastIndexOf("."));

            // 文件存储入OSS，Object的名称为uuid
            String uuid = FileUtils.uuid();
            InputStream inputStream = null;
            try {
                inputStream = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 添加
            list.add(bucketDomain + "/" + uuid + "." + fileType);
            // 上传图片
            ossClient.putObject(bucketName, uuid + "." + fileType, inputStream);
        }

        ossClient.shutdown();

        return list;
    }

    public static String addInputStream(InputStream inputStream, Integer id) {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ossClient.putObject(bucketName,  id + ".png", inputStream);

        String image = bucketDomain + "/" + id + ".png";

        ossClient.shutdown();

        return image;
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean deleteFile(String filePath) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        boolean exist = ossClient.doesObjectExist(bucketName, filePath);
        if (!exist) {
            System.out.println("文件不存在,filePath=" + filePath);
            return false;
        }
        System.out.println("删除文件,filePath=" + filePath);
        ossClient.deleteObject(bucketName, filePath);
        ossClient.shutdown();
        return true;
    }

    public static String verifyImage(MultipartFile files) {

        String type = "";
        try {
            type = FileTypeUtil.getType(files.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (JPG.equals(type) || PNG.equals(type) || GIF.equals(type)) {
            return type;
        } else {
            throw new MyException(CodeMsg.IMAGE_MISMATCHING);
        }
    }
}
