package com.edata.common;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 字节工具
 * 
 * @author 生
 *
 */
public class BytesUtil {
	/**
	 * 计算并更新校验码
	 * 
	 * @param src
	 */
	public static void resetXor(byte[] src) {
		int length = src.length;
		byte a = 0;
		for (int i = 0; i < length - 2; i++) {
			a ^= src[i];
		}

		src[length - 2] = a;
	}

	public static byte[] fixed(String text, Charset charset, int fixed) {
		if (text == null)
			return new byte[fixed];
		byte[] raw = text.getBytes(charset);
		if (raw.length == fixed)
			return raw;
		else if (raw.length < fixed) {
			byte[] data = new byte[fixed];
			for (int x = 0; x < raw.length; x++) {
				data[x] = raw[x];
			}
			return data;
		} else
			return Arrays.copyOf(raw, fixed);
	}
}
