package com.mixencrypte.mixencrypte.common.mixencrypt;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 混合加密工具类
 *
 * @author 黄培桂
 * @create 2018-12-07 9:30
 **/

public class MixEncryptUtils {
    private static final String splitFlag = "#@#";
    /**
     * 混合加密方法-消息发送者
     * @param plaintext 加密明文
     * @param privateKeyA 发送者A私钥
     * @param publicKeyB 接受者B公钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String plaintext,String privateKeyA,String publicKeyB) throws Exception{
        //求明文消息的消息散列值：hA=H'(M)
        String hA = EncryptMd5Util.MD5Encode(plaintext,"UTF-8");

        //发送方用自己的私钥KSA对散列值进行数字签名：h'=EKSA (hA)
        RSAPrivateKey rsaPrivateKey = RSAUtils.getPrivateKey(privateKeyA);
        String hASign = RSAUtils.privateEncrypt(hA,rsaPrivateKey);

        //将明文M和数字签名h'合并为M'，M'=【M h'】
        StringBuilder mixPlaintext = new StringBuilder(plaintext);
        mixPlaintext.append(splitFlag + hASign);

        //随机产牛—个DES密钥KDES
        String DESKey = EncryptMd5Util.getUUID();

        //用DES密钥KDES加密M'，C1=EKDES( M')
        String c1 = DESUtils.encrypt(mixPlaintext.toString(),DESKey);

        //用接受方B的公钥加密DES密钥，C2=EKPB(KDES)
        String c2 = RSAUtils.publicEncrypt(DESKey,RSAUtils.getPublicKey(publicKeyB));// DESUtils.encrypt(DESKey,publicKeyB);

        StringBuilder message = new StringBuilder(c1);
        message.append(splitFlag + c2);
        return message.toString();
    }

    public static String decrypt(String ciphertext,String privateKeyB,String publicKeyA) throws Exception{
        String[] c1c2 = ciphertext.split(splitFlag);
        String c1= c1c2[0];
        String c2=c1c2[1];

        //B用其私钥打开数字信封，得到发送方的DES密钥，KDES=DKSB( C2)
        RSAPrivateKey rsaPrivateKeyB = RSAUtils.getPrivateKey(privateKeyB);
        String desKey = RSAUtils.privateDecrypt(c2,rsaPrivateKeyB);

        //再用此密钥去解密消息C1，M'=DKDES( C1)
        String c1Message = DESUtils.decrypt(c1,desKey);

        //从M’中分离出M和h’
        String[] Mh = c1Message.split(splitFlag);
        String M = Mh[0]; //明文
        String h = Mh[1];

        //求明文消息的消息散列值，hB=H(M)
        String hB = EncryptMd5Util.MD5Encode(M,"UTF-8");

        //对A的数字签名h’进行身份验证，hA=DKPA (h’)
        RSAPublicKey hAPublicKey = RSAUtils.getPublicKey(publicKeyA);
        String hA = RSAUtils.publicDecrypt(h,hAPublicKey);
        if (!hA.equals(hB)){
            throw new MixEncrypteException("数字签名不一致，数据被篡改！");
        }
        return M;
    }
}
