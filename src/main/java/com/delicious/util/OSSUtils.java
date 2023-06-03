package com.delicious.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-03 09:09
 **/
public class OSSUtils {
    //访问域名
    private static final String ali_url = "https://delicious-blood.oss-cn-chengdu.aliyuncs.com/";
    // 地域节点
    private static final String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
    // 阿里云账号AccessKey
    private static final String accessKeyId = "LTAI5t7LR9jphXjguemPyRZz";
    private static final String accessKeySecret = "bwbwaO6eHujBgKRBVcF1Bt54hYXGQy";
    // 填写Bucket名称
    private static final String bucketName = "delicious-blood";
    // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
    private static final String objectName = "EB-furnitures/";
    public static String Upload(MultipartFile img) {
        //生成文件名
        String originalFilename = img.getOriginalFilename();
        //生成随机值，防止文件名相同
        String uuid = UUID.randomUUID().toString().replace("-", " ");
        String imgName = objectName + uuid + "-" +originalFilename;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.putObject(
                    bucketName,
                    imgName,
                    img.getInputStream()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return ali_url + imgName;
    }

    public static boolean Delete(String url) {
        int start = url.indexOf("EB-furnitures");
        if (start == -1) {
            return false;
        }
        String Path = url.substring(start);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.deleteObject(
                    bucketName,
                    Path
            );
        } catch (Exception e) {
            return false;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return true;
    }
}
