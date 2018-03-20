package com.edata.monitor.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.ning.http.util.Base64;

public class RSA {
	private RSA() {
	}

	private static PublicKey publicKey;

	/**
	 * 获取公钥
	 */
	private static PublicKey getPublicKey() {
		if (publicKey == null) {
			try {
				publicKey = DecodePublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3pmn4H/WbduvuaJv60eCcvrbIqIPK18S1gpfWbEvJDLUMOSTc5zIaDvE1ixYp8hvEAPVMvZI/JqVxaSICRUC3zbaN/RlMgT9oVh8mNw6LcT4fGeqYOFKLQU4obRkW4GSq/ZXA79Og79Ii2Il153/Bo3l8rQZotYz54aHEwp8jLwIDAQAB");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return publicKey;
	}

	private static PrivateKey privateKey;

	/**
	 * 获取私钥
	 */
	private static PrivateKey getPrivateKey() {
		if (privateKey == null) {
			try {
				privateKey = DecodePrivateKey("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALemafgf9Zt26+5om/rR4Jy+tsiog8rXxLWCl9ZsS8kMtQw5JNznMhoO8TWLFinyG8QA9Uy9kj8mpXFpIgJFQLfNto39GUyBP2hWHyY3DotxPh8Z6pg4UotBTihtGRbgZKr9lcDv06Dv0iLYiXXnf8GjeXytBmi1jPnhocTCnyMvAgMBAAECgYBPEKc4utGrv9QtTP4ySt9PvE/HLb2nu6MlnfauyusJwJ3FiGiVbjfEvkAclCfToTdQ+DSjeE4ZQBMkM2X/4j7M6Ck2EWr5KW/inC8YJzFUZtWz9Kn8jQ9pGCj/9apafCWCsbFQhI/q/kJrgwmGvGRLvIyyggxTYgf4UfcpLKlnQQJBAOOZO6PQnHZyoLpy48BeoLrV+numdSaI4Pll0Yuk7+8fkMzp3ed064QMJsbIywVjoxLtncGsRI60SmGb4G+lP6ECQQDOkThyu93UsjHhuHgVZBiMfNvpVERlFsuAcRbhpoZslWtrp/lx0Iktsy/kKNRryT5lEE/D0AsbRA3xiIbBArDPAkAeL5CNZXma7BENKXezZ9mBbifOhoE7HyRXb32fO7zmxhT6WHop3IPv+3yZGMB0coaKWCF4MlTVaGwFDhtyM0SBAkA00eFzRSKmHqKuqzLZlWzJj6vT8B5FEx0aTNCjBqfc4CBFuQAJ4F8TdnxKhT48CYuQ4CjlUy9j23UNS/HZLMz1AkEA1MKbMk1B/uLSA3CNgg/5jKNEimFO1QNS93/ucM8mr0IFp2tPbr6sgOQ2LUUSCWA3Da1tXjloN0BWrq9akAp/aw==");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return privateKey;
	}

	/**
	 * 
	 * 解密base64编码得到公钥
	 * 
	 *
	 * 
	 * @param key
	 * 
	 *            密钥字符串（经过base64编码）
	 * 
	 * @throws Exception
	 */

	private static PublicKey DecodePublicKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = Base64.decode(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 
	 * 解密base64编码得到私钥
	 * 
	 *
	 * 
	 * @param key
	 * 
	 *            密钥字符串（经过base64编码）
	 * 
	 * @throws Exception
	 */

	private static PrivateKey DecodePrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = Base64.decode(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	private static Cipher encryptCipher;

	/**
	 * 获取加密密码器
	 */
	private static Cipher getEncryptCipher() {
		if (encryptCipher == null) {
			try {
				encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				encryptCipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		return encryptCipher;
	}

	private static Cipher decryptCipher;

	/**
	 * 获取解密密码器
	 */
	private static Cipher getDecryptCipher() {
		if (decryptCipher == null) {
			try {
				decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				decryptCipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		return decryptCipher;
	}

	/**
	 * 把消息加密
	 * 
	 * @param message
	 *            明文
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * 
	 * @return Base64密文
	 * @throws UnsupportedEncodingException
	 */
	public synchronized static String encrypt(String message) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		byte[] plainText = message.getBytes(Charsets.UTF8);

		ByteBuffer out = ByteBuffer.allocate(1024);
		ByteBuffer in = ByteBuffer.wrap(plainText);
		while (in.remaining() > 0) {
			byte[] data = null;
			if (in.remaining() > 100)
				data = new byte[100];
			else
				data = new byte[in.remaining()];
			in.get(data);
			byte[] cipherText = getEncryptCipher().doFinal(data);
			out.put(cipherText);
		}
		out.flip();
		byte[] raw = new byte[out.limit()];
		out.get(raw);
		return Base64.encode(raw);
	}

	/**
	 * 把密文解密成明文
	 * 
	 * @param message
	 *            Base64密文
	 * @return 明文
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws UnsupportedEncodingException
	 */
	public synchronized static String decrypt(String message) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		byte[] cipherText = Base64.decode(message);
		ByteBuffer out = ByteBuffer.allocate(1024);
		ByteBuffer in = ByteBuffer.wrap(cipherText);
		while (in.remaining() > 0) {
			byte[] data = null;
			if (in.remaining() > 128)
				data = new byte[128];
			else
				data = new byte[in.remaining()];
			in.get(data);
			byte[] plainText = getDecryptCipher().doFinal(data);
			out.put(plainText);
		}
		out.flip();
		byte[] raw = new byte[out.limit()];
		out.get(raw);
		return new String(raw, Charsets.UTF8);
	}
}
