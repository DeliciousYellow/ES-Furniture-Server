package com.delicious.util;

import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-10 18:01
 **/
public class DigestUtils {

    public static String GetDigest(){
        // 使用 SecureRandom 生成 16 字节的随机字节数组
        BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom(16);
        byte[] key = keyGenerator.generateKey();
        // 返回生成的密钥16进制
        return bytesToHex(key);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
}
