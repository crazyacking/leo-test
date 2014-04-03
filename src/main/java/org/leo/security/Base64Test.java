package org.leo.security;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Base64Test {

	@Test
	public void test() throws UnsupportedEncodingException{
		String str = "���ǿ���������CEO�������״������μ�IDF��ᡣ���������κ�չ���˶�Ӣ�ض���ת�͹��������ڽ��յ������ݽ���ȫ���������һת�͵�˼·������ĿǰӢ�ض���ս���ܽ�Ϊ�ڸ����豸���ṩȫ��ļ������顣";
		byte[] binaryData = str.getBytes("utf-8");
		System.out.println(Base64.encodeBase64URLSafeString(binaryData));
		System.out.println(Base64.encodeBase64String(binaryData));
		System.out.println(new String(Base64.encodeBase64(binaryData), "utf-8"));
		System.out.println(new String(Base64.encodeBase64(binaryData, true), "utf-8"));
		System.out.println(new String(Base64.encodeBase64(binaryData, false), "utf-8"));
		System.out.println(new String(Base64.encodeBase64Chunked(binaryData), "utf-8"));
		System.out.println(new String(Base64.encodeBase64(binaryData, false, true), "utf-8"));
		System.out.println(new String(Base64.decodeBase64(Base64.encodeBase64(binaryData)), "utf-8"));
		System.out.println(new String(Base64.decodeBase64(Base64.encodeBase64Chunked(binaryData)), "utf-8"));
	}
}
