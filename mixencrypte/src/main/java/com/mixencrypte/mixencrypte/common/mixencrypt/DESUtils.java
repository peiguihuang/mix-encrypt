package com.mixencrypte.mixencrypte.common.mixencrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * DES工具类
 *
 * @author 黄培桂
 * @create 2018-12-07 9:24
 **/

public class DESUtils {

    private static final String DES = "DES";
    private static final String PADDING = "DES/ECB/PKCS5Padding";
    private static final String DEFAULT_INCODING = "utf-8";


    /**
     * 加密
     * @param code 明文
     * @param key 秘钥
     * @return
     */
    public final static String encrypt(String code, String key)  {

        try {
            return Base64.encodeBase64String(encrypt(code.getBytes(DEFAULT_INCODING),
                    key.getBytes(DEFAULT_INCODING)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    public static byte[] encrypt(byte[] code, byte[] key) throws Exception {

        SecureRandom sr = new SecureRandom();
        //生成密钥
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);
        //进行加密
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
        return cipher.doFinal(code);

    }

    /**
     * 解密
     * @param code 密文
     * @param key 秘钥
     * @return
     */
    public final static String decrypt(String code, String key) {
        try {
            return new String(decrypt(Base64.decodeBase64(code), key.getBytes(DEFAULT_INCODING)), DEFAULT_INCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

        SecureRandom sr = new SecureRandom();
        //生成密钥
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey sectetKey = keyFactory.generateSecret(dks);
        //进行加密
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.DECRYPT_MODE, sectetKey, sr);
        return cipher.doFinal(src);

    }
}
