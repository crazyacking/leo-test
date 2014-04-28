package org.leo.security;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class Base64Test {

	//@Test
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
	
	//@Test
	public void testDecode() throws IOException{
		String readFilePath = "d:/temp/base64.txt";
		String writeFilePath = "d:/temp/decodeBase64-2.txt";
		String base64Str = FileUtils.readFileToString(new File(readFilePath), "gbk");
		//System.out.println(base64Str);
		String txt = new String(Base64.decodeBase64(base64Str), "utf-8");
		//txt = URLDecoder.decode(txt, "utf-8");
		FileUtils.writeStringToFile(new File(writeFilePath), txt);
		System.out.println(txt);
	}
	
	@Test
	public void url() throws UnsupportedEncodingException, DecoderException, EncoderException{
		String enc = "utf-8";
		String hz = "����";
		String udhz = URLEncoder.encode(hz, enc);
		System.out.println(udhz);
		//URLCodec
		System.out.println(URLDecoder.decode(udhz, "utf-8"));
		

		System.out.println(new String(URLCodec.decodeUrl(udhz.getBytes(enc)), enc));
		URLCodec urlCodec = new URLCodec(enc);
		System.out.println(urlCodec.decode(udhz));
		System.out.println(urlCodec.encode(hz));
		
	}
}
