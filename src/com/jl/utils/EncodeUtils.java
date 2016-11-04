/**
 * Copyright (c) 2005-2011 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: EncodeUtils.java 1504 2011-03-08 14:49:20Z calvinxiu $
 */
package com.jl.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * 封装各种格式的编码解码工具类.
 * 
 * 1.Commons-Codec的hex/base64 编码
 * 2.Commons-Lang的xml/html escape
 * 3.JDK提供的URLEncoder
 * 
 * @author liuxc
 */
public abstract class EncodeUtils {
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	private static final String DEFAULT_URL_ENCODING = "UTF-8";

	/**
	 * Hex编码, byte[]->String.
	 */
	public static String hexEncode(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码, String->byte[].
	 */
	public static byte[] hexDecode(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException("Hex Decoder exception", e);
		}
	}

	/**
	 * Base64编码, byte[]->String.
	 */
	public static String base64Encode(byte[] input) {
		return Base64.encodeBase64String(input);
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
	 */
	public static String base64UrlSafeEncode(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码, String->byte[].
	 */
	public static byte[] base64Decode(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * Base36(0_9A_Z)编码, long->String.
	 */
	public static String base36Encode(long num) {
		return alphabetEncode(num, 36);
	}

	/**
	 * Base36(0_9A_Z)解码, String->long.
	 */
	public static long base36Decode(String str) {
		return alphabetDecode(str, 36);
	}

	/**
	 * Base62(0_9A_Za_z)编码, long->String.
	 */
	public static String base62Encode(long num) {
		return alphabetEncode(num, 62);
	}

	/**
	 * Base62(0_9A_Za_z)解码, String->long.
	 */
	public static long base62Decode(String str) {
		return alphabetDecode(str, 62);
	}

	private static String alphabetEncode(long num, int base) {
		num = Math.abs(num);
		StringBuilder sb = new StringBuilder();
		for (; num > 0; num /= base) {
			sb.append(ALPHABET.charAt((int) (num % base)));
		}

		return sb.toString();
	}

	private static long alphabetDecode(String str, int base) {
		AssertUtils.hasText(str);

		long result = 0;
		for (int i = 0; i < str.length(); i++) {
			result += ALPHABET.indexOf(str.charAt(i)) * Math.pow(base, i);
		}

		return result;
	}

	/**
	 * URL 编码, Encode默认为UTF-8. 
	 */
	public static String urlEncode(String input) {
		try {
			return URLEncoder.encode(input, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8. 
	 */
	public static String urlDecode(String input) {
		try {
			return URLDecoder.decode(input, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * Html 转码.
	 */
	public static String htmlEscape(String html) {
		return StringEscapeUtils.escapeHtml(html);
	}

	/**
	 * Html 解码.
	 */
	public static String htmlUnescape(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml(htmlEscaped);
	}

	/**
	 * Xml 转码.
	 */
	public static String xmlEscape(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * Xml 解码.
	 */
	public static String xmlUnescape(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}
	public static String md5Encode(String str){
		 String resultString = null;
	        try {
	            resultString = new String(str);
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            // md.digest() 该函数返回值为存放哈希值结果的byte数组
	            resultString = byteToString(md.digest(str.getBytes()));
	        } catch (NoSuchAlgorithmException ex) {
	            ex.printStackTrace();
	        }
	        return resultString;
	    }
	
	/**
	 * MD5算法
	 */
    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }
    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
}
