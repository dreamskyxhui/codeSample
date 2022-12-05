package com.share.codesample.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AES256Utils {

    //密钥, 256位32个字节
    public static final String DEFAULT_SECRET_KEY = "c8OeCx3wecFvnekuROG0LF4EL8n7hTYm";

    private static final String AES = "AES";

    //初始向量IV, 初始向量IV的长度规定为128位16个字节, 初始向量的来源为随机生成.
    private static final byte[] KEY_VI = "qo1sdf2Inp6fCOhC".getBytes();

    //加密解密算法/加密模式/填充方式
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    private static Encoder base64Encoder = java.util.Base64.getEncoder();
    private static Decoder base64Decoder = java.util.Base64.getDecoder();

    static {
        java.security.Security.setProperty("crypto.policy", "unlimited");
    }


    /**
     * AES加密
     *
     * @param content
     * @return
     */
    public static String encode(String content) {
        try {
            SecretKey secretKey = new SecretKeySpec(DEFAULT_SECRET_KEY.getBytes(), AES);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(KEY_VI));

            // 获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byteEncode = content.getBytes(StandardCharsets.UTF_8);

            // 根据密码器的初始化方式加密
            byte[] byteAES = cipher.doFinal(byteEncode);

            // 将加密后的数据转换为字符串
            return base64Encoder.encodeToString(byteAES);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AES encode exception:{}", e.toString());
        }

        return null;
    }

    /**
     * 解密
     *
     * @param content
     * @return
     */
    public static String decode(String content) {
        try {
            SecretKey secretKey = new SecretKeySpec(DEFAULT_SECRET_KEY.getBytes(), AES);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(KEY_VI));

            // 将加密并编码后的内容解码成字节数组
            byte[] byteContent = base64Decoder.decode(content);

            // 解密
            byte[] byteDecode = cipher.doFinal(byteContent);

            return new String(byteDecode, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
}
