package org.leo.cryptography;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AESUtil {
	

	public static final String KEY_GENERATION_ALG = "PBKDF2WithHmacSHA1";
	public static final int HASH_ITERATIONS = 10000;
	public static final int KEY_LENGTH = 256;
	public static byte[] salt = { 1, 3, 9, 6, 9, 4, 4, 4, 0, 2, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF };

	/**
	 * ��Կ�㷨
	 */
	public static final String KEY_ALGORITHM = "AES";
		
	/**
	 * ����/�����㷨/����ģʽ/��䷽ʽ
	 */	
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
		
	/**
	 * ת����Կ
	 * @param key ��������Կ
	 * @return key ��Կ
	 * 
	 */	
	public static Key toKey(String password) throws Exception{
		/**
		PBEKeySpec keyspec = new PBEKeySpec(password.toCharArray(), salt, HASH_ITERATIONS, KEY_LENGTH);
		
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM, "BC");
		//����������Կ
		return keyFactory.generateSecret(keyspec);
		*/
		
		PBEKeySpec myKeyspec = new PBEKeySpec(password.toCharArray(), salt, HASH_ITERATIONS, KEY_LENGTH);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(KEY_GENERATION_ALG, "BC");
		SecretKey sk = keyfactory.generateSecret(myKeyspec);
		byte[] skAsByteArray = sk.getEncoded();
		SecretKeySpec skforAES = new SecretKeySpec(skAsByteArray, "AES");
		System.out.println(Hex.encodeHexString(skforAES.getEncoded()).length());
		return skforAES;
	}

	/**
	 * ����
	 * @param data ����������
	 * @param key ��Կ
	 * @return byte[] ��������
	 */	
	public static byte[] decrypt(byte[] data, String password)throws Exception{
		//��ԭ��Կ
		Key k = toKey(password);
		/**
		 * ʵ����
		 * ʹ��PKCS7Padding��䷽ʽ�������´���ʵ��
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
		//��ʼ��������Ϊ����ģʽ
		cipher.init(Cipher.DECRYPT_MODE, k);
		//ִ�в���
		return cipher.doFinal(data);
	}
	
	/**
	 * ����
	 * @param data ����������
	 * @param key ��Կ
	 * @return byte[] ��������
	 */	
	public static byte[] encrypt(byte[] data, String password) throws Exception{
		//��ԭ��Կ
		Key k = toKey(password);
		/**
		 * ʵ����
		 * ʹ��PKCS7Padding��䷽ʽ�������´���ʵ��
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM ,"BC");
		//��ʼ��������Ϊ����ģʽ
		cipher.init(Cipher.ENCRYPT_MODE, k);
		//ִ�в���
		return cipher.doFinal(data);
	}
	
	/**
	 * ������Կ
	 * 
	 * @return byte[] ��������Կ
	 */	
	public static byte[] initKey() throws Exception{
		/**
		 * ʵ����
		 * ʹ��128λ��192λ������Կ
		 * KeyGenerator.getInstance(KEY_ALGORITHM,"BC");
		 */
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		/**
		 * ��ʼ��
		 *ʹ��128λ��192λ������Կ�������´���ʵ��
		 *kg.init(128);
		 *kg.init(192);
		 */
		kg.init(256);
		//����������Կ
		SecretKey secretKey = kg.generateKey();
		//�����Կ�Ķ����Ʊ�����ʽ
		return secretKey.getEncoded();
	}


	public static void main(String[] args) throws UnsupportedEncodingException, Exception {

		Security.addProvider(new BouncyCastleProvider());
		/***/
		byte[] en = AESUtil.encrypt("abc".getBytes("utf-8"), "123456");
		System.out.println(Base64.encodeBase64String(en));
		
		//System.out.println(Hex.encodeHexString(initKey()));
	}
	
}
