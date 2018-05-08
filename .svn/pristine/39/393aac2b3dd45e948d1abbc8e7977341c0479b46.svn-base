package com.rayton.gps.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


public class FuckRSA {

    private static KeyPair keyPair;

    static {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            SecureRandom random = new SecureRandom();
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
            generator.initialize(1024, random);
            keyPair = generator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // private static KeyPair initKey() {
    //     try {
    //         Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    //         SecureRandom random = new SecureRandom();
    //         KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
    //         generator.initialize(1024, random);
    //         return generator.generateKeyPair();
    //     } catch (Exception e) {
    //         throw new RuntimeException(e);
    //     }
    // }

    /**
     * 生成public key
     *
     * @return
     */
    public static String generateBase64PublicKey() {
        RSAPublicKey key = (RSAPublicKey) keyPair.getPublic();
        return new String(Base64.encodeBase64(key.getEncoded()));
    }

    /**
     * 解密
     *
     * @param string
     * @return
     */
    public static String decryptBase64(String string) {
        return new String(decrypt(Base64.decodeBase64(string)));
    }

    private static byte[] decrypt(byte[] string) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
            RSAPrivateKey pbk = (RSAPrivateKey) keyPair.getPrivate();
            cipher.init(Cipher.DECRYPT_MODE, pbk);
            byte[] plainText = cipher.doFinal(string);
            return plainText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}