package mmp.gps.common.util;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import com.ning.http.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA {

    private static PublicKey publicKey;
    private static PrivateKey privateKey;
    private static Cipher encryptCipher;
    private static Cipher decryptCipher;


    private static PublicKey getPublicKey() {
        if (publicKey == null) {
            try {
                publicKey = DecodePublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3pmn4H/WbduvuaJv60eCcvrbIqIPK18S1gpfWbEvJDLUMOSTc5zIaDvE1ixYp8hvEAPVMvZI/JqVxaSICRUC3zbaN/RlMgT9oVh8mNw6LcT4fGeqYOFKLQU4obRkW4GSq/ZXA79Og79Ii2Il153/Bo3l8rQZotYz54aHEwp8jLwIDAQAB");
            } catch (Exception var1) {
                var1.printStackTrace();
            }
        }

        return publicKey;
    }

    private static PrivateKey getPrivateKey() {
        if (privateKey == null) {
            try {
                privateKey = DecodePrivateKey("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALemafgf9Zt26+5om/rR4Jy+tsiog8rXxLWCl9ZsS8kMtQw5JNznMhoO8TWLFinyG8QA9Uy9kj8mpXFpIgJFQLfNto39GUyBP2hWHyY3DotxPh8Z6pg4UotBTihtGRbgZKr9lcDv06Dv0iLYiXXnf8GjeXytBmi1jPnhocTCnyMvAgMBAAECgYBPEKc4utGrv9QtTP4ySt9PvE/HLb2nu6MlnfauyusJwJ3FiGiVbjfEvkAclCfToTdQ+DSjeE4ZQBMkM2X/4j7M6Ck2EWr5KW/inC8YJzFUZtWz9Kn8jQ9pGCj/9apafCWCsbFQhI/q/kJrgwmGvGRLvIyyggxTYgf4UfcpLKlnQQJBAOOZO6PQnHZyoLpy48BeoLrV+numdSaI4Pll0Yuk7+8fkMzp3ed064QMJsbIywVjoxLtncGsRI60SmGb4G+lP6ECQQDOkThyu93UsjHhuHgVZBiMfNvpVERlFsuAcRbhpoZslWtrp/lx0Iktsy/kKNRryT5lEE/D0AsbRA3xiIbBArDPAkAeL5CNZXma7BENKXezZ9mBbifOhoE7HyRXb32fO7zmxhT6WHop3IPv+3yZGMB0coaKWCF4MlTVaGwFDhtyM0SBAkA00eFzRSKmHqKuqzLZlWzJj6vT8B5FEx0aTNCjBqfc4CBFuQAJ4F8TdnxKhT48CYuQ4CjlUy9j23UNS/HZLMz1AkEA1MKbMk1B/uLSA3CNgg/5jKNEimFO1QNS93/ucM8mr0IFp2tPbr6sgOQ2LUUSCWA3Da1tXjloN0BWrq9akAp/aw==");
            } catch (Exception var1) {
                var1.printStackTrace();
            }
        }

        return privateKey;
    }

    private static PublicKey DecodePublicKey(String key) throws Exception {
        byte[] keyBytes = Base64.decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    private static PrivateKey DecodePrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    private static Cipher getEncryptCipher() {
        if (encryptCipher == null) {
            try {
                encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                encryptCipher.init(1, getPublicKey());
            } catch (NoSuchAlgorithmException var1) {
                var1.printStackTrace();
            } catch (NoSuchPaddingException var2) {
                var2.printStackTrace();
            } catch (InvalidKeyException var3) {
                var3.printStackTrace();
            }
        }

        return encryptCipher;
    }

    private static Cipher getDecryptCipher() {
        if (decryptCipher == null) {
            try {
                decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                decryptCipher.init(2, getPrivateKey());
            } catch (NoSuchAlgorithmException var1) {
                var1.printStackTrace();
            } catch (NoSuchPaddingException var2) {
                var2.printStackTrace();
            } catch (InvalidKeyException var3) {
                var3.printStackTrace();
            }
        }

        return decryptCipher;
    }

    public static synchronized String encrypt(String message) throws IllegalBlockSizeException, BadPaddingException {
        byte[] plainText = message.getBytes(Charsets.UTF8);
        byte[] cipherText = getEncryptCipher().doFinal(plainText);
        return Base64.encode(cipherText);
    }

    public static synchronized String decrypt(String message) throws IllegalBlockSizeException, BadPaddingException {
        byte[] cipherText = Base64.decode(message);
        byte[] raw = getDecryptCipher().doFinal(cipherText);
        return new String(raw, Charsets.UTF8);
    }
}
